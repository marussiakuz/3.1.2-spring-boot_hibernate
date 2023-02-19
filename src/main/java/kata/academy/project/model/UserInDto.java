package kata.academy.project.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long birthday;
    private Role role;
}
