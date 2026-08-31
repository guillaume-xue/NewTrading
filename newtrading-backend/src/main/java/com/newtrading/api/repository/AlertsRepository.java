package com.newtrading.api.repository;

import com.newtrading.api.model.SimulatedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.newtrading.api.model.Alerts;
import java.util.List;
import java.util.UUID;

@Repository
public interface AlertsRepository extends JpaRepository<Alerts, UUID> {

}
