package com.voronkov.vaccination.service.Impl;

import com.voronkov.vaccination.model.Role;
import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.repository.UserRepository;
import com.voronkov.vaccination.service.UserService;
import com.voronkov.vaccination.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        log.info("register {}", user);
        ValidationUtil.checkNew(user);
        user.setRoles(Set.of(Role.USER));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
        log.info("update {}", user);
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User '" + id + "' was not found"));
        log.info("User: {}", user);
        log.info("User find successfully by id: {}", id);

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException("User '" + email + "' was not found"));
        log.info("User: {}", user);
        log.info("User find successfully by email: {}", email);

        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("user with id: {} successfully deleted", id);
    }
}
