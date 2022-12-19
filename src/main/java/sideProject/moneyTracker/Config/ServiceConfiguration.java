package sideProject.moneyTracker.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sideProject.moneyTracker.Services.CustomerService;
import sideProject.moneyTracker.Services.GuestService;

@Configuration
public class ServiceConfiguration {
    @Bean
    public CustomerService initialiseCustomerService() {
        return new CustomerService();
    }
}
