package co.com.java.api.handlingexception;

import co.com.java.api.request.ResponseRequest;
import co.com.java.usecase.exceptions.InsufficientStockException;
import io.micrometer.core.ipc.http.HttpSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseRequest> custom(RuntimeException ex) {

        ResponseRequest response = ResponseRequest.builder()
                .responseDescription(ex.getMessage())
                .resultCode("BAD_REQUEST")
                .date(LocalDateTime.now().toString())
                .result(Collections.emptyList())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {InsufficientStockException.class})
    public ResponseEntity<ResponseRequest> custom(InsufficientStockException ex) {
        ResponseRequest response = ResponseRequest.builder()
                .responseDescription(ex.getMessage())
                .resultCode("UNPROCESSABLE_ENTITY")
                .date(LocalDateTime.now().toString())
                .result(Collections.emptyList())
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

}


