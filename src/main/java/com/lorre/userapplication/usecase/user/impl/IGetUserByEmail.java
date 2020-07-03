package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.exception.user.UserNotFoundException;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IGetUserByEmail implements GetUserByEmail {

    private final UserRepository userRepository;

    @Override
    public UserEntity get(String email) {
        return userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No user found with email: " + email));
    }
}
