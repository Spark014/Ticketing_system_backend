package com.Ticketing_System.ticket_sys.Services;

import com.Ticketing_System.ticket_sys.CLI.ConfigDTO;
import com.Ticketing_System.ticket_sys.CLI.Main;
import com.Ticketing_System.ticket_sys.CLI.Configuration;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private boolean isRunning = false;
    private Thread simulationThread;
    private Configuration configuration = new Configuration();

    public void startSimulation() {
        if (!isRunning) {
            isRunning = true;
            simulationThread = new Thread(() -> {
                try {
                    Main.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            simulationThread.start();
        }
    }

    public void pauseSimulation() {
        if (isRunning) {
            isRunning = false;
            if (simulationThread != null) {
                simulationThread.interrupt();
            }
        }
    }

    public void resetSimulation() {
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
        isRunning = false;
        startSimulation();
    }

    public void configureSystem(ConfigDTO configDTO) {
        configuration.setTotalTickets(configDTO.getTotalTickets());
        configuration.setTicketReleaseRate(configDTO.getTicketReleaseRate());
        configuration.setCustomerRetrievalRate(configDTO.getCustomerRetrievalRate());
        configuration.setMaxTicketCapacity(configDTO.getMaxTicketCapacity());
        configuration.setVendorCount(configDTO.getVendorCount());
        configuration.setCustomerCount(configDTO.getCustomerCount());
    }
}