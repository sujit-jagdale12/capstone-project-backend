package com.ani.ems.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ani.ems.domain.Event;
import com.ani.ems.domain.Notification;
import com.ani.ems.domain.OrderTicket;
import com.ani.ems.domain.ReminderUpdate;
import com.ani.ems.domain.Schedule;
import com.ani.ems.domain.Ticket;
import com.ani.ems.domain.User;
import com.ani.ems.dto.AnalyticsDto;
import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.NewEventDto;
import com.ani.ems.dto.ReminderDto;
import com.ani.ems.dto.ScheduleDto;
import com.ani.ems.dto.SendNotificationDto;
import com.ani.ems.dto.TicketDto;
import com.ani.ems.dto.UpdateEventDto;
import com.ani.ems.exception.InvalidTicketException;
import com.ani.ems.exception.NoEventFoundException;
import com.ani.ems.repository.AdminRepository;
import com.ani.ems.repository.NotificationRepository;
import com.ani.ems.repository.OrderRepository;
import com.ani.ems.repository.ReminderRepository;
import com.ani.ems.repository.ScheduleRepository;
import com.ani.ems.repository.TicketRepository;
import com.ani.ems.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final TicketRepository ticketRepository;
    private final DynamicMapper dynamicMapper;
    private final ScheduleRepository scheduleRepository;
    private final ReminderRepository reminderRepository;
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Integer createNewEvent(NewEventDto dto) {
        Event event = dynamicMapper.convertor(dto, new Event());
        adminRepository.save(event);
        return 1;
    }

    @Override
    public List<EventListDto> getAllEvents() {
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
    public Integer updateEvent(UpdateEventDto dto) {
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

    @Override
    public Integer createTicket(Long id, TicketDto dto) {
        if (!isValidTicketType(dto.getType()))
            throw new InvalidTicketException("Invalid ticket type");
        
        Event event = adminRepository.findById(id)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + id + " id"));

        Ticket ticket = dynamicMapper.convertor(dto, new Ticket());
        ticket.setEvent(event);
        ticketRepository.save(ticket);
        return 1;
    }

    private boolean isValidTicketType(String type) {
        return type.equals("vip") || type.equals("earlybird") || type.equals("group");
    }

    @Override
    public Integer addSchedule(Long eventId, ScheduleDto dto) {
        Event event = adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("No Event found for " + eventId + " ID"));
        Schedule schedule = dynamicMapper.convertor(dto, new Schedule());
        schedule.setEvent(event);
        scheduleRepository.save(schedule);
        return 1;
    }

    @Override
    public Integer sendReminder(Long eventId, ReminderDto dto) {
        Event event = adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));

        ReminderUpdate reminderUpdate = dynamicMapper.convertor(dto, new ReminderUpdate());
        reminderUpdate.setEvent(event);

        reminderRepository.save(reminderUpdate);
        return 1;
    }

    @Override
    public Integer sendNotification(Long eventId, SendNotificationDto dto) {
        Set<User> notifiedUsers = new HashSet<>();
        Event event = adminRepository.findById(eventId)
                .orElseThrow(() -> new NoEventFoundException("Event not Found for " + eventId + " id"));
        List<OrderTicket> orders = orderRepository.findByEvent(event);
        for (OrderTicket order : orders) {
            User user = order.getUser();
            if (!notifiedUsers.contains(user)) {
                Notification notification = dynamicMapper.convertor(dto, new Notification());
                notification.setDate(LocalDate.now());
                notification.setUser(user);
                notification.setEvent(event);
                notificationRepository.save(notification);
                notifiedUsers.add(user);
            }
        }
        return 1;
    }

    @Override
    public List<AnalyticsDto> getTicketTypeCounts(Long eventId) {
        List<Object[]> ticketTypeCounts = orderRepository.countTicketsByType(eventId);

        List<AnalyticsDto> ticketTypeCountDTOs = new ArrayList<>();
        for (Object[] result : ticketTypeCounts) {
            String ticketType = (String) result[0];
            Long count = (Long) result[1];
            Long totalQuantity = orderRepository.sumQuantityByTicketType(eventId, ticketType);
            ticketTypeCountDTOs.add(new AnalyticsDto(ticketType, count, totalQuantity));
        }

        return ticketTypeCountDTOs;
    }

}
