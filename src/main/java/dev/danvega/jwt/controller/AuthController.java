package dev.danvega.jwt.controller;

import dev.danvega.jwt.model.LoginRequest;
import dev.danvega.jwt.record.User;
import dev.danvega.jwt.service.TokenService;
import dev.danvega.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody LoginRequest userLogin) throws AuthenticationException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        return new ResponseEntity<>(tokenService.generateToken(authentication), HttpStatus.CREATED);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws AuthenticationException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

}
