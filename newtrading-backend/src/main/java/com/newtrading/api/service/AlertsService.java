package com.newtrading.api.service;

import com.newtrading.api.model.Alerts;
import com.newtrading.api.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertsService {

    @Autowired
    private AlertsRepository alertsRepository;

    public List<Alerts> getAllAlerts() {
        return alertsRepository.findAll();
    }
}
