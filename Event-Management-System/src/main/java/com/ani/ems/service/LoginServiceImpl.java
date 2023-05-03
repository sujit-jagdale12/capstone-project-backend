package com.ani.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.User;
import com.ani.ems.dto.RegisterDto;
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
        User user = dynamicMapper.convertor(dto, new User());
        userRepository.save(user);
        return 1;
    }

    @Override
    public String loginUser(RegisterDto dto) {
        User user = dynamicMapper.convertor(dto, new User());
        List<User> listUsers = userRepository.findAll();
        
        for (User user1 : listUsers) {
            if (user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword())) {
                return user1.getRole();
            }
        }
        return "User Not Found";
    }

    @Override
    public String forgotpass(RegisterDto dto) {
        User user = dynamicMapper.convertor(dto, new User());
        List<User> listUsers = userRepository.findAll();
        for (User user1 : listUsers) {
            if (user1.getEmail().equals(user.getEmail())) {
                return "Reset link send";
            }
        }
        return "Invalid Email";
    }

}
