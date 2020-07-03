package com.lorre.userapplication.usecase.user.impl;

import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.DeleteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IDeleteUser implements DeleteUser {

    private final UserRepository userRepository;

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
