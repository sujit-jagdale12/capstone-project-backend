package com.ani.ems.service;

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

}
