package sideProject.moneyTracker.Services;

import sideProject.moneyTracker.Beans.Categories;
import sideProject.moneyTracker.Beans.Customers;
import sideProject.moneyTracker.Beans.Payments;

import java.util.ArrayList;

public class CustomerService extends ClientService {
    private long customer_id;

    @Override
    public boolean login(String email, String password) {
        try {
            customer_id = customerRepo.findByEmailAndPassword(email, password).getId();
            System.out.println("You have successfully logged in");
            return true;
        }catch (NullPointerException e){
            System.out.println("wrong email or password");
        }
        return false;
    }

    /**
     * this method add new Payment in MYSQL server
     * @param payments
     */
    public void addPayment (Payments payments){
        paymentsRepo.save(payments);
        System.out.println("successfully added");
        Customers customers = customerRepo.findById(customer_id);

        if (payments.getCategories().equals(Categories.Incomes)){
            customers.setBalance(customers.getBalance() + payments.getAmount());
        } else {
            customers.setBalance(customers.getBalance() - payments.getAmount());
        }
        customerRepo.saveAndFlush(customers);
    }

    /**
     * this method gets Customer's Id from MYSQL server
     * @param email
     * @return customer's id
     */
    public long getCustomerId(String email){
        Customers customers = customerRepo.findByEmail(email);
        if (customers.getEmail().equals(email)){
            customer_id = customers.getId();
            return customer_id;
        }
        return customer_id=0;
    }

    /**
     * this method gets Customer's name from MYSQL server
     * @param email
     * @return customer's name
     */
    public String getCustomerName(String email){
        Customers customers = customerRepo.findByEmail(email);
        String customer_name;
        if (customers.getEmail().equals(email)){
            customer_name = customers.getFirst_name();
            return customer_name;
        }
        return customer_name ="";
    }

    /**
     * this method gets Customer's last payment from MYSQL server
     * @return customer's last payment
     */
    public ArrayList<Payments> getLastPayment(){
        return paymentsRepo.findLastPayment(customer_id, Categories.Payments.toString());
    }

    /**
     * this method gets Customer's last income from MYSQL server
     * @return customer's last incomes
     */
    public ArrayList<Payments> getLastIncome(){
        ArrayList<Payments> payments = paymentsRepo.findLastIncome(customer_id, Categories.Incomes.toString());
        return payments;
    }

    /**
     * this method gets Customer's payment from last half year in MYSQL server
     * @return customer's incomes from last six months
     */
    public ArrayList<Payments> getLastSixMonthsIncomes(){
        ArrayList<Payments> payments = paymentsRepo.findLastSixMonthsIncomes(customer_id, Categories.Incomes.toString());
        return payments;
    }

    /**
     * this method gets Customer's incomes from today in MYSQL server
     * @return customer's incomes from today
     */
    public ArrayList<Payments> getTodayIncomes(){
        ArrayList<Payments> payments = paymentsRepo.findTodayIncomes(customer_id, Categories.Incomes.toString());
        return payments;
    }

    /**
     * this method gets Customer's incomes from last Week in MYSQL server
     * @return customer's incomes from last week
     */
    public ArrayList<Payments> getWeekIncomes(){
        ArrayList<Payments> payments = paymentsRepo.findWeekIncomes(customer_id, Categories.Incomes.toString());
        return payments;
    }

    /**
     * this method gets Customer's incomes from last month in MYSQL server
     * @return customer's incomes from last month
     */
    public ArrayList<Payments> getMonthIncomes(){
        ArrayList<Payments> payments = paymentsRepo.findMonthIncomes(customer_id, Categories.Incomes.toString());
        return payments;
    }

    /**
     * this method gets Customer's payments from MYSQL server
     * @return customer's payments
     */
    public ArrayList<Payments> getCustomerPayments(){
        ArrayList<Payments> payments = paymentsRepo.findByCustomerId(customer_id);
        return payments;
    }

    /**
     * this method gets Customer's target in MYSQL server
     * @return customer's target
     */
    public long getCustomerTarget() {
        Customers customers = customerRepo.findById(customer_id);
        return customers.getTarget();
    }

    /**
     * this method gets Customer's balance from MYSQL server
     * @return customer's balance
     */
    public long getCustomerBalance() {
        Customers customers = customerRepo.findById(customer_id);
        return customers.getBalance();
    }

    /**
     * this method gets Customer from MYSQL server
     * @return customer
     */
    public Customers getCustomer() {
        Customers customers = customerRepo.findById(customer_id);
        return customers;
    }

    /**
     * this method updates Customer's balance in MYSQL server
     * @param customers
     */
    public void updateCustomerBalance(Customers customers) {
        customerRepo.saveAndFlush(customers);

    }

    /**
     * this method gets Customer's only payments in MYSQL server
     * @return customer's payments
     */
    public ArrayList<Payments> getCustomerOnlyPayments(){
        ArrayList<Payments> payments = paymentsRepo.findByCustomerIdAndCategories(customer_id, Categories.Payments);
        return payments;
    }

    /**
     * this method gets Customer's only incomes in MYSQL server
     * @return customer's incomes
     */
    public ArrayList<Payments> getCustomerOnlyIncomes(){
        ArrayList<Payments> payments = paymentsRepo.findByCustomerIdAndCategories(customer_id, Categories.Incomes);
        return payments;
    }



}
