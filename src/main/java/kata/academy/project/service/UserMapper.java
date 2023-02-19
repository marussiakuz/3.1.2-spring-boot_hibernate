package kata.academy.project.service;

import kata.academy.project.model.User;
import kata.academy.project.model.UserInDto;
import kata.academy.project.model.UserOutDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserInDto userInDto);

    UserOutDto toDto(User user);

    List<UserOutDto> toDto(Page<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserInDto userInDto, @MappingTarget User user);

    static Date map(Long value) {
        return isNull(value) ? null : new Date(value);
    }
}
