package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Fournisseur;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.FournisseurMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.MateriauMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.TigeMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;
    private final TigeMapper tigeMapper;
    private final MateriauMapper materiauMapper;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository, FournisseurMapper fournisseurMapper,
                              TigeMapper tigeMapper, MateriauMapper materiauMapper){
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurMapper = fournisseurMapper;
        this.tigeMapper = tigeMapper;
        this.materiauMapper = materiauMapper;
    }

    public List<FournisseurDTO> getAll(){
        return this.fournisseurRepository.findAll().stream()
                .map(this.fournisseurMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    public FournisseurDTO getById(Integer id){
        Fournisseur fournisseur = this.fournisseurRepository.findById(Long.valueOf(id.toString()))
                .orElseThrow(() -> new RuntimeException("Le fournisseur n'existe pas : "+id));
        FournisseurDTO fournisseurDTO =  this.fournisseurMapper.fromEntityToDomain(fournisseur);
        fournisseurDTO.setTiges(fournisseur.getTiges().stream()
                .map(t -> this.tigeMapper.fromEntityToDomain(t, fournisseurDTO))
                .collect(Collectors.toList()));
        fournisseurDTO.setMateriaux(fournisseur.getMateriaux().stream()
                .map(m -> this.materiauMapper.fromEntityToDomain(m, fournisseurDTO))
                .collect(Collectors.toList()));
        return fournisseurDTO;
    }

    public FournisseurDTO save(FournisseurDTO fournisseurDTO){
        Fournisseur fournisseur = this.fournisseurRepository.save(this.fournisseurMapper.fromDomainToEntity(fournisseurDTO));
        FournisseurDTO fournisseurDTOSave = this.fournisseurMapper.fromEntityToDomain(fournisseur);
        if(!fournisseurDTO.getTiges().isEmpty()){
            fournisseurDTOSave.setTiges(
                    fournisseur.getTiges().stream()
                            .map(t -> this.tigeMapper.fromEntityToDomain(t, fournisseurDTOSave))
                            .collect(Collectors.toList()));
        }
        if(!fournisseurDTO.getMateriaux().isEmpty()){
            fournisseurDTOSave.setMateriaux(fournisseur.getMateriaux().stream()
                    .map(m -> this.materiauMapper.fromEntityToDomain(m, fournisseurDTOSave))
                    .collect(Collectors.toList()));
        }

        return fournisseurDTOSave;
    }
}
