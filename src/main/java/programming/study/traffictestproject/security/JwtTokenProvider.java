package programming.study.traffictestproject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

//    public String createAccessToken(Long id) {
//        try {
//
//        } catch ()
//    }
//
//    public String createRefreshToken(Long id) {
//
//    }


}
