package programming.study.traffictestproject.enums.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "400","400 BAD REQUEST"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED,"401","401 UNAUTHORIZED"),
    FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN,"403","403 FORBIDDEN"),
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"404","404 NOT FOUND"),
    INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "500", "500 SERVER ERROR");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
