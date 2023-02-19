package kata.academy.project.service;

import kata.academy.project.error.UserNotFoundException;
import kata.academy.project.model.User;
import kata.academy.project.model.UserInDto;
import kata.academy.project.model.UserOutDto;
import kata.academy.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserOutDto> getAll(int pageNumber, int pageSize) {
        Page<User> all = userRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return userMapper.toDto(all);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getTotalCount() {
        return userRepository.count();
    }

    @Transactional
    @Override
    public UserOutDto create(UserInDto user) {
        return userMapper.toDto(userRepository.save(userMapper.toUser(user)));
    }

    @Transactional
    @Override
    public UserOutDto update(long id, UserInDto userDto) {
        User user = getByIdOrThrowException(id);
        userMapper.updateUserFromDto(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.delete(getByIdOrThrowException(id));
    }

    private User getByIdOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("user with id=%d not found", id)));
    }
}
