package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.binding.Passanger;
import com.nt.binding.Ticket;

public interface TicketService {
	
	public Ticket bookTicket(Passanger passanger);
	
	public Optional<Ticket> getById(String ticketid);
	public List<Ticket> getAllTickets();

}
