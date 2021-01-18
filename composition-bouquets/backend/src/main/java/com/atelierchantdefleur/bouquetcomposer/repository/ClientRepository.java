package com.atelierchantdefleur.bouquetcomposer.repository;

import com.atelierchantdefleur.bouquetcomposer.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
