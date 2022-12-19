package sideProject.moneyTracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sideProject.moneyTracker.Beans.Categories;
import sideProject.moneyTracker.Beans.Payments;


import java.util.ArrayList;

@Repository
public interface PaymentsRepo extends JpaRepository <Payments, Long> {
    ArrayList<Payments> findByCustomerId (long customer_id);

    ArrayList<Payments> findByCustomerIdAndCategories (long customer_id, Categories categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value = "SELECT * FROM moneytracker.payments WHERE CATEGORIES = :categories AND customer_id = :customerId order by purchase_date desc Limit 1", nativeQuery = true)
    ArrayList<Payments> findLastPayment (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value = "SELECT * FROM moneytracker.payments WHERE CATEGORIES = :categories AND customer_id = :customerId order by purchase_date desc Limit 1", nativeQuery = true)
    ArrayList<Payments> findLastIncome (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "select sum(amount) as amount, id, categories, purchase_date, customer_id, receipt, title\n" +
                    "from payments where customer_id = :customerId AND `purchase_date` >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) AND  categories= :categories  \n" +
                    "group  by DATE_FORMAT(purchase_date, '%Y %M')\n" +
                    "order by DATE(purchase_date);", nativeQuery = true)
    ArrayList<Payments> findLastSixMonthsIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "select sum(amount) as amount, id, categories, purchase_date, customer_id, receipt, title\n" +
            "from payments where customer_id = :customerId AND `purchase_date` >= DATE_SUB(CURDATE(), INTERVAL 1 day) AND  categories= :categories  \n" +
            "group  by DATE_FORMAT(purchase_date, '%Y %M');", nativeQuery = true)
    ArrayList<Payments> findTodayIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "select sum(amount) as amount, id, categories, purchase_date, customer_id, receipt, title\n" +
            "from payments where customer_id = :customerId AND `purchase_date` >= DATE_SUB(CURDATE(), INTERVAL 1 week) AND  categories= :categories  \n" +
            "group  by DATE_FORMAT(purchase_date, '%Y %M');", nativeQuery = true)
    ArrayList<Payments> findWeekIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "select sum(amount) as amount, id, categories, purchase_date, customer_id, receipt, title\n" +
            "from payments where customer_id = :customerId AND `purchase_date` >= DATE_SUB(CURDATE(), INTERVAL 1 Month) AND  categories= :categories  \n" +
            "group  by DATE_FORMAT(purchase_date, '%Y %M');", nativeQuery = true)
    ArrayList<Payments> findMonthIncomes (@Param("customerId") long customerId, @Param("categories") String categories);
}
