package fr.eni.demowebservice.handlers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<String> capturMethodArgumentNotValidException( MethodArgumentNotValidException e ) {

        String errorMsg = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(ex -> ex != null && !ex.isBlank())
                .reduce("Error(s) : ", (a, b) -> a + " - " + b);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMsg);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<String> captureException( Exception e ) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getLocalizedMessage());
    }

}
