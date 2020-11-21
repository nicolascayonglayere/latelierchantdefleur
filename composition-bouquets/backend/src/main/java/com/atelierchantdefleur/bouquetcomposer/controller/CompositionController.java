package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.domain.CompositionService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementCompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class CompositionController {

    @Autowired
    private CompositionService compositionService;
    @Autowired
    private CompositionMapper compositionMapper;
    @Autowired
    private ElementCompositionMapper elementCompositionMapper;
    @Autowired
    private ImageCompositionMapper imageCompositionMapper;

    @PostMapping("atelier-chant-de-fleur/compositions/{id}/edit")
    public CompositionRest save(@RequestBody CompositionRest compositionRest){
        List<ElementCompositionDTO> elementCompositionDTOS = compositionRest.getElements().stream()
                .map(this.elementCompositionMapper::fromRestToDomain)
                .collect(Collectors.toList());
        CompositionDTO compositionDTOTosave = this.compositionMapper.fromRestToDomain(compositionRest, elementCompositionDTOS, new ArrayList<>());
        CompositionDTO compositionDTO = this.compositionService.save(compositionDTOTosave);
        List<ElementCompositionRest> elementCompositionRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementCompositionMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        elementCompositionRests.stream()
                .sorted(Comparator.comparing(ElementCompositionRest::getNom))
//                .sorted(Comparator.comparing(ElementCompositionRest::getType))
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementCompositionRests, new ArrayList<>());
    }

    @GetMapping("atelier-chant-de-fleur/compositions/")
    public List<CompositionRest> getAll(){
        List<CompositionDTO> compositionDTOS = this.compositionService.getAll();
        return compositionDTOS.stream()
                .sorted(Comparator.comparing(CompositionDTO::getDateCreation).reversed())
                .map(c -> this.compositionMapper.fromDomainToRest(
                        c,
                        new ArrayList<>(c.getElementsComposition().stream()
                                .map(this.elementCompositionMapper::fromDomainToRest)
                                .collect(Collectors.toSet())),
                        c.getImagesComposition().stream()
                                .map(this.imageCompositionMapper::fromDomainToRest)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @GetMapping("atelier-chant-de-fleur/compositions/{id}")
    public CompositionRest getById(@PathVariable Long id){
        CompositionDTO compositionDTO = this.compositionService.getById(id);
        List<ElementCompositionRest> elementCompositionRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementCompositionMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        List<ImageCompositionRest> imageCompositionRests = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToRest)
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementCompositionRests, imageCompositionRests);
    }
}
