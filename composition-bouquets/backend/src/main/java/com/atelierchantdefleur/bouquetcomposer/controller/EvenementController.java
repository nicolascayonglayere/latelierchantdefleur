package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.service.EvenementService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.EvenementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.EvenementMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.EvenementRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;
    @Autowired
    private EvenementMapper evenementMapper;
    @Autowired
    private CompositionMapper compositionMapper;
    @Autowired
    private ElementCompositionMapper elementCompositionMapper;

    @PostMapping("atelier-chant-de-fleur/evenements")
    public EvenementRest save(@RequestBody EvenementRest evenementRest){
        List<CompositionDTO> compositionDTOS = evenementRest.getCompositions().stream()
                .map(c -> this.compositionMapper.fromRestToDomain(c, new ArrayList<>(), new ArrayList<>()))
                .collect(Collectors.toList());
        EvenementDTO evenementDTO = this.evenementMapper.fromRestToDomain(evenementRest, compositionDTOS);
        EvenementDTO evtSave = this.evenementService.save(evenementDTO);
        return this.evenementMapper.fromDomainToRest(evtSave, evenementRest.getCompositions());
    }

    @PutMapping("atelier-chant-de-fleur/evenements")
    public EvenementRest update(@RequestBody EvenementRest evenementRest){
        List<CompositionDTO> compositionDTOS = evenementRest.getCompositions().stream()
                .map(c -> this.compositionMapper.fromRestToDomain(c, new ArrayList<>(), new ArrayList<>()))
                .collect(Collectors.toList());
        EvenementDTO evenementDTO = this.evenementMapper.fromRestToDomain(evenementRest, compositionDTOS);
        EvenementDTO evtSave = this.evenementService.save(evenementDTO);
        return this.evenementMapper.fromDomainToRest(evtSave, evenementRest.getCompositions());
    }

    @GetMapping("atelier-chant-de-fleur/evenements/{id}")
    public EvenementRest getById(@PathVariable("id") Long id){
        EvenementDTO evenementDTO = this.evenementService.getById(id);
        List<CompositionRest> compositionRests = evenementDTO.getCompositions().stream()
                .map(c -> this.compositionMapper.fromDomainToRest(c,
                        c.getElementsComposition().stream()
                                .map(e -> this.elementCompositionMapper.fromDomainToRest(e))
                                .collect(Collectors.toList()), new ArrayList<>()))
                .collect(Collectors.toList());
        return this.evenementMapper.fromDomainToRest(evenementDTO, compositionRests);
    }

    @GetMapping("atelier-chant-de-fleur/evenements/")
    public List<EvenementRest> getAll(){
        List<EvenementDTO> evenementDTOList = this.evenementService.getAll();
        return evenementDTOList.stream()
                .map(e -> this.evenementMapper.fromDomainToRest(e,
                        e.getCompositions().stream()
                            .map(c -> this.compositionMapper.fromDomainToRest(c,
                                    c.getElementsComposition().stream()
                                        .map(elt -> this.elementCompositionMapper.fromDomainToRest(elt))
                                    .collect(Collectors.toList()), new ArrayList<>()))
                            .collect(Collectors.toList())))
                .sorted(Comparator.comparing(EvenementRest::getNom))
                .collect(Collectors.toList());
    }

    @GetMapping(value="atelier-chant-de-fleur/evenements", params={"id-compo"})
    public List<EvenementRest> getByIdCompo(@RequestParam(name="id-compo") Long idCompo){
        List<EvenementDTO> evenementDTOList = this.evenementService.getByIdCompo(idCompo);
        return evenementDTOList.stream()
                .map(e -> this.evenementMapper.fromDomainToRest(e, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("atelier-chant-de-fleur/evenements/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        this.evenementService.delete(id);
        return ResponseEntity.ok(null);
    }
}
