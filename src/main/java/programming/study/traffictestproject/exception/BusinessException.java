package programming.study.traffictestproject.exception;

import lombok.Getter;
import programming.study.traffictestproject.enums.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
