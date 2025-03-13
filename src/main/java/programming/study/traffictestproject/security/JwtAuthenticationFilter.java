package programming.study.traffictestproject.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import programming.study.traffictestproject.enums.error.ErrorJwtCode;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        ErrorJwtCode errorCode;

        try {
            String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

            if (refreshToken != null && refreshToken.trim().isEmpty()) {
                setResponse(response, ErrorJwtCode.INVALID_JWT_FORMAT);
                return;
            }

            if (refreshToken != null && path.contains("/reissue")) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (ExpiredJwtException e) {
            errorCode = ErrorJwtCode.EXPIRED_REFRESH_TOKEN;
            setResponse(response, errorCode);
            return;
        }

        try {
            String accessToken = jwtTokenProvider.resolveAccessToken(request);
            String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

            if(accessToken == null && refreshToken ==null) {
                filterChain.doFilter(request, response);
            } else if (accessToken != null && refreshToken == null) {
                if (jwtTokenProvider.validateAccessToken(accessToken)) {
                    filterChain.doFilter(request, response);
                }
            } else if (accessToken != null && refreshToken != null) {
                setResponse(response, ErrorJwtCode.INVALID_USE_TWO_TOKENS);
                filterChain.doFilter(request, response);
            }
        }catch (MalformedJwtException e) {
            errorCode = ErrorJwtCode.INVALID_JWT_FORMAT;
            setResponse(response, errorCode);
        } catch (ExpiredJwtException e) {
            errorCode = ErrorJwtCode.EXPIRED_ACCESS_TOKEN;
            setResponse(response, errorCode);
        } catch (UnsupportedJwtException e) {
            errorCode = ErrorJwtCode.UNSUPPORTED_JWT_TOKEN;
            setResponse(response, errorCode);
        } catch (RuntimeException e) {
            errorCode = ErrorJwtCode.INVALID_VALUE;
            setResponse(response, errorCode);
        } catch (Exception e) {
            e.printStackTrace(); // 모든 오류 찍기 지워야하는 코드이다.
            throw new RuntimeException(e);
        }


    }

    private void setResponse(HttpServletResponse response, ErrorJwtCode errorJwtCode) throws IOException {
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 여기 오류는 무조건 권한 오류

        jsonObject.put("code", errorJwtCode.getCode());
        jsonObject.put("message", errorJwtCode.getMessage());

        response.getWriter().print(jsonObject);
        response.getWriter().flush();
    }

}
