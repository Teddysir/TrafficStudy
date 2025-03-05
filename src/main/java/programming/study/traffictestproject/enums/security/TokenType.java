package programming.study.traffictestproject.enums.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    REFRESH_TOKEN("accessToken"),
    ACCESS_TOKEN("refreshToken");

    private final String type;
}
