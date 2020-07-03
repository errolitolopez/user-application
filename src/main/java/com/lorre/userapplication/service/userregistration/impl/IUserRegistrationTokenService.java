package com.lorre.userapplication.service.userregistration.impl;

import com.lorre.userapplication.exception.user.UserAlreadyActivatedException;
import com.lorre.userapplication.gateway.user.UserGateway;
import com.lorre.userapplication.gateway.userregistrationtoken.UserRegistrationTokenGateway;
import com.lorre.userapplication.model.user.User;
import com.lorre.userapplication.model.userregistrationtoken.UserRegistrationToken;
import com.lorre.userapplication.service.userregistration.UserRegistrationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IUserRegistrationTokenService implements UserRegistrationTokenService {

    private final UserRegistrationTokenGateway userRegistrationTokenGateway;
    private final UserGateway userGateway;
    private final JavaMailSender javaMailSender;

    @Override
    public void resendUserRegistrationToken(Long id) {
        User user = userGateway.getUserById(id);

        if (user.isActive()) {
            throw new UserAlreadyActivatedException("User already activated.");
        }

        List<UserRegistrationToken> userRegistrationTokens
                = userRegistrationTokenGateway.getUserRegistrationTokensByUserId(id);

        if (!userRegistrationTokens.isEmpty()) {
            userRegistrationTokens
                    .forEach(userRegistrationToken -> {
                        userRegistrationToken.setExpired(true);
                        userRegistrationTokenGateway.updateUserRegistrationToken(userRegistrationToken);
                    });
        }

        UserRegistrationToken userRegistrationToken = new UserRegistrationToken();
        userRegistrationToken.setExpired(false);
        userRegistrationToken.setCreationDate(LocalDateTime.now());
        userRegistrationToken.setUserId(user.getId());
        UUID userRegistrationTokenId = userRegistrationTokenGateway.createUserRegistrationToken(userRegistrationToken);
        resendRegistrationActivationMail(user.getEmail(), user.getId(), userRegistrationTokenId);
    }

    private void resendRegistrationActivationMail(String email, Long userId, UUID userRegistrationTokenId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[Activate User Application Account]");
        message.setText("To activate your account click this link. http://localhost:8080/user/activate-account/" + userId + "/" + userRegistrationTokenId);
        javaMailSender.send(message);
    }
}
