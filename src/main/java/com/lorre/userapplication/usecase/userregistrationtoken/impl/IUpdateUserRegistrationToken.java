package com.lorre.userapplication.usecase.userregistrationtoken.impl;

import com.lorre.userapplication.entity.userregistrationtoken.UserRegistrationTokenEntity;
import com.lorre.userapplication.repository.userregistrationtoken.UserRegistrationTokenRepository;
import com.lorre.userapplication.usecase.userregistrationtoken.UpdateUserRegistrationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IUpdateUserRegistrationToken implements UpdateUserRegistrationToken {

    private final UserRegistrationTokenRepository userRegistrationTokenRepository;

    @Override
    public void update(UserRegistrationTokenEntity userRegistrationTokenEntity) {
        userRegistrationTokenRepository.save(userRegistrationTokenEntity);
    }
}
