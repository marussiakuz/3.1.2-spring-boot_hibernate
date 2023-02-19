package kata.academy.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter @Setter
public class UserOutDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private Role role;
}
