package com.ani.ems.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.Event;
import com.ani.ems.domain.Notification;
import com.ani.ems.domain.OrderTicket;
import com.ani.ems.domain.User;
import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.GetNotificationDto;
import com.ani.ems.dto.OrderDto;
import com.ani.ems.dto.TicketDto;
import com.ani.ems.dto.UserEventDto;
import com.ani.ems.dto.UserReminderDto;
import com.ani.ems.dto.ViewSpeakerDetails;
import com.ani.ems.exception.DuplicateEventException;
import com.ani.ems.exception.InvalidRoleException;
import com.ani.ems.exception.InvalidTicketException;
import com.ani.ems.exception.NoEventFoundException;
import com.ani.ems.exception.PastDateException;
import com.ani.ems.exception.UserNotFoundException;
import com.ani.ems.repository.AdminRepository;
import com.ani.ems.repository.NotificationRepository;
import com.ani.ems.repository.OrderRepository;
import com.ani.ems.repository.ReminderRepository;
import com.ani.ems.repository.ScheduleRepository;
import com.ani.ems.repository.TicketRepository;
import com.ani.ems.repository.UserRepository;
import com.ani.ems.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final TicketRepository ticketRepository;
    private final ScheduleRepository scheduleRepository;
    private final ReminderRepository reminderRepository;
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;

    private final DynamicMapper dynamicMapper;

    @Override
    public Integer bookEvent(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't book Event");
        Event event = adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));

        if (user.getEvents().contains(event))
            throw new DuplicateEventException("Event already booked...");
        user.getEvents().add(event);
        userRepository.save(user);
        return 1;
    }

    private boolean isValidTicketType(String type) {
        return type.equals("vip") || type.equals("earlybird") || type.equals("group");
    }

    @Override
    public Integer orderEventTicket(Long userId, Long eventId, OrderDto dto) {

        if (!isValidTicketType(dto.getTickettype()))
            throw new InvalidTicketException("Invalid ticket type");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        Event event = adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));
        if (event.getStartdate().isBefore(LocalDate.now()))
            throw new PastDateException("Can't book event its already occured.");
        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't book Event");
        OrderTicket order = dynamicMapper.convertor(dto, new OrderTicket());
        order.setUser(user);
        order.setDate(LocalDate.now());
        order.setEvent(event);
        orderRepository.save(order);

        user.getEvents().add(event);
        userRepository.save(user);

        return 1;
    }

    @Override
    public List<UserEventDto> getAllEvents(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No bookings for Admin");

        List<UserEventDto> collect = user.getEvents()
                .stream()
                .map(event -> dynamicMapper.convertor(event, new UserEventDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new NoEventFoundException("No event found book one.");

        return collect;
    }

    @Override
    public List<EventListDto> getEventsByLocation(String location) {
        List<EventListDto> collect = adminRepository.findAllByLocation(location)
                .stream()
                .map(event -> dynamicMapper.convertor(event, new EventListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new NoEventFoundException("No event found for location");

        return collect;
    }

    @Override
    public List<EventListDto> getEventsByDate(LocalDate date) {
        List<EventListDto> collect = adminRepository.findAllByStartdate(date)
                .stream()
                .map(event -> dynamicMapper.convertor(event, new EventListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new NoEventFoundException("No event found for location");

        return collect;
    }

    @Override
    public UserEventDto getEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't book Event");
        return user.getEvents().stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst().map(event -> dynamicMapper.convertor(event, new UserEventDto()))
                .orElseThrow(() -> new NoEventFoundException("Event not found"));
    }

    @Override
    public List<TicketDto> getAllTicketsEventId(Long eventId) {
        adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));
        return ticketRepository.findByEventId(eventId)
                .stream()
                .map(ticket -> dynamicMapper.convertor(ticket, new TicketDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventListDto> getAllUpcomingEvents(LocalDate date) {
        List<EventListDto> collect = adminRepository.findAllByEnddateGreaterThanEqual(date)
                .stream()
                .map(event -> dynamicMapper.convertor(event, new EventListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new NoEventFoundException("No event found create one.");

        return collect;

    }

    @Override
    public List<ViewSpeakerDetails> getAllSpeakersEventId(Long eventId) {
        adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));
        return scheduleRepository.findByEventId(eventId)
                .stream()
                .map(speaker -> dynamicMapper.convertor(speaker, new ViewSpeakerDetails()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserReminderDto> getAllReminders(Long eventId) {
        adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));

        return reminderRepository.findByEventId(eventId)
                .stream()
                .map(speaker -> dynamicMapper.convertor(speaker, new UserReminderDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetNotificationDto> getNotifications(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));
        List<Notification> notifications = notificationRepository.findByUser(user);
        List<GetNotificationDto> notificationDtos = new ArrayList<>();

        for (Notification notification : notifications) {
            GetNotificationDto notificationDto = new GetNotificationDto();
            notificationDto.setMessage(notification.getMessage());
            notificationDto.setDate(notification.getDate());
            notificationDtos.add(notificationDto);
        }
        return notificationDtos;
    }
}
