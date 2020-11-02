package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TigeMapper {

    public TigeDTO fromEntityToDomain(Tige tige){
        return new TigeDTO(
          tige.getId(),
          tige.getNom(),
          Objects.isNull(tige.getNomLatin()) ? "" : tige.getNomLatin(),
          tige.getPrixUnitaire()
        );
    }

    public TigeRest fromDomainToRest(TigeDTO tigeDTO){
        TigeRest tigeRest = new TigeRest();
        tigeRest.setId(tigeDTO.getId());
        tigeRest.setNom(tigeDTO.getNom());
        tigeRest.setNomLatin(tigeDTO.getNomLatin());
        tigeRest.setPrixUnitaire(tigeDTO.getPrixUnitaire());
        return tigeRest;
    }

}
