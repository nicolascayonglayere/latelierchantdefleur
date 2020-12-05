package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.EvenementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Evenement;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.EvenementMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.EvementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvenementService {

    private final EvementRepository evementRepository;
    private final EvenementMapper evenementMapper;
    private final CompositionMapper compositionMapper;
    private final ElementCompositionMapper elementCompositionMapper;

    public EvenementService(EvementRepository evementRepository, EvenementMapper evenementMapper, CompositionMapper compositionMapper, ElementCompositionMapper elementCompositionMapper){
        this.compositionMapper = compositionMapper;
        this.evementRepository = evementRepository;
        this.evenementMapper = evenementMapper;
        this.elementCompositionMapper = elementCompositionMapper;
    }

    public EvenementDTO save(EvenementDTO evenementDTO){
        List<Composition> compositions = evenementDTO.getCompositions().stream()
                .map(c -> this.compositionMapper.fromDomainToEntity(c, new ArrayList<>(), new ArrayList<>()))
                .collect(Collectors.toList());
        Evenement evenementToSave = this.evenementMapper.fromDomainToEntity(evenementDTO, compositions);
        Evenement evenement = this.evementRepository.save(evenementToSave);
        return this.evenementMapper.fromEntityToDomain(evenement, evenementDTO.getCompositions());
    }

    public EvenementDTO getById(Long id){
        Evenement evenement = this.evementRepository.findById(id).orElseThrow(() -> new RuntimeException("L'evenement n'existe pas"));
        List<CompositionDTO> compositionDTOS = evenement.getCompositions().stream()
                .map(c -> this.compositionMapper.fromEntityToDomain(c, c.getElements().stream()
                        .map(e -> this.elementCompositionMapper.fromEntityToDomain(e, 1l))
                        .collect(Collectors.toList()), new ArrayList<>()))
                .collect(Collectors.toList());
        return this.evenementMapper.fromEntityToDomain(evenement, compositionDTOS);
    }

    public List<EvenementDTO> getAll(){
        List<Evenement> evenementList = this.evementRepository.findAll();
        return evenementList.stream()
                .map(e -> this.evenementMapper.fromEntityToDomain(e,
                    e.getCompositions().stream()
                        .map(c -> this.compositionMapper.fromEntityToDomain(c,
                                c.getElements().stream()
                                    .map(elt -> this.elementCompositionMapper.fromEntityToDomain(elt, 1l))
                                    .collect(Collectors.toList()), new ArrayList<>()))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<EvenementDTO> getByIdCompo(Long idCompo){
        List<Evenement> evenementList = this.evementRepository.findByCompositionsId(idCompo);
        return evenementList.stream()
                .map(e -> this.evenementMapper.fromEntityToDomain(e, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        this.evementRepository.deleteById(id);
    }
}
