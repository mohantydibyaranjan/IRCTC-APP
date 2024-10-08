package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.binding.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {
	

}
