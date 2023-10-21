package com.example.TomTomIntegration.repository;

import com.example.TomTomIntegration.entity.PoiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<PoiEntity, Long> {

    PoiEntity findByName(String name);

    @Query("SELECT p FROM PoiEntity p WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:scoreMin IS NULL OR p.score >= CAST(:scoreMin as text)) " +
            "AND (:scoreMax IS NULL OR p.score <= CAST(:scoreMax as text)) " +
            "AND (:country IS NULL OR p.country LIKE %:country%)")
    Page<PoiEntity> searchPoi(@Param("name") String name,
                              @Param("scoreMin") Double scoreMin,
                              @Param("scoreMax") Double scoreMax,
                              @Param("country") String country,
                              PageRequest pageRequest);

}
