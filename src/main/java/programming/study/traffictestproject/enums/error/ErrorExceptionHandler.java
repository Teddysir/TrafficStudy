package programming.study.traffictestproject.enums.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import programming.study.traffictestproject.security.error.*;

@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final BadRequestException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }

    @ExceptionHandler({DuplicateException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final DuplicateException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }

    @ExceptionHandler({ExpiredTokenException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final ExpiredTokenException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }

    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final InvalidTokenException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final NotFoundException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }


    @ExceptionHandler({UnAuthorizationException.class})
    public ResponseEntity<ErrorEntity> exceptionHandler(final UnAuthorizationException e) {
        ErrorEntity error = ErrorEntity.builder()
                .errorCode(Integer.parseInt(e.getErrorCode().getCode()))
                .errorMessage(e.getMessage())
                .build();

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(error);
    }







}
