package com.lorre.userapplication.usecase.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorre.userapplication.entity.user.UserEntity;
import com.lorre.userapplication.exception.user.UserNotFoundException;
import com.lorre.userapplication.repository.user.UserRepository;
import com.lorre.userapplication.usecase.user.GetUserById;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class IGetUserById implements GetUserById {

	private final UserRepository userRepository;

	@Override
	public UserEntity get(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with id: " + id));
	}
}
