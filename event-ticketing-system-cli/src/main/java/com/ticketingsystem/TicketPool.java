package com.ticketingsystem;

public class TicketPool {
    private int remainingTickets;

    public TicketPool(int initialTickets) {
        this.remainingTickets = initialTickets;
    }

    // Synchronized to ensure thread-safe access to the ticket pool
    public synchronized boolean sellTicket() {
        if (remainingTickets > 0) {
            remainingTickets--;
            return true;
        }
        return false; // No tickets available
    }

    public synchronized int getRemainingTickets() {
        return remainingTickets;
    }

    public synchronized void addTickets(int ticketsToAdd) {
        remainingTickets += ticketsToAdd;
    }
}
