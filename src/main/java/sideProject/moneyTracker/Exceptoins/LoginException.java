package sideProject.moneyTracker.Exceptoins;

public class LoginException extends Exception{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
