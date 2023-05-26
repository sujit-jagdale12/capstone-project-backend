package com.ani.ems.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ani.ems.dto.EventListDto;
import com.ani.ems.dto.GetNotificationDto;
import com.ani.ems.dto.OrderDto;
import com.ani.ems.dto.TicketDto;
import com.ani.ems.dto.UserEventDto;
import com.ani.ems.dto.UserReminderDto;
import com.ani.ems.dto.ViewSpeakerDetails;
import com.ani.ems.service.UserService;
import com.ani.ems.util.AppResponse;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/attendee")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/{userId}/event/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> newEvent(@Valid @PathVariable Long userId, @PathVariable Long eventId) {
        Integer bookEvent = userService.bookEvent(userId, eventId);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .msg("new event booked successfully.")
                .bd(bookEvent)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEventDto>> findAll(@PathVariable Long userId) {

        return ResponseEntity.ok().body(userService.getAllEvents(userId));
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventListDto>> findEventByLocation(@RequestParam String location) {

        return ResponseEntity.ok().body(userService.getEventsByLocation(location));
    }

    @GetMapping(value = "/events/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventListDto>> findEventByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok().body(userService.getEventsByDate(date));
    }

    @GetMapping(value = "/{userId}/event/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<UserEventDto>> findEventById(@PathVariable Long userId,
            @PathVariable Long eventId) {

        UserEventDto dto = userService.getEvent(userId, eventId);

        AppResponse<UserEventDto> response = AppResponse.<UserEventDto>builder()
                .msg("Event Details")
                .bd(dto)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/tickets/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketDto>> findAllTicketsByEventId(@PathVariable Long eventId) {

        return ResponseEntity.ok().body(userService.getAllTicketsEventId(eventId));
    }

    @GetMapping(value = "/upcomingevents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventListDto>> upcomingEvents() {

        return ResponseEntity.ok().body(userService.getAllUpcomingEvents(LocalDate.now()));
    }

    @GetMapping(value = "event/speaker/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ViewSpeakerDetails>> findAllSpeakersByEventId(@PathVariable Long eventId) {

        return ResponseEntity.ok().body(userService.getAllSpeakersEventId(eventId));
    }

    @GetMapping(value = "/events/{eventId}/reminders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserReminderDto>>> getRemindersAndUpdates(@PathVariable Long eventId) {

        List<UserReminderDto> allReminders = userService.getAllReminders(eventId);
        AppResponse<List<UserReminderDto>> response = AppResponse.<List<UserReminderDto>>builder()
                .msg("Reminders for event")
                .bd(allReminders)
                .build();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping(value = "/{userId}/notification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<GetNotificationDto>>> getNotifications(@PathVariable Long userId) {

        List<GetNotificationDto> getNotifications = userService.getNotifications(userId);
        AppResponse<List<GetNotificationDto>> response = AppResponse.<List<GetNotificationDto>>builder()
                .msg("Reminders for event")
                .bd(getNotifications)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/{userId}/event/{eventId}/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> orderTickets(@Valid @PathVariable Long userId,
            @PathVariable Long eventId,
            @RequestBody OrderDto dto) {
        Integer bookEvent = userService.orderEventTicket(userId, eventId, dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .msg("new event booked successfully.")
                .bd(bookEvent)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
