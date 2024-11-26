package com.takarub.AuthJwtTemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    // here we should to valid like notNull Email Password all thank
    // also exception for this constrain
    private String email;
    private String passWord;
}
