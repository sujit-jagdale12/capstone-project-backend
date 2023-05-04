package com.ani.ems.service;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.Event;
import com.ani.ems.dto.NewEventDto;
import com.ani.ems.repository.AdminRepository;
import com.ani.ems.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final DynamicMapper dynamicMapper;

    @Override
    public Integer createNewEvent(NewEventDto dto) {
        Event event = dynamicMapper.convertor(dto, new Event());
        adminRepository.save(event);
        return 1;
    }

}
