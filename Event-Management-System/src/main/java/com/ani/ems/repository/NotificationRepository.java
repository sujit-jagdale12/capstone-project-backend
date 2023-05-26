package com.ani.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.Notification;
import com.ani.ems.domain.User;

public interface NotificationRepository extends JpaRepository<Notification,Long>{

    List<Notification> findByUser(User user);
    
}
