package com.ani.ems.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.User;
import com.ani.ems.dto.ForgotpassDto;
import com.ani.ems.dto.LoginDto;
import com.ani.ems.dto.LoginResponseDto;
import com.ani.ems.dto.RegisterDto;
import com.ani.ems.exception.DuplicateEmailFoundException;
import com.ani.ems.exception.InvalidPasswordException;
import com.ani.ems.exception.InvalidRoleException;
import com.ani.ems.exception.UserNotFoundException;
import com.ani.ems.repository.UserRepository;
import com.ani.ems.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final DynamicMapper dynamicMapper;

    @Override
    public Integer registerUser(RegisterDto dto) {
        if (!"user".equals(dto.getRole()) && !"admin".equals(dto.getRole()))
            throw new InvalidRoleException("Invalid role! Enter admin/user");
        User user = dynamicMapper.convertor(dto, new User());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailFoundException("Email already used.");
        }
        isValidPassword(dto.getPassword());
        userRepository.save(user);
        return 1;
    }

    private void isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8) {
            throw new InvalidPasswordException("Password must be more than 8 characters and less than 20 characters in length.");
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            throw new InvalidPasswordException("Password must have atleast one uppercase character");
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            throw new InvalidPasswordException("Password must have atleast one lowercase character");
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            throw new InvalidPasswordException("Password must have atleast one number");
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars)) {
            throw new InvalidPasswordException("Password must have atleast one special character among @#$%");
        }
    }

    @Override
    public String loginUser(LoginDto dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
        return user.getRole();
    }

    @Override
    public String forgotpass(ForgotpassDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email Not Found"));

        return "Reset link send to email";
    }

    @Override
    public LoginResponseDto loginUserForResponse(LoginDto dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
        return dynamicMapper.convertor(user, new LoginResponseDto());
    }

}
