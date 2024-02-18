package com.allrise.njord.service;

import com.allrise.njord.entity.UserEntity;
import com.allrise.njord.resource.request.AuthRequest;
import org.springframework.stereotype.Service;

@Service("authService")
public interface AuthService {

    UserEntity register(AuthRequest authRequest);

}
