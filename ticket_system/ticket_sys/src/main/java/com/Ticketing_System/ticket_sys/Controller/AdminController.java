package com.Ticketing_System.ticket_sys.Controller;

import com.Ticketing_System.ticket_sys.CLI.ConfigDTO;
import com.Ticketing_System.ticket_sys.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/start")
    public void startSimulation() {
        adminService.startSimulation();
    }

    @PostMapping("/pause")
    public void pauseSimulation() {
        adminService.pauseSimulation();
    }

    @PostMapping("/reset")
    public void resetSimulation() {
        adminService.resetSimulation();
    }

    @PostMapping("/configure")
    public void configureSystem(@RequestBody ConfigDTO configDTO) {
        adminService.configureSystem(configDTO);
    }
}