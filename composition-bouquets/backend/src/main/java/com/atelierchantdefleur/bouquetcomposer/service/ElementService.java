package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Element;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementService {

    private final ElementRepository elementRepository;
    private final ElementMapper elementMapper;

    @Autowired
    public ElementService(ElementRepository elementRepository, ElementMapper elementMapper) {
        this.elementRepository = elementRepository;
        this.elementMapper = elementMapper;
    }

    public ElementDTO getById(Long id){
        Element element = this.elementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'element n'existe pas"));
        return this.elementMapper.fromEntityToDomain(element, 0d);
    }

    public ElementDTO getByIdWithQuantite(Long id, Double quantite){
        Element element = this.elementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'element n'existe pas"));
        return this.elementMapper.fromEntityToDomain(element, quantite);
    }
}
