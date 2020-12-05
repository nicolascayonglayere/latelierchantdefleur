package com.atelierchantdefleur.bouquetcomposer.repository;

import com.atelierchantdefleur.bouquetcomposer.model.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvementRepository extends JpaRepository<Evenement, Long> {

    List<Evenement> findByCompositionsId(Long idCompo);
}
