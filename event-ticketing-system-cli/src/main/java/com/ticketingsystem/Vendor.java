package com.ticketingsystem;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        while (ticketPool.getRemainingTickets() > 0) {
            try {
                Thread.sleep(releaseRate);  // Simulate the release rate (in ms)
            } catch (InterruptedException e) {
                // Log the interruption error
                CustomLogger.logError("Thread interrupted: " + Thread.currentThread().getName(), e);
                return;  // Stop further processing if interrupted
            }

            if (ticketPool.sellTicket()) {
                // Log the ticket release and remaining tickets
                CustomLogger.logInfo(Thread.currentThread().getName() + " released 1 ticket. Remaining tickets: " + ticketPool.getRemainingTickets());
            }
        }
    }
}
