package programming.study.traffictestproject.Enums.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("유저"),
    ADMIN("관리자");

    private final String Role;

}
