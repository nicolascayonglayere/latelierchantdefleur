package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Fournisseur;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.MateriauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriauService {

    private final MateriauRepository materiauRepository;
    private final MateriauMapper materiauMapper;
    private final FournisseurMapper fournisseurMapper;

    @Autowired
    public MateriauService(MateriauRepository materiauRepository, MateriauMapper materiauMapper, FournisseurMapper fournisseurMapper){
        this.materiauRepository = materiauRepository;
        this.materiauMapper = materiauMapper;
        this.fournisseurMapper = fournisseurMapper;
    }

    public List<MateriauDTO> getAll(){
        List<Materiau> materiauList = this.materiauRepository.findAll();
        return materiauList.stream()
                .map(m -> this.materiauMapper.fromEntityToDomain(m, this.fournisseurMapper.fromEntityToDomain(m.getFournisseur())))
                .collect(Collectors.toList());
    }

    public MateriauDTO getById(Integer id){
        Materiau materiau = this.materiauRepository.findById(Long.valueOf(id.toString()))
                .orElseThrow(() -> new RuntimeException("Le materiau n'existe pas : "+id));
        FournisseurDTO fournisseurDTO = this.fournisseurMapper.fromEntityToDomain(materiau.getFournisseur());
        return this.materiauMapper.fromEntityToDomain(materiau, fournisseurDTO);
    }

    public MateriauDTO save(MateriauDTO materiauDTO){
        Fournisseur fournisseur = this.fournisseurMapper.fromDomainToEntity(materiauDTO.getFournisseurDTO());
        Materiau materiauToSave = this.materiauMapper.fromDomainToEntity(materiauDTO);
        materiauToSave.setFournisseur(fournisseur);
        Materiau materiau = this.materiauRepository.save(materiauToSave);
        return this.materiauMapper.fromEntityToDomain(materiau, this.fournisseurMapper.fromEntityToDomain(materiau.getFournisseur()));
    }
}
