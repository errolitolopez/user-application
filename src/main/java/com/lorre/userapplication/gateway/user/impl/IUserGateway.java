package com.lorre.userapplication.gateway.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.exception.user.UserNotCreatedException;
import com.lorre.userapplication.exception.user.UserNotFoundException;
import com.lorre.userapplication.exception.user.UserNotUpdatedException;
import com.lorre.userapplication.gateway.user.UserGateway;
import com.lorre.userapplication.mapper.user.UserMapper;
import com.lorre.userapplication.model.user.User;
import com.lorre.userapplication.usecase.user.CreateUser;
import com.lorre.userapplication.usecase.user.GetUserByEmail;
import com.lorre.userapplication.usecase.user.GetUserById;
import com.lorre.userapplication.usecase.user.GetUserByUsername;
import com.lorre.userapplication.usecase.user.GetUsers;
import com.lorre.userapplication.usecase.user.UpdateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class IUserGateway implements UserGateway {

    private final UserMapper userMapper;
    private final GetUsers getUsers;
    private final GetUserById getUserById;
    private final GetUserByEmail getUserByEmail;
    private final GetUserByUsername getUserByUsername;
    private final CreateUser createUser;
    private final UpdateUser updateUser;

    @Override
    public List<User> getUsers() {
        return getUsers.get()
                .stream()
                .map(userMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(User user) {
        UserEntity userEntity = userMapper.mapModelToEntity(user);

        boolean isEmailExisting = true;
        boolean isUsernameExisting = true;

        try {
            getUserByEmail.get(user.getEmail());
        } catch (UserNotFoundException e) {
            log.info("{}", e.getMessage());
            isEmailExisting = false;
        }

        try {
            getUserByUsername.get(user.getUsername());
        } catch (UserNotFoundException e) {
            log.info("{}", e.getMessage());
            isUsernameExisting = false;
        }

        if (isEmailExisting) {
            throw new UserNotCreatedException("User with email: " + user.getEmail() + " is already existing.");
        }

        if (isUsernameExisting) {
            throw new UserNotCreatedException("User with username: " + user.getUsername() + " is already existing.");
        }
        return createUser.create(userEntity);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.mapEntityToModel(getUserById.get(id));
    }

    @Override
    public void changeUsername(User user) {
        try {
            getUserByUsername.get(user.getUsername());
        } catch (UserNotFoundException e) {
            log.info("{}", e.getMessage());
            updateUser.update(userMapper.mapModelToEntity(user));
            return;
        }
        throw new UserNotUpdatedException("User with username: " + user.getUsername() + " is already existing.");
    }

    @Override
    public void changeEmail(User user) {
        try {
            getUserByEmail.get(user.getEmail());
        } catch (UserNotFoundException e) {
            log.info("{}", e.getMessage());
            updateUser.update(userMapper.mapModelToEntity(user));
            return;
        }
        throw new UserNotUpdatedException("User with email: " + user.getEmail() + " is already existing.");
    }

    @Override
    public void changePassword(User user) {
        updateUser.update(userMapper.mapModelToEntity(user));
    }

    @Override
    public void updateUser(User user) {
        updateUser.update(userMapper.mapModelToEntity(user));
    }
}
