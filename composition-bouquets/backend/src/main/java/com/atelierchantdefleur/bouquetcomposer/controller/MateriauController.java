package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.service.MateriauService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.MateriauRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class MateriauController {

    @Autowired
    private MateriauMapper materiauMapper;
    @Autowired
    private MateriauService materiauService;
    @Autowired
    private FournisseurMapper fournisseurMapper;

    @GetMapping("atelier-chant-de-fleur/materiaux/")
    public List<MateriauRest> getAll(){
        return this.materiauService.getAll().stream()
                .sorted(Comparator.comparing(MateriauDTO::getNom))
                .map(m -> this.materiauMapper.fromDomainToRest(m, this.fournisseurMapper.fromDomainToRest(m.getFournisseurDTO())))
                .collect(Collectors.toList());
    }

    @GetMapping("atelier-chant-de-fleur/materiaux/{id}")
    public MateriauRest getById(@PathVariable(name="id") Integer id){
        MateriauDTO materiauDTO = this.materiauService.getById(id);
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(materiauDTO.getFournisseurDTO());
        return this.materiauMapper.fromDomainToRest(materiauDTO, fournisseurRest);
    }

    @PostMapping("atelier-chant-de-fleur/materiaux/{id}/edit")
    public MateriauRest save(@RequestBody MateriauRest materiauRest){
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromRestToDomain(materiauRest.getFournisseurRest());
        MateriauDTO materiauDTO = this.materiauService.save(this.materiauMapper.fromRestToDomainWithFournisseur(materiauRest, fournisseurDTO));
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(materiauDTO.getFournisseurDTO());
        return this.materiauMapper.fromDomainToRest(materiauDTO, fournisseurRest);
    }

    @PutMapping("atelier-chant-de-fleur/materiaux/{id}/edit")
    public MateriauRest update(@RequestBody MateriauRest materiauRest){
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromRestToDomain(materiauRest.getFournisseurRest());
        MateriauDTO materiauDTO = this.materiauService.save(this.materiauMapper.fromRestToDomainWithFournisseur(materiauRest, fournisseurDTO));
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(materiauDTO.getFournisseurDTO());
        return this.materiauMapper.fromDomainToRest(materiauDTO, fournisseurRest);
    }
}
