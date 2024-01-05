package com.gorl.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gorl.Entity.Role;
import com.gorl.Entity.User;
import com.gorl.dto.JWTAuthResponse;
import com.gorl.dto.LoginDto;
import com.gorl.dto.RegisterDto;
import com.gorl.dto.UserDto;
import com.gorl.exception.BadRequestException;
import com.gorl.repo.RoleRepo;
import com.gorl.repo.UserRepo;
import com.gorl.security.JwtTokenProvider;


@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, 
			UserRepo userRepo, RoleRepo roleRepo,PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider) {
		
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public JWTAuthResponse login(LoginDto dto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtTokenProvider.generateToken(authentication);
		User user = userRepo.findByEmail(dto.getEmail()).get();
		
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setUsername(user.getUsername());
		String role = "User";
		Set<Role> roleUser = user.getRoles();
		for(Role roleTemp:roleUser)
		{
			if(roleTemp.getName().equalsIgnoreCase("ROLE_ADMIN"))
				role = "Admin";
		}
		userDto.setRole(role);
		return new JWTAuthResponse(token,userDto);
	}

	@Override
	public String register(RegisterDto dto) {

		if(userRepo.existsByUsername(dto.getUsername()))
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exist");
		
		if(userRepo.existsByEmail(dto.getEmail()))
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exist");
		
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role role = roleRepo.findByName("ROLE_USER").get();
		roles.add(role);
		
		user.setRoles(roles);
		
		userRepo.save(user);
		
		return "Register Successfull!..";
	}

}