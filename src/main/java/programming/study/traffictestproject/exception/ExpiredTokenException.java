package programming.study.traffictestproject.exception;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class ExpiredTokenException extends BusinessException {
    public ExpiredTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
