package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IUpdateUser implements UpdateUser {

    private final UserRepository userRepository;

    @Override
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
