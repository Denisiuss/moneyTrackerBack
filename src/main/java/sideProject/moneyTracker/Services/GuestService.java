package sideProject.moneyTracker.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sideProject.moneyTracker.Beans.Customers;
import sideProject.moneyTracker.Repositories.CustomerRepo;
import sideProject.moneyTracker.Repositories.GuestRepo;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class GuestService{
    private final GuestRepo guestRepo;

    /**
     * this method add customer in MYSQL server
     * @param customer
     */
    public void addCustomer (Customers customer){
        ArrayList<Customers> customers = guestRepo.findAll();
        for (Customers item: customers){
            if (item.getEmail().equals(customer.getEmail())){
                System.out.println("Customer with this email is already exists");
                return;
            }
        }
        guestRepo.save(customer);
        System.out.println("customer " + customer.getFirst_name() + " successfully added");
    }



}
