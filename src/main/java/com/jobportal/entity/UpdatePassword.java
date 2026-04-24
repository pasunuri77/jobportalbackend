package com.jobportal.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePassword {

    private String email;
    private String newPassword;
}
