package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.model.constante.HttpUrlConstantes;
import com.atelierchantdefleur.bouquetcomposer.service.CompositionService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping(CompositionController.rootUrl)
@RestController
@CrossOrigin(HttpUrlConstantes.CROSS_ORIGIN)
public class CompositionController {

    public static final String rootUrl = HttpUrlConstantes.ROOT_URL + "/" + HttpUrlConstantes.COMPOSITION_URL;

    @Autowired
    private CompositionService compositionService;
    @Autowired
    private CompositionMapper compositionMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private ImageCompositionMapper imageCompositionMapper;

    @PostMapping("/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.EDIT)
    public CompositionRest save(@RequestBody CompositionRest compositionRest){
        List<ElementDTO> elementDTOS = compositionRest.getElements().stream()
                .map(this.elementMapper::fromRestToDomain)
                .collect(Collectors.toList());
        CompositionDTO compositionDTOTosave = this.compositionMapper.fromRestToDomain(compositionRest, elementDTOS, new ArrayList<>());
        CompositionDTO compositionDTO = this.compositionService.save(compositionDTOTosave);
        List<ElementRest> elementRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        elementRests.stream()
                .sorted(Comparator.comparing(ElementRest::getNom))
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementRests, new ArrayList<>());
    }

    @PostMapping(value="/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.EDIT, params = {HttpUrlConstantes.ID_EVT_PARAM})
    public CompositionRest saveInEvt(@RequestBody CompositionRest compositionRest, @RequestParam(name="id-evenement")Long idEvt){
        List<ElementDTO> elementDTOS = compositionRest.getElements().stream()
                .map(this.elementMapper::fromRestToDomain)
                .collect(Collectors.toList());
        CompositionDTO compositionDTOTosave = this.compositionMapper.fromRestToDomain(compositionRest, elementDTOS, new ArrayList<>());
        CompositionDTO compositionDTO = this.compositionService.saveInEvt(compositionDTOTosave, idEvt);
        List<ElementRest> elementRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        elementRests.stream()
                .sorted(Comparator.comparing(ElementRest::getNom))
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementRests, new ArrayList<>());
    }

    @PutMapping("/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.EDIT)
    public CompositionRest update(@RequestBody CompositionRest compositionRest){
        List<ElementDTO> elementDTOS = compositionRest.getElements().stream()
                .map(this.elementMapper::fromRestToDomain)
                .collect(Collectors.toList());
        CompositionDTO compositionDTOTosave = this.compositionMapper.fromRestToDomain(compositionRest, elementDTOS, new ArrayList<>());
        CompositionDTO compositionDTO = this.compositionService.update(compositionDTOTosave);
        List<ElementRest> elementRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        elementRests.stream()
                .sorted(Comparator.comparing(ElementRest::getNom))
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementRests, new ArrayList<>());
    }

    @GetMapping("/")
    public List<CompositionRest> getAll(){
        List<CompositionDTO> compositionDTOS = this.compositionService.getAll();
        return compositionDTOS.stream()
                .sorted(Comparator.comparing(CompositionDTO::getDateCreation).reversed())
                .map(c -> this.compositionMapper.fromDomainToRest(
                        c,
                        new ArrayList<>(c.getElementsComposition().stream()
                                .map(this.elementMapper::fromDomainToRest)
                                .collect(Collectors.toSet())),
                        c.getImagesComposition().stream()
                                .map(this.imageCompositionMapper::fromDomainToRest)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @GetMapping("/"+HttpUrlConstantes.ID_PV)
    public CompositionRest getById(@PathVariable Long id){
        CompositionDTO compositionDTO = this.compositionService.getById(id);
        List<ElementRest> elementRests = new ArrayList<>(compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToRest)
                .collect(Collectors.toSet()));
        List<ImageCompositionRest> imageCompositionRests = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToRest)
                .collect(Collectors.toList());
        return this.compositionMapper.fromDomainToRest(compositionDTO, elementRests, imageCompositionRests);
    }

    @DeleteMapping("/"+HttpUrlConstantes.ID_PV)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        this.compositionService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/"+HttpUrlConstantes.EVENEMENT_URL+"/"+HttpUrlConstantes.ID_PV)
    public ResponseEntity<String> deleteByIdFromEvt(@PathVariable Long id){
        this.compositionService.deleteByIdFromEvt(id);
        return ResponseEntity.ok(null);
    }
}
