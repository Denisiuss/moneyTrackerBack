package sideProject.moneyTracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sideProject.moneyTracker.Beans.Customers;

import java.util.ArrayList;

public interface GuestRepo extends JpaRepository<Customers, Long> {
    ArrayList<Customers> findAll();
}
