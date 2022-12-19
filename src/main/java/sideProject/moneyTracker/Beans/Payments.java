package sideProject.moneyTracker.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Scope("prototype")
@Entity
@Table(name = "payments")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_id")
    private long customerId;

    @Enumerated(EnumType.STRING)
    private Categories categories;

    private String title;
    private int amount;
    private Date purchase_date;
    private String receipt;
}
