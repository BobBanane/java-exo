package fr.eni.cave_a_vin.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    private final View error;

    public AppExceptionHandler( View error ) {
        this.error = error;
    }

    @ExceptionHandler( value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String, String>> capturMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        Map<String, String> errorsMsgList = new HashMap<>();

        ex.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null && !e.getDefaultMessage().isBlank())
                .forEach(e -> errorsMsgList.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorsMsgList);
    }

    @ExceptionHandler( value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<String > capturMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getLocalizedMessage());
    }

}
