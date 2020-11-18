package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Fournisseur;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.TigeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TigeService {

    private final TigeRepository tigeRepository;
    private final TigeMapper tigeMapper;
    private final FournisseurMapper fournisseurMapper;

    @Autowired
    public TigeService(TigeRepository tigeRepository, TigeMapper tigeMapper, FournisseurMapper fournisseurMapper){
        this.tigeRepository = tigeRepository;
        this.tigeMapper = tigeMapper;
        this.fournisseurMapper = fournisseurMapper;
    }

    public List<TigeDTO> getAll(){
        List<Tige> tiges = this.tigeRepository.findAll();
        return tiges.stream()
                .map(t -> this.tigeMapper.fromEntityToDomain(t, this.fournisseurMapper.fromEntityToDomain(t.getFournisseur())))
                .collect(Collectors.toList());
    }

    public TigeDTO getByID(Integer id){
        Tige tige = this.tigeRepository.findById(Long.valueOf(id.toString()))
                .orElseThrow(() -> new RuntimeException("La tige n'existe pas en base : "+id));
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromEntityToDomain(tige.getFournisseur());
        return this.tigeMapper.fromEntityToDomain(tige, fournisseurDTO);
    }

    public TigeDTO save(TigeDTO tigeDTO){
        Fournisseur fournisseur = this.fournisseurMapper.fromDomainToEntity(tigeDTO.getFournisseurDTO());
        Tige tigeToSave = this.tigeMapper.fromDomainToEntity(tigeDTO);
        tigeToSave.setFournisseur(fournisseur);
        tigeToSave.setType("TIGE");
        Tige tige = this.tigeRepository.save(tigeToSave);
        return this.tigeMapper.fromEntityToDomain(tige, this.fournisseurMapper.fromEntityToDomain(tige.getFournisseur()));
    }
}
