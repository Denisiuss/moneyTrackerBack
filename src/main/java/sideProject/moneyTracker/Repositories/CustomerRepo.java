package sideProject.moneyTracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sideProject.moneyTracker.Beans.Customers;

import java.util.ArrayList;

@Repository
public interface CustomerRepo extends JpaRepository <Customers, Long> {

    Customers findByEmailAndPassword(String email, String password);
    Customers findByEmail (String email);
    ArrayList<Customers> findAll();
    Customers findById(long id);

}
