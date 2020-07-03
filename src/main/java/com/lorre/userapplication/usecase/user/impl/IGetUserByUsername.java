package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.exception.user.UserNotFoundException;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.GetUserByUsername;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IGetUserByUsername implements GetUserByUsername {

    private final UserRepository userRepository;

    @Override
    public UserEntity get(String username) {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found."));
    }
}
