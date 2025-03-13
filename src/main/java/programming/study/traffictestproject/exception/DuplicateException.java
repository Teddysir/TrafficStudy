package programming.study.traffictestproject.exception;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class DuplicateException extends BusinessException {
    public DuplicateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
