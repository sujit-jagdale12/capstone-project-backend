package com.ani.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ani.ems.domain.OrderTicket;
import com.ani.ems.domain.User;

public interface OrderRepository extends JpaRepository<OrderTicket, Long> {
    User findByUserId(Long userId);

    @Query("SELECT ot.tickettype, COUNT(ot) FROM OrderTicket ot WHERE ot.event.id = :eventId GROUP BY ot.tickettype")
    List<Object[]> countTicketsByType(Long eventId);
}
