package com.ani.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.ems.domain.OrderTicket;
import com.ani.ems.domain.User;

public interface OrderRepository extends JpaRepository<OrderTicket, Long> {
    User findByUserId(Long userId);
}
