package com.ani.ems.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.ems.dto.NewEventDto;
import com.ani.ems.service.AdminService;
import com.ani.ems.util.AppResponse;

import lombok.AllArgsConstructor;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    
    private final AdminService adminService;
    
    @PostMapping(value = "/newevent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> newEvent(@Valid @RequestBody NewEventDto dto) {
        Integer createNewEvent = adminService.createNewEvent(dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("new event created successfully.")
                .bd(createNewEvent)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
