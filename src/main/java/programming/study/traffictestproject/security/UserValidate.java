package programming.study.traffictestproject.security;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidate {

    private final JwtTokenProvider jwtTokenProvider;

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

    private Claims extractAllClaims(String token) {
        return getParser()
                .parseSignedClaims(token)
                .getPayload();
    }

    private JwtParser getParser() {
        return Jwts.parser()
                .verifyWith(jwtTokenProvider.getSigningKey())
                .build();
    }
}
