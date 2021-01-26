package com.voronkov.vaccination.service.Impl;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.repository.UserRepository;
import com.voronkov.vaccination.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
//        Role roleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(userRoles);
//        user.setStatus(Status.ACTIVE);
//
//        User registerUser = userRepository.save(user);
//
//        log.info("IN register - user: {} successfully registered", registerUser);
//
//        return registerUser;
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    @Override
    public User findByEmail(String email) {
//        User result = userRepository.findByEmailIgnoreCase(email);
//
//        log.info("IN findByEmail - user: {} found by email: {}", result, email);
//        return result;
        return null;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - user: {} found by id: {}", result, id);
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
