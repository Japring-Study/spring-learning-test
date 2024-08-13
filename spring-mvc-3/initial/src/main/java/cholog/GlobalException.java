package cholog;

import cholog.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalException {
        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<String> handleException(NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
}
