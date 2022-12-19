package sideProject.moneyTracker.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDetails {
    private long id;
    private String email;
    private String first_name;
    private String password;
}
