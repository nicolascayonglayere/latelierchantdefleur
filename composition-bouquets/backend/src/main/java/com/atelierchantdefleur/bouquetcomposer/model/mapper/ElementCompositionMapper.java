package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementCompositionRest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ElementCompositionMapper {

    public ElementCompositionRest fromDomainToRest(ElementCompositionDTO elementCompositionDTO){
        ElementCompositionRest elementCompositionRest = new ElementCompositionRest();
        elementCompositionRest.setId(elementCompositionDTO.getId());
        elementCompositionRest.setNom(elementCompositionDTO.getNom());
        elementCompositionRest.setType(elementCompositionDTO.getType());
        elementCompositionRest.setQuantite(elementCompositionDTO.getQuantite());
        elementCompositionRest.setPrixUnitaire(elementCompositionDTO.getPrixUnitaire() / 100);
        return elementCompositionRest;
    }

    public ElementCompositionDTO fromRestToDomain(ElementCompositionRest elementCompositionRest){
        return new ElementCompositionDTO(
        elementCompositionRest.getId(),
        elementCompositionRest.getNom(),
        elementCompositionRest.getType(),
        elementCompositionRest.getQuantite(),
        (int) (elementCompositionRest.getPrixUnitaire()*100));
    }

    public ElementCompositionDTO fromEntityToDomain(ElementComposition elementComposition, Long quantite){
        return new ElementCompositionDTO(
                elementComposition.getId(),
                elementComposition.getNom(),
                elementComposition.getType(),
                Integer.valueOf(quantite.toString()),
                elementComposition.getPrixUnitaire());
    }

    public ElementComposition fromDomainToEntity(ElementCompositionDTO elementCompositionDTO){
        ElementComposition elementComposition = new ElementComposition();
        elementComposition.setId(Objects.isNull(elementCompositionDTO.getId()) ? null : elementCompositionDTO.getId());
        elementComposition.setNom(elementCompositionDTO.getNom());
        elementComposition.setType(elementComposition.getType());
        elementComposition.setPrixUnitaire(elementComposition.getPrixUnitaire());
        return elementComposition;
    }
}
