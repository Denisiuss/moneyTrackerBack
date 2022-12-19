package sideProject.moneyTracker.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sideProject.moneyTracker.Beans.Customers;
import sideProject.moneyTracker.Beans.Payments;
import sideProject.moneyTracker.Services.GuestService;

@RestController
@RequestMapping("guest")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuestController {

    private final GuestService guestService;

    @PostMapping("addNewCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customers customers) {
        guestService.addCustomer(customers);
    }
}
