package programming.study.traffictestproject.exception;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
