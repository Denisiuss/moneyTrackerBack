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
    @Query(value = "SELECT * FROM payments WHERE CATEGORIES = :categories AND customer_id = :customerId order by purchase_date desc Limit 1", nativeQuery = true)
    ArrayList<Payments> findLastPayment (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value = "SELECT * FROM payments WHERE CATEGORIES = :categories AND customer_id = :customerId order by purchase_date desc Limit 1", nativeQuery = true)
    ArrayList<Payments> findLastIncome (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "SELECT date_trunc('month', purchase_date) as purchase_date, sum(amount) as amount, max(id) as id, categories, sum(customer_id) as customer_id, max(receipt) as receipt, max(title) as title\n\n" +
                    "from payments where customer_id = :customerId AND (purchase_date >= CURRENT_DATE - INTERVAL '6 months') AND  categories= :categories  \n" +
                    "group by 1, 4\n" +
                    "order by 1;", nativeQuery = true)
    ArrayList<Payments> findLastSixMonthsIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "SELECT date_trunc('month', purchase_date) as purchase_date, sum(amount) as amount, max(id) as id, categories, sum(customer_id) as customer_id, max(receipt) as receipt, max(title) as title\n" +
            "from payments where customer_id = :customerId AND (purchase_date >= CURRENT_DATE - INTERVAL '1 day') AND  categories= :categories  \n" +
            "group by 1, 4;", nativeQuery = true)
    ArrayList<Payments> findTodayIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "SELECT date_trunc('month', purchase_date) as purchase_date, sum(amount) as amount, max(id) as id, categories, sum(customer_id) as customer_id, max(receipt) as receipt, max(title) as title\n" +
            "from payments where customer_id = :customerId AND (purchase_date >= CURRENT_DATE - INTERVAL '1 week') AND  categories= :categories  \n" +
            "group by 1, 4;", nativeQuery = true)
    ArrayList<Payments> findWeekIncomes (@Param("customerId") long customerId, @Param("categories") String categories);

    @Transactional
    @Modifying (clearAutomatically = true)
    @Query(value =  "SELECT date_trunc('month', purchase_date) as purchase_date, sum(amount) as amount, max(id) as id, categories, sum(customer_id) as customer_id, max(receipt) as receipt, max(title) as title\n" +
            "from payments where customer_id = :customerId AND (purchase_date >= CURRENT_DATE - INTERVAL '1 Month') AND  categories= :categories  \n" +
            "group by 1, 4;", nativeQuery = true)
    ArrayList<Payments> findMonthIncomes (@Param("customerId") long customerId, @Param("categories") String categories);
}
