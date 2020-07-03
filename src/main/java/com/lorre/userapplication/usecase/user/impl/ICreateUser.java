package com.lorre.userapplication.usecase.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.CreateUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ICreateUser implements CreateUser {

	private final UserRepository userRepository;

	@Override
	public Long create(UserEntity userEntity) {
		UserEntity createdUser = userRepository.save(userEntity);
		return createdUser.getId();
	}
}
