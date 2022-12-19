package sideProject.moneyTracker.Beans;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Scope("prototype")
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private long balance;
    private long target;

    @Singular
    @OneToMany (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Payments> payments;


}
