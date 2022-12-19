package sideProject.moneyTracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import sideProject.moneyTracker.Repositories.CustomerRepo;
import sideProject.moneyTracker.Repositories.PaymentsRepo;


public abstract class ClientService {

    @Autowired
    protected PaymentsRepo paymentsRepo;
    @Autowired
    protected CustomerRepo customerRepo;


    public abstract boolean login(String email, String password);
}
