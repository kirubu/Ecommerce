package com.gorl.service;

import com.gorl.dto.JWTAuthResponse;
import com.gorl.dto.LoginDto;
import com.gorl.dto.RegisterDto;

public interface AuthService {

	JWTAuthResponse login(LoginDto dto);
	String register(RegisterDto dto);
}
