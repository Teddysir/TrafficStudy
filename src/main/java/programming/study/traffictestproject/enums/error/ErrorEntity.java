package programming.study.traffictestproject.enums.error;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ErrorEntity {
    private final int errorCode;
    private final String errorMessage;
}
