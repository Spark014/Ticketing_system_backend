package com.ticketingsystem;

import java.util.Scanner;

public class Simulation {
    private int maxTickets;
    private int totalTickets;
    private int[] vendorReleaseRates;
    private int numVendors;
    private int numCustomers;
    private int customerRetrievalRate;
    private TicketPool ticketPool;

    public Simulation(int maxTickets, int totalTickets, int[] vendorReleaseRates, int numVendors, int numCustomers, int customerRetrievalRate, TicketPool ticketPool) {
        this.maxTickets = maxTickets;
        this.totalTickets = totalTickets;
        this.vendorReleaseRates = vendorReleaseRates;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    public void startSimulation() {
        Scanner scanner = new Scanner(System.in);

        // Create and start vendor and customer threads
        Thread[] vendorThreads = new Thread[numVendors];
        for (int i = 0; i < numVendors; i++) {
            final int vendorIndex = i;
            vendorThreads[i] = new Thread(new Vendor(ticketPool, vendorReleaseRates[vendorIndex]), "Vendor-" + (vendorIndex + 1));
            vendorThreads[i].start();
        }

        Thread[] customerThreads = new Thread[numCustomers];
        for (int i = 0; i < numCustomers; i++) {
            final int customerIndex = i;
            customerThreads[i] = new Thread(new Customer(ticketPool, customerRetrievalRate), "Customer-" + (customerIndex + 1));
            customerThreads[i].start();
        }

        try {
            for (int i = 0; i < numVendors; i++) {
                vendorThreads[i].join();
            }
            for (int i = 0; i < numCustomers; i++) {
                customerThreads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if all tickets are sold
        if (ticketPool.getRemainingTickets() == 0) {
            System.out.println("Tickets sold out!");
            manageTickets(scanner);
        }
    }

    private void manageTickets(Scanner scanner) {
        while (true) {
            System.out.println("Would you like to add more tickets, reset the simulation, or stop?");
            System.out.println("1. Add tickets");
            System.out.println("2. Reset simulation");
            System.out.println("3. Stop simulation");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    int availableTickets = maxTickets - totalTickets;
                    if (availableTickets <= 0) {
                        System.out.println("You cannot add more tickets. Maximum capacity reached.");
                    } else {
                        System.out.println("You can add up to " + availableTickets + " tickets.");
                        System.out.print("Enter the number of tickets to add: ");
                        int ticketsToAdd = Integer.parseInt(scanner.nextLine());
                        if (ticketsToAdd <= availableTickets) {
                            totalTickets += ticketsToAdd;
                            ticketPool.addTickets(ticketsToAdd);  // Add tickets to the pool
                            System.out.println("Tickets added successfully.");
                            startSimulation();  // Restart the simulation with the new total tickets
                            return;  // Exit the manageTickets loop after restarting simulation
                        } else {
                            System.out.println("You cannot add more than " + availableTickets + " tickets.");
                        }
                    }
                    break;

                case "2":
                    System.out.println("Resetting simulation...");
                    ticketPool = new TicketPool(totalTickets);  // Reset ticket pool
                    startSimulation();  // Restart the simulation with original ticket count
                    return;  // Exit the manageTickets loop after resetting

                case "3":
                    System.out.println("Simulation stopped.");
                    return;  // Stop the simulation

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
