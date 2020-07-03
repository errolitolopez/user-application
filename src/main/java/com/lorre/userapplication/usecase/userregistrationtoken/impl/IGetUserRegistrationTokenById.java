package com.lorre.userapplication.usecase.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.exception.userregistrationtoken.UserRegistrationTokenNotFoundException;
import com.lorre.userapplication.repository.userregistrationtoken.UserRegistrationTokenRepository;
import com.lorre.userapplication.usecase.userregistrationtoken.GetUserRegistrationTokenById;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IGetUserRegistrationTokenById implements GetUserRegistrationTokenById {

    private final UserRegistrationTokenRepository userRegistrationTokenRepository;

    @Override
    public UserRegistrationTokenEntity get(UUID userRegistrationTokenById) {
        return userRegistrationTokenRepository.findById(userRegistrationTokenById)
                .orElseThrow( () -> new UserRegistrationTokenNotFoundException("User registration token not found."));
    }
}
