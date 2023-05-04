package com.ani.ems.service;

import com.ani.ems.dto.NewEventDto;

public interface AdminService {
    Integer createNewEvent(NewEventDto dto);
}
