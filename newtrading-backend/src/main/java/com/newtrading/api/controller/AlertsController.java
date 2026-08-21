package com.newtrading.api.controller;

import com.newtrading.api.model.Alerts;
import com.newtrading.api.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    @Autowired
    private AlertsRepository alertsRepository;

}
