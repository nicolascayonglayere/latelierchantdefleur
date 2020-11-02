package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.domain.MateriauService;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.DataTablesMateriau;
import com.atelierchantdefleur.bouquetcomposer.model.rest.MateriauRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class MateriauController {

    @Autowired
    private MateriauMapper materiauMapper;
    @Autowired
    private MateriauService materiauService;

    @GetMapping("atelier-chant-de-fleur/materiaux/")
    public List<MateriauRest> getAll(){
        return this.materiauService.getAll().stream()
                .map(this.materiauMapper::fromDomainToRest)
                .collect(Collectors.toList());
    }

    @GetMapping("atelier-chant-de-fleur/materiaux/datatables")
    public DataTablesMateriau getAllDatatablesMateriaux(){
        return this.materiauMapper.fromDomainToRest(this.materiauService.getAll());
    }
}
