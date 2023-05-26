package com.ani.ems.service;

import java.time.LocalDate;
import java.util.List;

import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.GetNotificationDto;
import com.ani.ems.dto.OrderDto;
import com.ani.ems.dto.TicketDto;
import com.ani.ems.dto.UserEventDto;
import com.ani.ems.dto.UserReminderDto;
import com.ani.ems.dto.ViewSpeakerDetails;

public interface UserService {
    Integer bookEvent(Long userId,Long eventId);

    Integer orderEventTicket(Long userId,Long eventId,OrderDto dto);

    List<UserEventDto> getAllEvents(Long userId);

    List<EventListDto> getEventsByLocation(String location);
    
    List<EventListDto> getEventsByDate(LocalDate date);

    UserEventDto getEvent(Long userId,Long eventId);

    List<TicketDto>getAllTicketsEventId(Long eventId);
    
    List<ViewSpeakerDetails>getAllSpeakersEventId(Long eventId);

    List<EventListDto> getAllUpcomingEvents(LocalDate date);

    List<UserReminderDto> getAllReminders(Long eventId);

    List<GetNotificationDto> getNotifications(Long userId);
}
