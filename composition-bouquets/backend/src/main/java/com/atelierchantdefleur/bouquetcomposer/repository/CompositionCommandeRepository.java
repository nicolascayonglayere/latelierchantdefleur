package com.atelierchantdefleur.bouquetcomposer.repository;

import com.atelierchantdefleur.bouquetcomposer.model.entity.CompositionsCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionCommandeRepository extends JpaRepository<CompositionsCommande, Long> {

    void deleteAllByCommandeId(Long idCommmande);
    void deleteAllByCompositionId(Long idCompo);
}
