package com.lorre.userapplication.service.user.impl;

import com.lorre.userapplication.exception.user.EmailNotMatchException;
import com.lorre.userapplication.exception.user.PasswordNotMatchException;
import com.lorre.userapplication.exception.user.UserAlreadyActivatedException;
import com.lorre.userapplication.exception.user.UserNotUpdatedException;
import com.lorre.userapplication.exception.user.UsernameNotMatchException;
import com.lorre.userapplication.exception.userregistrationtoken.UserRegistrationTokenInvalidException;
import com.lorre.userapplication.gateway.user.UserGateway;
import com.lorre.userapplication.gateway.userregistrationtoken.UserRegistrationTokenGateway;
import com.lorre.userapplication.mapper.user.UserMapper;
import com.lorre.userapplication.model.user.User;
import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;
import com.lorre.userapplication.request.ChangeEmailForm;
import com.lorre.userapplication.request.ChangePasswordForm;
import com.lorre.userapplication.request.ChangeUsernameForm;
import com.lorre.userapplication.request.CreateUserForm;
import com.lorre.userapplication.resource.UserResource;
import com.lorre.userapplication.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class IUserService implements UserService {

    private final UserGateway userGateway;
    private final UserMapper userMapper;
    private final UserRegistrationTokenGateway userRegistrationTokenGateway;
    private final JavaMailSender javaMailSender;


    @Override
    public List<UserResource> getUsers() {
        return userGateway.getUsers()
                .stream()
                .map(userMapper::mapModelToResource)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(CreateUserForm createUserForm) {
        if (!createUserForm.getEmail().equals(createUserForm.getConfirmEmail())) {
            throw new EmailNotMatchException("The email and it's confirmation do not match.");
        }

        if (!createUserForm.getUsername().equals(createUserForm.getConfirmUsername())) {
            throw new UsernameNotMatchException("The username and it's confirmation do not match.");
        }

        if (!createUserForm.getPassword().equals(createUserForm.getConfirmPassword())) {
            throw new PasswordNotMatchException("The password and it's confirmation do not match.");
        }

        User user = userMapper.mapCreateUserFormToUser(createUserForm);
        Long userId = userGateway.createUser(user);

        UserRegistrationToken userRegistrationToken = new UserRegistrationToken();
        userRegistrationToken.setCreationDate(LocalDateTime.now());
        userRegistrationToken.setExpired(false);
        userRegistrationToken.setUserId(userId);
        UUID userRegistrationTokenId = userRegistrationTokenGateway.createUserRegistrationToken(userRegistrationToken);
        sendRegistrationMail(user.getEmail(), userId, userRegistrationTokenId);
        return userId;
    }

    @Override
    public UserResource getUserById(Long id) {
        return userMapper.mapModelToResource(userGateway.getUserById(id));
    }

    @Override
    public void changeUsername(Long id, ChangeUsernameForm changeUsernameForm) {
        User user = userGateway.getUserById(id);

        if (changeUsernameForm.getNewUsername().equals(user.getUsername())) {
            throw new UserNotUpdatedException("You cannot use your current username.");
        }

        if (!changeUsernameForm.getNewUsername().equals(changeUsernameForm.getConfirmNewUsername())) {
            throw new UsernameNotMatchException("The new username and it's confirmation do not match.");
        }

        if (!changeUsernameForm.getPassword().equals(user.getPassword())) {
            throw new PasswordNotMatchException("Incorrect password.");
        }

        user.setUsername(changeUsernameForm.getNewUsername());
        userGateway.changeUsername(user);
    }

    @Override
    public void changeEmail(Long id, ChangeEmailForm changeEmailForm) {
        User user = userGateway.getUserById(id);

        if (changeEmailForm.getNewEmail().equals(user.getEmail())) {
            throw new UserNotUpdatedException("You cannot use your current email.");
        }

        if (!changeEmailForm.getNewEmail().equals(changeEmailForm.getConfirmNewEmail())) {
            throw new EmailNotMatchException("The new email and it's confirmation do not match.");
        }

        if (!changeEmailForm.getPassword().equals(user.getPassword())) {
            throw new PasswordNotMatchException("Incorrect password.");
        }

        user.setEmail(changeEmailForm.getNewEmail());
        userGateway.changeEmail(user);
    }

    @Override
    public void changePassword(Long id, ChangePasswordForm changePasswordForm) {
        User user = userGateway.getUserById(id);
        if (changePasswordForm.getNewPassword().equals(user.getPassword())) {
            throw new UserNotUpdatedException("You cannot use your current password.");
        }

        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmNewPassword())) {
            throw new PasswordNotMatchException("The new password and it's confirmation do not match.");
        }

        if (!changePasswordForm.getPassword().equals(user.getPassword())) {
            throw new PasswordNotMatchException("Incorrect password.");
        }

        user.setPassword(changePasswordForm.getNewPassword());
        userGateway.changePassword(user);
    }

    @Override
    public void activateAccount(Long userId, UUID userRegistrationTokenId) {
        User user = userGateway.getUserById(userId);

        if (user.isActive()) {
            throw new UserAlreadyActivatedException("User already activated.");
        }

        UserRegistrationToken userRegistrationToken =
                userRegistrationTokenGateway.getUserRegistrationTokenById(userRegistrationTokenId);

        if (!user.getId().equals(userRegistrationToken.getUserId())) {
            throw new UserRegistrationTokenInvalidException("Invalid registration token with id: " + user.getId());
        }

        user.setActive(true);
        userGateway.updateUser(user);
        userRegistrationToken.setExpired(true);
        userRegistrationTokenGateway.updateUserRegistrationToken(userRegistrationToken);
    }

    private void sendRegistrationMail(String email, Long userId, UUID userRegistrationTokenId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Activate User Application Account]");
        message.setText("To activate your account click this link. http://localhost:8080/user/activate-account/" + userId + "/" + userRegistrationTokenId);
        javaMailSender.send(message);
    }
}
