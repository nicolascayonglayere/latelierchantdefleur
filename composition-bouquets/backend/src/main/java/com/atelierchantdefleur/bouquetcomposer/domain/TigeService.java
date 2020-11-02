package com.atelierchantdefleur.bouquetcomposer.domain;

import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
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

    @Autowired
    public TigeService(TigeRepository tigeRepository, TigeMapper tigeMapper){
        this.tigeRepository = tigeRepository;
        this.tigeMapper = tigeMapper;
    }

    public List<TigeDTO> getAll(){
        List<Tige> tiges = this.tigeRepository.findAll();
        return tiges.stream().map(this.tigeMapper::fromEntityToDomain).collect(Collectors.toList());
    }
}
