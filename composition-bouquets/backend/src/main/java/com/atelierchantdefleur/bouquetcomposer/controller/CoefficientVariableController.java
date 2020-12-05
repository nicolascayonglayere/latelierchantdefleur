package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.model.rest.CoefficientVariableRest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class CoefficientVariableController {

    @GetMapping("atelier-chant-de-fleur/coefficients")
    public CoefficientVariableRest getAll(){
        return new CoefficientVariableRest(30, 30, 20);
    }

}
