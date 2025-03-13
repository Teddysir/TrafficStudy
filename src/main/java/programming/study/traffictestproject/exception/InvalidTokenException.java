package programming.study.traffictestproject.exception;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
