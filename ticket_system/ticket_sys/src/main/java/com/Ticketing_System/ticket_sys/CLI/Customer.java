package com.Ticketing_System.ticket_sys.CLI;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (ticketPool.getRemainingTickets() > 0) {
            try {
                Thread.sleep(retrievalRate);  // Simulate the retrieval rate (in ms)
            } catch (InterruptedException e) {
                // Log the interruption error
                CustomLogger.logError("Thread interrupted: " + Thread.currentThread().getName(), e);
                return;  // Stop further processing if interrupted
            }

            if (ticketPool.sellTicket()) {
                // Log the ticket purchase and remaining tickets
                CustomLogger.logInfo(Thread.currentThread().getName() + " bought 1 ticket. Remaining tickets: " + ticketPool.getRemainingTickets());
            }
        }
    }
}
