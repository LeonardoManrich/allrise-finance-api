package com.allrise.njord.controller;

import com.allrise.njord.entity.UserEntity;
import com.allrise.njord.exception.BadRequestException;
import com.allrise.njord.resource.request.AuthRequest;
import com.allrise.njord.resource.response.AuthResponse;
import com.allrise.njord.service.AuthService;
import com.allrise.njord.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                            authRequest.getPassword())
            );

            UserEntity userEntity = (UserEntity) authentication.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(userEntity);

            AuthResponse authResponse = new AuthResponse(userEntity.getEmail(), accessToken);

            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest authRequest) {
        UserEntity newUser = authService.register(authRequest);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/teste")
    public ResponseEntity<?> teste(){
        return ResponseEntity.ok("teste");
    }
}
