package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.*;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CompositionService {

    private final CompositionRepository compositionRepository;
    private final CompositionMapper compositionMapper;
    private final TigeMapper tigeMapper;
    private final MateriauMapper materiauMapper;
    private final FournisseurMapper fournisseurMapper;
    private final ElementCompositionMapper elementCompositionMapper;

    @Autowired
    public CompositionService (CompositionRepository compositionRepository, CompositionMapper compositionMapper,
                               TigeMapper tigeMapper, MateriauMapper materiauMapper, FournisseurMapper fournisseurMapper, ElementCompositionMapper elementCompositionMapper){
        this.compositionRepository = compositionRepository;
        this.compositionMapper = compositionMapper;
        this.tigeMapper = tigeMapper;
        this.materiauMapper = materiauMapper;
        this.fournisseurMapper = fournisseurMapper;
        this.elementCompositionMapper = elementCompositionMapper;
    }

    public CompositionDTO save(CompositionDTO compositionDTO){
        List<ElementComposition> elementCompositions = new ArrayList<>();
        compositionDTO.getElementsComposition().forEach(e ->{
            IntStream.range(0, e.getQuantite()).forEach(i ->{
                elementCompositions.add(this.elementCompositionMapper.fromDomainToEntity(e));
            });
        });
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions);
        Composition composition = this.compositionRepository.save(compositionToSave);
       return this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition());
    }

    public List<CompositionDTO> getAll(){
        List<Composition> composition = this.compositionRepository.findAll();
           return composition.stream()
                .map(c -> this.compositionMapper
                        .fromEntityToDomain(c,
                                c.getElements().stream()
                                        .map(elt -> this.elementCompositionMapper.fromEntityToDomain(elt, this.calculQuantiteElement(c.getElements(), elt.getId())))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }


    private Long calculQuantiteElement(List<ElementComposition> eltComposition, Long id){
        return eltComposition.stream().filter(e -> Objects.equals(e.getId(), id)).count();
    }
}
