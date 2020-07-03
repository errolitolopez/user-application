package com.lorre.userapplication.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateUserForm {
    @NotEmpty
    @Length(min = 2, max = 70)
    private String name;

    @NotEmpty
    @Length(min = 6, max = 256)
    private String username;

    @NotEmpty
    @Length(min = 6, max = 256)
    @JsonProperty(value = "confirm_username")
    private String confirmUsername;

    @NotEmpty
    @Length(min = 8, max = 256)
    private String password;

    @NotEmpty
    @Length(min = 8, max = 256)
    @JsonProperty(value = "confirm_password")
    private String confirmPassword;

    @NotEmpty
    @Length(min = 5, max = 256)
    private String email;

    @NotEmpty
    @Length(min = 5, max = 256)
    @JsonProperty(value = "confirm_email")
    private String confirmEmail;

    @JsonProperty(value = "mobile_phone")
    private String mobilePhone;
}
