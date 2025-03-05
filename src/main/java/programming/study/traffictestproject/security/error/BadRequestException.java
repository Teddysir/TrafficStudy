package programming.study.traffictestproject.security.error;

import programming.study.traffictestproject.enums.error.ErrorCode;

public class BadRequestException extends BusinessException{

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
