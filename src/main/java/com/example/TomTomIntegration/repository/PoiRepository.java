package com.example.TomTomIntegration.repository;

import com.example.TomTomIntegration.entity.PoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<PoiEntity, Long> {

    PoiEntity findByName(String name);

    List<PoiEntity> findByNameContaining(String name);
}
