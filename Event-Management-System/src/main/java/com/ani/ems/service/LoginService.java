package com.ani.ems.service;

import com.ani.ems.dto.RegisterDto;

public interface LoginService {
    Integer registerUser(RegisterDto dto);

    String loginUser(RegisterDto dto);

    String forgotpass(RegisterDto dto);
}
