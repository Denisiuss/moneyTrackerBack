package sideProject.moneyTracker.Advice;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class JwtRestExceptions {
    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetail handleException(Exception error){
        return new ErrorDetail("Invalid Token", error.getMessage());
    }
}
