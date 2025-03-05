package programming.study.traffictestproject.security;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import programming.study.traffictestproject.enums.security.TokenType;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
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

}
