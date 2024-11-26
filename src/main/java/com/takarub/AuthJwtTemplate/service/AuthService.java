package com.takarub.AuthJwtTemplate.service;

import com.takarub.AuthJwtTemplate.dto.AuthenticationResponse;
import com.takarub.AuthJwtTemplate.dto.LoginRequest;
import com.takarub.AuthJwtTemplate.dto.RegisteredRequest;
import com.takarub.AuthJwtTemplate.model.Role;
import com.takarub.AuthJwtTemplate.model.Token;
import com.takarub.AuthJwtTemplate.model.TokenType;
import com.takarub.AuthJwtTemplate.model.User;
import com.takarub.AuthJwtTemplate.repository.TokenRepository;
import com.takarub.AuthJwtTemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public AuthenticationResponse register(RegisteredRequest request) {
        User build = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passWord(passwordEncoder.encode(request.getPassWord()))
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(build);

        String jwtToken = jwtServices.generateToken(build);

        Date issuedAt = jwtServices.extractIssuedAt(jwtToken);
        Date expirationTime = jwtServices.extractExpiration(jwtToken);


        saveUserToken(jwtToken, savedUser);


        String issuedAtStr = formatDate(issuedAt);
        String expirationTimeStr = formatDate(expirationTime);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .issuedAt(issuedAtStr)
                .expirationTime(expirationTimeStr)
                .build();
    }



    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassWord()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtServices.generateToken(user);


        Date issuedAt = jwtServices.extractIssuedAt(jwtToken);
        Date expirationTime = jwtServices.extractExpiration(jwtToken);
        revokeAllUserToken(user);
        saveUserToken(jwtToken,user);

        String issuedAtStr = formatDate(issuedAt);
        String expirationTimeStr = formatDate(expirationTime);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .issuedAt(issuedAtStr)
                .expirationTime(expirationTimeStr)
                .build();
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(date);
    }
    private void saveUserToken(String jwtToken, User savedUser) {
        var token = Token
                .builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .user(savedUser)
                .build();

        tokenRepository.save(token);
    }

    private void revokeAllUserToken(User user){
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserToken == null)
            return;
        validUserToken.forEach(t->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }
}
