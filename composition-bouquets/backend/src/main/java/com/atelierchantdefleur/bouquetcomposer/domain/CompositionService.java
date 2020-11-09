package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompositionService {

    private final CompositionRepository compositionRepository;
    private final CompositionMapper compositionMapper;
    private final TigeMapper tigeMapper;
    private final MateriauMapper materiauMapper;
    private final FournisseurMapper fournisseurMapper;

    @Autowired
    public CompositionService (CompositionRepository compositionRepository, CompositionMapper compositionMapper,
                               TigeMapper tigeMapper, MateriauMapper materiauMapper, FournisseurMapper fournisseurMapper){
        this.compositionRepository = compositionRepository;
        this.compositionMapper = compositionMapper;
        this.tigeMapper = tigeMapper;
        this.materiauMapper = materiauMapper;
        this.fournisseurMapper = fournisseurMapper;
    }

    public CompositionDTO save(CompositionDTO compositionDTO){
        List<Tige> tiges = compositionDTO.getTiges().stream()
                .map(this.tigeMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        List<Materiau> materiaux = compositionDTO.getMateriaux().stream()
                .map(this.materiauMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, tiges, materiaux);
        Composition composition = this.compositionRepository.save(compositionToSave);
       return this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getTiges(), compositionDTO.getMateriaux());
    }

    public List<CompositionDTO> getAll(){
        List<Composition> composition = this.compositionRepository.findAll();
        return composition.stream()
                .map(c -> this.compositionMapper
                        .fromEntityToDomain(c,
                                c.getTiges().stream()
                                        .map(t -> this.tigeMapper.fromEntityToDomain(t, this.fournisseurMapper.fromEntityToDomain(t.getFournisseur())))
                                        .collect(Collectors.toList()),
                                c.getMateriaux().stream()
                                        .map(m -> this.materiauMapper.fromEntityToDomain(m, this.fournisseurMapper.fromEntityToDomain(m.getFournisseur())))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
