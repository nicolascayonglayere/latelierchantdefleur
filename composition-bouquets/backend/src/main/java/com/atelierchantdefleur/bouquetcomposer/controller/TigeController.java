package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.domain.TigeService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class TigeController {

    @Autowired
    private TigeMapper tigeMapper;
    @Autowired
    private TigeService tigeService;

    @GetMapping("atelier-chant-de-fleur/tiges/")
    public List<TigeRest> getAllTiges(){
        List<TigeDTO> tigeDTOS = this.tigeService.getAll();
        return tigeDTOS.stream().map(this.tigeMapper::fromDomainToRest).collect(Collectors.toList());
    }
}
