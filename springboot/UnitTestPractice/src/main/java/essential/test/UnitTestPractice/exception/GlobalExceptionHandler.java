package essential.test.UnitTestPractice.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import essential.test.UnitTestPractice.dto.response.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.toList());

        BaseResponse<?> response = new BaseResponse<>(false, "Validation failed", errors);
        return ResponseEntity.badRequest().body(response);
    }
}
