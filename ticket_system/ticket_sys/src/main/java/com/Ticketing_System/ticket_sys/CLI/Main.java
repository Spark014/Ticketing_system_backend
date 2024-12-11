package com.Ticketing_System.ticket_sys.CLI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Log start of simulation
        CustomLogger.logInfo("Starting the ticketing system simulation...");

        // Get the maximum ticket capacity
        System.out.print("Enter maximum ticket capacity: ");
        int maxTickets = Integer.parseInt(scanner.nextLine());
        CustomLogger.logInfo("Maximum ticket capacity set to: " + maxTickets);

        // Get the total tickets available at the start
        System.out.print("Enter total number of tickets: ");
        int totalTickets = Integer.parseInt(scanner.nextLine());
        CustomLogger.logInfo("Total number of tickets set to: " + totalTickets);

        // Get the number of vendors and their release rates
        System.out.print("Enter the number of vendors: ");
        int numVendors = Integer.parseInt(scanner.nextLine());
        CustomLogger.logInfo("Number of vendors: " + numVendors);

        // Get the vendor release rate in seconds
        System.out.print("Enter the vendor release rate (in seconds): ");
        int vendorReleaseRate = Integer.parseInt(scanner.nextLine()) * 1000; // Convert to milliseconds
        CustomLogger.logInfo("Vendor release rate set to: " + vendorReleaseRate + " milliseconds");

        // Create the vendor release rates array, all vendors use the same rate
        int[] vendorReleaseRates = new int[numVendors];
        for (int i = 0; i < numVendors; i++) {
            vendorReleaseRates[i] = vendorReleaseRate;
        }

        // Get the number of customers
        System.out.print("Enter the number of customers: ");
        int numCustomers = Integer.parseInt(scanner.nextLine());
        CustomLogger.logInfo("Number of customers: " + numCustomers);

        // Get the customer retrieval rate in seconds
        System.out.print("Enter the customer retrieval rate (in seconds): ");
        int customerRetrievalRate = Integer.parseInt(scanner.nextLine()) * 1000; // Convert to milliseconds
        CustomLogger.logInfo("Customer retrieval rate set to: " + customerRetrievalRate + " milliseconds");

        // Create the ticket pool with the initial total tickets
        TicketPool ticketPool = new TicketPool(totalTickets);
        CustomLogger.logInfo("Ticket pool initialized with " + totalTickets + " tickets.");

        // Create and start the simulation
        Simulation simulation = new Simulation(maxTickets, totalTickets, vendorReleaseRates, numVendors, numCustomers, customerRetrievalRate, ticketPool);
        simulation.startSimulation();

        // Log end of simulation
        CustomLogger.logInfo("Ticketing system simulation has ended.");
    }
}
