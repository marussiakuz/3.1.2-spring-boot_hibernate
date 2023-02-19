package kata.academy.project.service;

import kata.academy.project.model.UserInDto;
import kata.academy.project.model.UserOutDto;

import java.util.List;

public interface UserService {

    List<UserOutDto> getAll(int pageNumber, int pageSize);

    Long getTotalCount();

    UserOutDto create(UserInDto user);

    UserOutDto update(long id, UserInDto user);

    void delete(long id);
}
