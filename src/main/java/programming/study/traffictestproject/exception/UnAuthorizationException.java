package programming.study.traffictestproject.exception;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class UnAuthorizationException extends BusinessException {
    public UnAuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
