package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.MateriauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriauService {

    private final MateriauRepository materiauRepository;
    private final MateriauMapper materiauMapper;

    @Autowired
    public MateriauService(MateriauRepository materiauRepository, MateriauMapper materiauMapper){
        this.materiauRepository = materiauRepository;
        this.materiauMapper = materiauMapper;
    }

    public List<MateriauDTO> getAll(){
        List<Materiau> materiauList = this.materiauRepository.findAll();
        return materiauList.stream().map(this.materiauMapper::fromEntityToDomain).collect(Collectors.toList());
    }
}
