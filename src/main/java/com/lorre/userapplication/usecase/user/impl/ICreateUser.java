package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.CreateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ICreateUser implements CreateUser {

    private final UserRepository userRepository;

    @Override
    public Long create(UserEntity userEntity) {
        UserEntity createdUser = userRepository.save(userEntity);
        return createdUser.getId();
    }
}
