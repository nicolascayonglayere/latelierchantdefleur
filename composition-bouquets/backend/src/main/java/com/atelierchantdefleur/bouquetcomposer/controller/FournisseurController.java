package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.service.FournisseurService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
    private FournisseurMapper fournisseurMapper;
    @Autowired
    private TigeMapper tigeMapper;
    @Autowired
    private MateriauMapper materiauMapper;

    @GetMapping("atelier-chant-de-fleur/fournisseurs/")
    public List<FournisseurRest> getAll(){
        return this.fournisseurService.getAll().stream()
                .sorted(Comparator.comparing(FournisseurDTO::getNom))
                .map(this.fournisseurMapper::fromDomainToRest)
                .collect(Collectors.toList());
    }

    @GetMapping("atelier-chant-de-fleur/fournisseurs/{id}")
    public FournisseurRest getById(@PathVariable(name="id") Integer id){
        FournisseurDTO fournisseurDTO = this.fournisseurService.getById(id);
        FournisseurRest fournisseurRest = this.fournisseurMapper.fromDomainToRest(fournisseurDTO);
//        fournisseurRest.setTiges(
//                fournisseurDTO.getTiges().stream()
//                        .sorted(Comparator.comparing(TigeDTO::getNom))
//                        .map(t -> this.tigeMapper.fromDomainToRest(t, fournisseurRest))
//                        .collect(Collectors.toList())
//        );
//        fournisseurRest.setMateriaux(
//                fournisseurDTO.getMateriaux().stream()
//                        .sorted(Comparator.comparing(MateriauDTO::getNom))
//                        .map(m -> this.materiauMapper.fromDomainToRest(m, fournisseurRest))
//                        .collect(Collectors.toList())
//        );
        return fournisseurRest;
    }

    @PostMapping("atelier-chant-de-fleur/fournisseurs/{id}/edit")
    public FournisseurRest save(@RequestBody FournisseurRest fournisseurRest){
        FournisseurDTO fournisseurDTO = this.fournisseurService.save(this.fournisseurMapper.fromRestToDomain(fournisseurRest));
        return this.fournisseurMapper.fromDomainToRest(fournisseurDTO);
    }

    @PutMapping("atelier-chant-de-fleur/fournisseurs/{id}/edit")
    public FournisseurRest update(@RequestBody FournisseurRest fournisseurRest){
        FournisseurDTO fournisseurDTO = this.fournisseurService.save(this.fournisseurMapper.fromRestToDomain(fournisseurRest));
        FournisseurRest fournisseurRestUpdate = this.fournisseurMapper.fromDomainToRest(fournisseurDTO);
//        fournisseurRestUpdate.setTiges(
//                fournisseurDTO.getTiges().stream()
//                        .sorted(Comparator.comparing(TigeDTO::getNom))
//                        .map(t -> this.tigeMapper.fromDomainToRest(t, fournisseurRestUpdate))
//                        .collect(Collectors.toList())
//        );
//        fournisseurRestUpdate.setMateriaux(
//                fournisseurDTO.getMateriaux().stream()
//                        .sorted(Comparator.comparing(MateriauDTO::getNom))
//                        .map(m -> this.materiauMapper.fromDomainToRest(m, fournisseurRestUpdate))
//                        .collect(Collectors.toList())
//        );
        return fournisseurRestUpdate;
    }




}
