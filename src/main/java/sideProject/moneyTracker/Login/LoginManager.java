package sideProject.moneyTracker.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sideProject.moneyTracker.Config.ServiceConfiguration;
import sideProject.moneyTracker.Services.ClientService;
import sideProject.moneyTracker.Services.CustomerService;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class LoginManager {
    private final ServiceConfiguration config;

    public ClientService login(String email, String password) throws SQLException, InterruptedException {
        CustomerService customerService = config.initialiseCustomerService();
        return customerService.login(email, password) ? customerService : null;
    }
}
