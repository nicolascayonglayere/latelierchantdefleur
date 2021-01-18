package com.atelierchantdefleur.bouquetcomposer.repository;


import com.atelierchantdefleur.bouquetcomposer.model.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
