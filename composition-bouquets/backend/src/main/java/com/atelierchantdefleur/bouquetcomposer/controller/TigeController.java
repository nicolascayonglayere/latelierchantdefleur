package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.service.TigeService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class TigeController {

    @Autowired
    private TigeMapper tigeMapper;
    @Autowired
    private TigeService tigeService;
    @Autowired
    private FournisseurMapper fournisseurMapper;

    @GetMapping("atelier-chant-de-fleur/tiges/")
    public List<TigeRest> getAllTiges(){
        List<TigeDTO> tigeDTOS = this.tigeService.getAll();
        return tigeDTOS.stream()
                .sorted(Comparator.comparing(t -> t.getNom().toLowerCase()))
                .map(t -> this.tigeMapper.fromDomainToRest(t, this.fournisseurMapper.fromDomainToRest(t.getFournisseurDTO())))
                .collect(Collectors.toList());
    }

    @GetMapping("atelier-chant-de-fleur/tiges/{id}")
    public TigeRest getById(@PathVariable(name="id") Integer id){
        TigeDTO tigeDTO = this.tigeService.getByID(id);
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(tigeDTO.getFournisseurDTO());
        return this.tigeMapper.fromDomainToRest(tigeDTO, fournisseurRest);
    }

    @PostMapping("atelier-chant-de-fleur/tiges/{id}/edit")
    public TigeRest save(@RequestBody TigeRest tige){
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromRestToDomain(tige.getFournisseurRest());
        TigeDTO tigeToSave = this.tigeMapper.fromRestToDomainWithFournisseur(tige, fournisseurDTO);
        TigeDTO tigeDTO = this.tigeService.save(tigeToSave);
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(tigeDTO.getFournisseurDTO());
        return this.tigeMapper.fromDomainToRest(tigeDTO, fournisseurRest);
    }

    @PutMapping("atelier-chant-de-fleur/tiges/{id}/edit")
    public TigeRest update(@RequestBody TigeRest tige, @PathVariable(name="id") Integer id){
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromRestToDomain(tige.getFournisseurRest());
        TigeDTO tigeToUpdate = this.tigeMapper.fromRestToDomainWithFournisseur(tige, fournisseurDTO);
        TigeDTO tigeDTO = this.tigeService.save(tigeToUpdate);
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(tigeDTO.getFournisseurDTO());
        return this.tigeMapper.fromDomainToRest(tigeDTO, fournisseurRest);
    }
}
