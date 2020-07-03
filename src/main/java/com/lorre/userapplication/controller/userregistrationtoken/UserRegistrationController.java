package com.lorre.userapplication.controller.userregistrationtoken;

import com.lorre.userapplication.service.userregistration.UserRegistrationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserRegistrationController {

    private final UserRegistrationTokenService userRegistrationTokenService;

    @GetMapping(value = "/user/resend-registration-activation/{userId}")
    public void resendUserRegistrationToken(@PathVariable Long userId) {
        userRegistrationTokenService.resendUserRegistrationToken(userId);
    }
}
