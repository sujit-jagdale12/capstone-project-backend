package com.ani.ems.service;

import java.util.List;

import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.NewEventDto;
import com.ani.ems.dto.TicketDto;
import com.ani.ems.dto.UserEventDto;

public interface UserService {
    Integer bookEvent(Long userId,Long eventId);

    List<UserEventDto> getAllEvents(Long userId);

    List<EventListDto> getEventsByLocation(String location);

    UserEventDto getEvent(Long userId,Long eventId);

    List<TicketDto>getAllTicketsEventId(Long eventId);
}
