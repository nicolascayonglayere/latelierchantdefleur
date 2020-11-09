package com.atelierchantdefleur.bouquetcomposer.repository;

import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
}
