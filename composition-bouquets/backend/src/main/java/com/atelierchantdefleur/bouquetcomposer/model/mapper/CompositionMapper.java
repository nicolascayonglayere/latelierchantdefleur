package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementCompositionRest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
public class CompositionMapper {

    public CompositionDTO fromRestToDomain(CompositionRest compositionRest, List<ElementCompositionDTO> elementCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(compositionRest.getId());
        compositionDTO.setDateCreation(compositionRest.getDateCreation());
        compositionDTO.setDureeCreation(LocalTime.ofSecondOfDay((long) (compositionRest.getDureeCreation() * 3600)));
        compositionDTO.setPrixUnitaire((int) (compositionRest.getPrixUnitaire() * 100));
        compositionDTO.setElementsComposition(elementCompositionDTOS);
        return compositionDTO;
    }

    public CompositionRest fromDomainToRest(CompositionDTO compositionDTO, List<ElementCompositionRest> elementCompositionRests){
        CompositionRest compositionRest = new CompositionRest();
        compositionRest.setId(compositionDTO.getId());
        compositionRest.setDateCreation(compositionDTO.getDateCreation());
        compositionRest.setPrixUnitaire((float)compositionDTO.getPrixUnitaire() / 100);
        compositionRest.setDureeCreation((float)Duration.between(LocalTime.MIDNIGHT, compositionDTO.getDureeCreation()).toMinutes()/60);//float)compositionDTO.getDureeCreation().getSecond()/3600);
        compositionRest.setElements(elementCompositionRests);
        return compositionRest;
    }

    public Composition fromDomainToEntity(CompositionDTO compositionDTO, List<ElementComposition> elementCompositions){
        Composition composition = new Composition();
        composition.setId(compositionDTO.getId() == 0 ? null : compositionDTO.getId());
        composition.setDateCreation(compositionDTO.getDateCreation());
        composition.setDureeCreation(compositionDTO.getDureeCreation());
        composition.setPrixUnitaire(compositionDTO.getPrixUnitaire());
        composition.setElements(elementCompositions);
        return composition;
    }

    public CompositionDTO fromEntityToDomain(Composition composition, List<ElementCompositionDTO> elementCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(composition.getId());
        compositionDTO.setDateCreation(composition.getDateCreation());
        compositionDTO.setDureeCreation(composition.getDureeCreation());
        compositionDTO.setPrixUnitaire(composition.getPrixUnitaire());
        compositionDTO.setElementsComposition(elementCompositionDTOS);
        return compositionDTO;
    }
}
