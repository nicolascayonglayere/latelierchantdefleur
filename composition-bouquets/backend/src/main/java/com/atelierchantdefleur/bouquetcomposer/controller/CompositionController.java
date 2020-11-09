package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.domain.CompositionService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private TigeMapper tigeMapper;
    @Autowired
    private MateriauMapper materiauMapper;
    @Autowired
    private FournisseurMapper fournisseurMapper;

    @PostMapping("atelier-chant-de-fleur/compositions/{id}/edit")
    public CompositionRest save(@RequestBody CompositionRest compositionRest){
        List<TigeDTO> tigeDTO = compositionRest.getTiges().stream()
        .map(this.tigeMapper::fromRestToDomainWithoutFournisseur)
                .collect(Collectors.toList());
        List<MateriauDTO> materiauDTOS = compositionRest.getMateriaux().stream()
                .map(this.materiauMapper::fromRestToDomainWithoutFournisseur)
                .collect(Collectors.toList());
        CompositionDTO compositionDTOTosave = this.compositionMapper.fromRestToDomain(compositionRest, tigeDTO, materiauDTOS);
        CompositionDTO compositionDTO = this.compositionService.save(compositionDTOTosave);
        return this.compositionMapper.fromDomainToRest(compositionDTO, compositionRest.getTiges(), compositionRest.getMateriaux());
    }

    @GetMapping("atelier-chant-de-fleur/compositions/")
    public List<CompositionRest> getAll(){
        List<CompositionDTO> compositionDTOS = this.compositionService.getAll();
        return compositionDTOS.stream()
                .map(c -> this.compositionMapper.fromDomainToRest(
                        c,
                        c.getTiges().stream()
                            .map(t -> this.tigeMapper.fromDomainToRest(t, this.fournisseurMapper.fromDomainToRest(t.getFournisseurDTO())))
                            .collect(Collectors.toList()),
                        c.getMateriaux().stream()
                            .map((m -> this.materiauMapper.fromDomainToRest(m, this.fournisseurMapper.fromDomainToRest(m.getFournisseurDTO()))))
                            .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
