package com.lorre.userapplication.usecase.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.repository.userregistrationtoken.UserRegistrationTokenRepository;
import com.lorre.userapplication.usecase.userregistrationtoken.GetUserRegistrationTokensByUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IGetUserRegistrationTokensByUserId implements GetUserRegistrationTokensByUserId {

    private final UserRegistrationTokenRepository userRegistrationTokenRepository;

    @Override
    public List<UserRegistrationTokenEntity> get(Long userId) {
        return userRegistrationTokenRepository.findUserRegistrationTokenEntityByUserId(userId);
    }
}
