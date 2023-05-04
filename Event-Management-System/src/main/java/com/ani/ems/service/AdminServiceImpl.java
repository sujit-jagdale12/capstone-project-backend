package com.ani.ems.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.Event;
import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.NewEventDto;
import com.ani.ems.dto.UpdateEventDto;
import com.ani.ems.exception.NoEventFoundException;
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

    @Override
    public List<EventListDto> getAllEvents() throws NoEventFoundException {
        List<EventListDto> collect = adminRepository.findAll()
                .stream()
                .map(event -> dynamicMapper.convertor(event, new EventListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new NoEventFoundException("No event found create one.");

        return collect;
    }

    @Override
    public Integer deleteEvent(Long id) {
        isEventPresent(id);
        adminRepository.deleteById(id);
        return 1;
    }

    @Override
    public Integer upateEvent(UpdateEventDto dto) {
        isEventPresent(dto.getId());
        adminRepository.save(dynamicMapper.convertor(dto, new Event()));
        return 1;
    }

    @Override
    public NewEventDto getEvent(Long id) {
        isEventPresent(id);
        Event event = adminRepository.getReferenceById(id);
        return dynamicMapper.convertor(event, new NewEventDto());
    }

    private void isEventPresent(Long id) {
        adminRepository.findById(id).orElseThrow(() -> new NoEventFoundException("No Event found for " + id + " ID"));
    }

}
