package programming.study.traffictestproject.security;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import programming.study.traffictestproject.enums.error.ErrorCode;
import programming.study.traffictestproject.enums.security.TokenType;
import programming.study.traffictestproject.exception.ExpiredTokenException;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessExpiration}")
    private long accessExpiration;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    @Value("${jwt.aesKey}")
    private String aesKey;

    @PostConstruct
    public SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createAccessToken(Long id) {
        return createToken(id, accessExpiration, TokenType.ACCESS_TOKEN);
    }

    private String createRefreshToken(Long id) {
        return createToken(id, refreshExpiration, TokenType.REFRESH_TOKEN);
    }

    private String createToken(Long id, long tokenValid, TokenType tokenType) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("tokenType", tokenType.getType());

        Claims claims = Jwts.claims().subject(jsonObject.toString()).build();
        Date date = new Date();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + tokenValid))
                .signWith(getSigningKey())
                .compact();
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Authorization", accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("RefreshToken", refreshToken);
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String headerContents = request.getHeader("Authorization");
        if (headerContents != null && headerContents.startsWith("Bearer ")) {
            String token = headerContents.substring(7).trim();
            if(tokenType(token).equals("accessToken")) {
                return token;
            }
        }
        return null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        String headerContents = request.getHeader("RefreshToken");
        if (headerContents != null && headerContents.startsWith("Bearer ")) {
            String token = headerContents.substring(7).trim();
            if(tokenType(token).equals("refreshToken")) {
                return token;
            }
        }
        return null;
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Claims claims = extractAllClaims(accessToken);

            return !claims.getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("5001", ErrorCode.ACCESS_DENIED_EXCEPTION);
        } catch (UnsupportedJwtException ex) {
            throw new UnsupportedJwtException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Claims claims = extractAllClaims(refreshToken);
            return !claims.getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("5002", ErrorCode.ACCESS_DENIED_EXCEPTION);
        } catch (UnsupportedJwtException ex) {
            throw new UnsupportedJwtException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty");
        }
    }



    public String tokenType(String token) {
        JsonElement tokenType = extractValue(token).get("tokenType");
        return tokenType.getAsString();
    }

    public Long extractId(String token) {
        JsonElement id = extractValue(token).get("id");
        return id.getAsLong();
    }

    private JsonObject extractValue(String token) {
        String subject = extractAllClaims(token).getSubject();
        return new Gson().fromJson(subject, JsonObject.class);
    }

    public Claims extractAllClaims(String token) {
        return getParser()
                .parseSignedClaims(token)
                .getPayload();
    }

    private JwtParser getParser() {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build();
    }
}
