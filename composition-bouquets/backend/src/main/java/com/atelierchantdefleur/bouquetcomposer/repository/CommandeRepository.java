package com.atelierchantdefleur.bouquetcomposer.repository;

import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByCompositionsId(Long idCompo);
}
