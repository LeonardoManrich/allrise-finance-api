package com.allrise.njord.service.impl;

import com.allrise.njord.entity.UserEntity;
import com.allrise.njord.exception.BadRequestException;
import com.allrise.njord.repository.UserRepository;
import com.allrise.njord.resource.request.AuthRequest;
import com.allrise.njord.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity register(AuthRequest authRequest) {
        String emailForRegister = authRequest.getEmail();
        String passwordForRegister = authRequest.getPassword();

        Optional<UserEntity> optOfUserFindedByEmail = userRepository.findByEmail(emailForRegister);

        if (optOfUserFindedByEmail.isPresent()) {
            throw new BadRequestException("Email already registered");
        }

        String passwordBEncrypted = new BCryptPasswordEncoder().encode(passwordForRegister);

        UserEntity newUser = new UserEntity();
        newUser.setName("teste");
        newUser.setEmail(emailForRegister);
        newUser.setPassword(passwordBEncrypted);

        newUser = userRepository.save(newUser);

        return newUser;
    }
}
