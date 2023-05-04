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
        Optional<Event> findById = adminRepository.findById(id);
        findById.orElseThrow(() -> new NoEventFoundException("No Event found for " + id + " ID"));

        adminRepository.deleteById(id);
        return 1;
    }

    @Override
    public Integer upateEvent(UpdateEventDto dto) {
        Long id = dto.getId();
        Optional<Event> eventByID = adminRepository.findById(id);
        eventByID.orElseThrow(() -> new NoEventFoundException("No Event found for " + id + " ID"));

        adminRepository.save(dynamicMapper.convertor(dto, new Event()));
        return 1;
    }

}
