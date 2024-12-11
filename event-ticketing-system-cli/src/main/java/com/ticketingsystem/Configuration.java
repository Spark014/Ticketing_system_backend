package com.ticketingsystem;

import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private int vendorCount;
    private int customerCount;

    // Log configuration changes
    public void addTickets(int ticketsToAdd) {
        totalTickets += ticketsToAdd;
        CustomLogger.logInfo(ticketsToAdd + " tickets added. Total tickets: " + totalTickets);
    }

    // Method to get input from the user
    public void getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum ticket capacity:");
        maxTicketCapacity = scanner.nextInt();
        CustomLogger.logInfo("Maximum ticket capacity set to: " + maxTicketCapacity);

        System.out.println("Enter the initial number of total tickets:");
        totalTickets = scanner.nextInt();
        CustomLogger.logInfo("Initial total tickets set to: " + totalTickets);

        System.out.println("Enter the ticket release rate per vendor:");
        ticketReleaseRate = scanner.nextInt();
        CustomLogger.logInfo("Ticket release rate per vendor set to: " + ticketReleaseRate + " seconds");

        System.out.println("Enter the ticket retrieval rate per customer:");
        customerRetrievalRate = scanner.nextInt();
        CustomLogger.logInfo("Customer ticket retrieval rate set to: " + customerRetrievalRate + " seconds");
    }

    // Method to get vendor and customer counts
    public void getCount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vendors:");
        vendorCount = scanner.nextInt();
        CustomLogger.logInfo("Number of vendors set to: " + vendorCount);

        System.out.println("Enter the number of customers:");
        customerCount = scanner.nextInt();
        CustomLogger.logInfo("Number of customers set to: " + customerCount);
    }

    // Getters for configuration values
    public int getVendorCount() {
        return vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
