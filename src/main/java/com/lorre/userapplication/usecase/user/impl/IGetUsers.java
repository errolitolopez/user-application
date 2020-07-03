package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.GetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IGetUsers implements GetUsers {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> get() {
        return userRepository.findAll();
    }
}
