package com.example.TomTomIntegration.repository;

import com.example.TomTomIntegration.entity.PoiLogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiLogRepository extends JpaRepository<PoiLogsEntity, Long> {
}
