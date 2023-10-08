package com.example.TomTomIntegration.repository;

import com.example.TomTomIntegration.entity.PoiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<PoiEntity, Long> {

    PoiEntity findByName(String name);

    Page<PoiEntity> findByNameContaining(String name, PageRequest pageRequest);
}
