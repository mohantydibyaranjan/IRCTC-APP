package com.nt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.Passanger;
import com.nt.binding.Ticket;
import com.nt.service.TicketService;

@RestController
public class TicketController {
	@Autowired
	private TicketService ticketService;
	@PostMapping(value = "/book",consumes= "application/json",produces= "application/json")
	public ResponseEntity<Ticket> bookTicket(@RequestBody Passanger passanger){
		Ticket ticket = ticketService.bookTicket(passanger);
		return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
	}
	
	@GetMapping(value="/book/{id}", produces= "application/json")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") String TicketId){
		Optional<Ticket> byId = ticketService.getById(TicketId);
		if(byId.isPresent()) { 
			return new ResponseEntity<Ticket>(byId.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
//		return new ResponseEntity<Ticket> (byId,HttpStatus.OK);
//		return byId.map(ResponseEntity::ok) 
//                .orElse(ResponseEntity.notFound().build()); 
	}
	
	@GetMapping(value = "/all",  produces = "application/json")
	public ResponseEntity<List<Ticket>> getAllTicket(){
		List<Ticket> allTickets = ticketService.getAllTickets();
		return new ResponseEntity<>(allTickets,HttpStatus.OK);
		
	}
	

}
