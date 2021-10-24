package demo.test.common.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHelper {

    @ExceptionHandler({APIException.class})
    public ResponseEntity<Object> handleUserNotFoundException(APIException exception) {

        return exception.getResponse();
    }

}