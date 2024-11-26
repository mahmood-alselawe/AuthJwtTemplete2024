package com.takarub.AuthJwtTemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisteredRequest {

    // here we should to valid like notNull Email Password all thank
    private String firstName;

    private String lastName;

    private String email;

    private String passWord;
}
