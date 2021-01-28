package com.voronkov.vaccination.service;

import com.voronkov.vaccination.model.User;

public interface UserService {

    User register(User user);

    void update(User user);

    User findById(long id);

    User findByEmail(String email);

    void delete(Long id);
}
