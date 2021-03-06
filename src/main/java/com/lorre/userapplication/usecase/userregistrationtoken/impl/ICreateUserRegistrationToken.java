package com.lorre.userapplication.usecase.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.repository.userregistrationtoken.UserRegistrationTokenRepository;
import com.lorre.userapplication.usecase.userregistrationtoken.CreateUserRegistrationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ICreateUserRegistrationToken implements CreateUserRegistrationToken {

    private final UserRegistrationTokenRepository userRegistrationTokenRepository;

    @Override
    public UUID create(UserRegistrationTokenEntity userRegistrationTokenEntity) {
        return userRegistrationTokenRepository.save(userRegistrationTokenEntity).getId();
    }
}
