package com.lorre.userapplication.gateway.user;

import com.lorre.userapplication.model.user.User;

import java.util.List;

public interface UserGateway {

    List<User> getUsers();

    Long createUser(User user);

    User getUserById(Long id);

    void changeUsername(User user);

    void changeEmail(User user);

    void changePassword(User user);

    void updateUser(User user);
}
