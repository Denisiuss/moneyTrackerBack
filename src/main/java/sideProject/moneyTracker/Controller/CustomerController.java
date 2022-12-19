package sideProject.moneyTracker.Controller;

import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sideProject.moneyTracker.Beans.Customers;
import sideProject.moneyTracker.Beans.Payments;
import sideProject.moneyTracker.Beans.UserDetails;
import sideProject.moneyTracker.Exceptoins.CustomerException;
import sideProject.moneyTracker.Login.LoginManager;
import sideProject.moneyTracker.Services.CustomerService;
import sideProject.moneyTracker.Utils.JWTUtil;

import java.sql.SQLException;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    CustomerService customerService;

    private final JWTUtil jwtUtil;
    @Autowired
    private LoginManager loginManager;
    private long customerId;

    private HttpHeaders getHeaders(String token) {
        UserDetails userDetails = new UserDetails();
        userDetails.setId(customerId);
        userDetails.setEmail(jwtUtil.extractEmail(token));
        userDetails.setFirst_name(customerService.getCustomerName(userDetails.getEmail()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", jwtUtil.generateToken(userDetails));
        return httpHeaders;
    }

    @PostMapping("Login")
    private ResponseEntity<?> userLogin (@RequestBody UserDetails userDetails) throws SQLException, InterruptedException {
        customerService = (CustomerService) loginManager.login(userDetails.getEmail(), userDetails.getPassword());
            if (customerService != null) {
                userDetails.setId(customerId=customerService.getCustomerId(userDetails.getEmail()));
                userDetails.setFirst_name(customerService.getCustomerName(userDetails.getEmail()));
                String token = jwtUtil.generateToken(userDetails);
                return ResponseEntity.accepted().header(jwtUtil.extractEmail(token)).body(token);
            }
        return new ResponseEntity<>("Incorrect login", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("addPayment")
    public ResponseEntity<?> addPayment(@RequestHeader(name = "Authorization") String token, @RequestBody Payments payments) throws CustomerException {
        if(jwtUtil.validateToken(token)){
            customerService.addPayment(payments);
        }
        System.out.println(getHeaders(token));
        return ResponseEntity.ok().headers(getHeaders(token)).body("successfully added");
    }

    @GetMapping("getOnlyPayment")
    private ResponseEntity<?> getOnlyPayment() throws CustomerException{
        return new ResponseEntity<>(customerService.getCustomerOnlyPayments(), HttpStatus.OK);
    }

    @GetMapping("getOnlyIncomes")
    private ResponseEntity<?> getOnlyIncomes() throws CustomerException{
        return new ResponseEntity<>(customerService.getCustomerOnlyIncomes(), HttpStatus.OK);
    }

    @GetMapping("getLastPayment")
    private ResponseEntity<?> getLastPayment() throws CustomerException{
        return new ResponseEntity<>(customerService.getLastPayment(), HttpStatus.OK);
    }

    @GetMapping("getLastIncome")
    private ResponseEntity<?> getLastIncome() throws CustomerException{
        return new ResponseEntity<>(customerService.getLastIncome(), HttpStatus.OK);
    }

    @GetMapping("getLastSixMonthIncomes")
    private ResponseEntity<?> getLastSixMonthIncomes() throws CustomerException{
        return new ResponseEntity<>(customerService.getLastSixMonthsIncomes(), HttpStatus.OK);
    }

    @GetMapping("getTodayIncomes")
    private ResponseEntity<?> todayIncomes() throws CustomerException{
        return new ResponseEntity<>(customerService.getTodayIncomes(), HttpStatus.OK);
    }

    @GetMapping("getWeekIncomes")
    private ResponseEntity<?> weekIncomes() {
        return new ResponseEntity<>(customerService.getWeekIncomes(), HttpStatus.OK);
    }

    @GetMapping("getMonthIncomes")
    private ResponseEntity<?> monthIncomes() {
        return new ResponseEntity<>(customerService.getMonthIncomes(), HttpStatus.OK);
    }

    @GetMapping("getCustomerPayments")
    private ResponseEntity<?> allPayments() {
        return new ResponseEntity<>(customerService.getCustomerPayments(), HttpStatus.OK);
    }

    @GetMapping("getCustomerTarget")
    private ResponseEntity<?> target() {
        return new ResponseEntity<>(customerService.getCustomerTarget(), HttpStatus.OK);
    }

    @GetMapping("getCustomerBalance")
    private ResponseEntity<?> balance() {
        return new ResponseEntity<>(customerService.getCustomerBalance(), HttpStatus.OK);
    }

    @GetMapping("getCustomerData")
    private ResponseEntity<?> customerData() {
        return new ResponseEntity<>(customerService.getCustomer(), HttpStatus.OK);
    }

    @PostMapping("updateBalance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void updateBalance(@RequestBody Customers customers) {
        customerService.updateCustomerBalance(customers);
    }



}
