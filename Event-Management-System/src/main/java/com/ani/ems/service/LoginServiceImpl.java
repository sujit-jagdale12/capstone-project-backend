package com.ani.ems.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.User;
import com.ani.ems.dto.ForgotpassDto;
import com.ani.ems.dto.LoginDto;
import com.ani.ems.dto.RegisterDto;
import com.ani.ems.exception.DuplicateEmailFoundException;
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
    public Integer registerUser(RegisterDto dto) throws DuplicateEmailFoundException {
        User user = dynamicMapper.convertor(dto, new User());
        boolean isPresent = userRepository.existsByEmail(user.getEmail());
        if (isPresent) {
            throw new DuplicateEmailFoundException("Email already used.");
        }
        userRepository.save(user);
        return 1;
    }

    @Override
    public String loginUser(LoginDto dto) {
        Optional<User> op = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        User user = op.orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));

        return user.getRole();
    }

    @Override
    public String forgotpass(ForgotpassDto dto) {
        Optional<User> op = userRepository.findByEmail(dto.getEmail());
        op.orElseThrow(() -> new UserNotFoundException("Email Not Found"));

        return "Reset link send to email";
    }

}
