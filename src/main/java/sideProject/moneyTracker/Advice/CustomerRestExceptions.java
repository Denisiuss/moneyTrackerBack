package sideProject.moneyTracker.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sideProject.moneyTracker.Exceptoins.CustomerException;

@RestController
@ControllerAdvice
public class CustomerRestExceptions {
    @ExceptionHandler(value = {CustomerException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleException(Exception error){
        return new ErrorDetail("Something went wrong --- CUSTOMER EDITION", error.getMessage());
    }
}
