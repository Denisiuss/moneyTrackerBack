package sideProject.moneyTracker.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sideProject.moneyTracker.Exceptoins.GuestException;

@RestController
@ControllerAdvice
public class GuestRestException {
    @ExceptionHandler(value = {GuestException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleException(Exception error){
        return new ErrorDetail("Something somewhere went terrible wrong --- CUSTOMER EDITION", error.getMessage());
    }
}
