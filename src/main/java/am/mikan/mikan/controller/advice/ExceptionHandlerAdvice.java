package am.mikan.mikan.controller.advice;

import am.mikan.mikan.Exception.EntityNotFoundException;
import am.mikan.mikan.Exception.FileNotExistException;
import am.mikan.mikan.Exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            EntityNotFoundException.class,
            FileNotExistException.class,
            UserNotFoundException.class,
            RuntimeException.class,
            Exception.class
    })
    public String handleEntityNotFoundException() {
        return "view/404";
    }
}
