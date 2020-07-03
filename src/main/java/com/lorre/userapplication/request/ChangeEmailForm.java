package com.lorre.userapplication.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@JsonDeserialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ChangeEmailForm {
    @NotEmpty
    private String password;

    @NotEmpty
    @Length(min = 5, max = 256)
    @JsonProperty(value = "new_email")
    private String newEmail;

    @NotEmpty
    @Length(min = 5, max = 256)
    @JsonProperty(value = "confirm_new_email")
    private String confirmNewEmail;
}
