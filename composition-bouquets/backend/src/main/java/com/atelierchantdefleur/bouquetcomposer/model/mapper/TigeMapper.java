package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TigeMapper {

    public TigeDTO fromEntityToDomain(Tige tige, FournisseurDTO fournisseurDTO){
        return new TigeDTO(
          tige.getId(),
          tige.getNom(),
          Objects.isNull(tige.getNomLatin()) ? "" : tige.getNomLatin(),
          tige.getPrixUnitaire(),
          fournisseurDTO
        );
    }

    public TigeRest fromDomainToRest(TigeDTO tigeDTO, FournisseurRest fournisseurRest){
        TigeRest tigeRest = new TigeRest();
        tigeRest.setId(tigeDTO.getId());
        tigeRest.setNom(tigeDTO.getNom());
        tigeRest.setNomLatin(tigeDTO.getNomLatin());
        tigeRest.setPrixUnitaire(tigeDTO.getPrixUnitaire());
        tigeRest.setFournisseurRest(fournisseurRest);
        return tigeRest;
    }

    public Tige fromDomainToEntity(TigeDTO tigeDTO){
        Tige tige = new Tige();
        tige.setId(tigeDTO.getId() == 0 ? null : tigeDTO.getId());
        tige.setNom(tigeDTO.getNom());
        tige.setNomLatin(tigeDTO.getNomLatin());
        tige.setPrixUnitaire(tigeDTO.getPrixUnitaire());
        return tige;
    }

    public TigeDTO fromRestToDomainWithoutFournisseur(TigeRest tigeRest){
        return new TigeDTO(
                tigeRest.getId(),
                tigeRest.getNom(),
                Objects.isNull(tigeRest.getNomLatin()) ? "" : tigeRest.getNomLatin(),
                tigeRest.getPrixUnitaire(),
                null
        );
    }

    public TigeDTO fromRestToDomainWithFournisseur(TigeRest tigeRest, FournisseurDTO fournisseurDTO){
        return new TigeDTO(
                tigeRest.getId(),
                tigeRest.getNom(),
                Objects.isNull(tigeRest.getNomLatin()) ? "" : tigeRest.getNomLatin(),
                tigeRest.getPrixUnitaire(),
                fournisseurDTO
        );
    }

}
