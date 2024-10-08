package com.nt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.binding.Passanger;
import com.nt.binding.Ticket;
import com.nt.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepo;

    @Override
    public Ticket bookTicket(Passanger passanger) {
        if (passanger.getDoj() == null) {
            throw new IllegalArgumentException("Date of Journey (doj) must not be null");
        }

        // Generate ticket ID before creating the ticket instance
        String ticketId = generateTicketId(passanger.getTrainNo(), passanger.getDoj());
        
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);  // Assign the generated ticketId
        ticket.setName(passanger.getName());
        ticket.setDob(passanger.getDob());
        ticket.setGender(passanger.getGender());
        ticket.setDoj(passanger.getDoj());
        ticket.setFromStation(passanger.getFromStation());
        ticket.setToStation(passanger.getToStation());
        ticket.setTrainNo(passanger.getTrainNo());

        boolean seatAvailable = checkSeatAvailability(passanger.getTrainNo(), passanger.getDoj());

        if (seatAvailable) {
            ticket.setTicketStatus(ticketId + " confirmed");
        } else {
            ticket.setTicketStatus(ticketId + " waitlist");
        }

        return ticketRepo.save(ticket);
    }

    @Override
    public Optional<Ticket> getById(String ticketId) {
        return ticketRepo.findById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    private String generateTicketId(String trainNo, LocalDate doj) {
        return trainNo + "-" + doj.toString().replace("-", "") + "-" + new Random().nextInt(1000);
    }

    private boolean checkSeatAvailability(String trainNo, LocalDate doj) {
        // Check seat availability based on some logic; for now, this is a stub
        return Integer.parseInt(trainNo) % 2 == 0; // Example logic
    }
}
