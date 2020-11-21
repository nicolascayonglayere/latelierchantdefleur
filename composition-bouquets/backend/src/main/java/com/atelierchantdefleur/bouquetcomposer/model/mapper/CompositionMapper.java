package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ImageCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementCompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
public class CompositionMapper {

    public CompositionDTO fromRestToDomain(CompositionRest compositionRest, List<ElementCompositionDTO> elementCompositionDTOS, List<ImageCompositionDTO> imageCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(compositionRest.getId());
        compositionDTO.setNom(compositionRest.getNom());
        compositionDTO.setDateCreation(compositionRest.getDateCreation());
        compositionDTO.setDureeCreation(LocalTime.ofSecondOfDay((long) (compositionRest.getDureeCreation() * 3600)));
        compositionDTO.setPrixUnitaire((int) (compositionRest.getPrixUnitaire() * 100));
        compositionDTO.setElementsComposition(elementCompositionDTOS);
        compositionDTO.setImagesComposition(imageCompositionDTOS);
        return compositionDTO;
    }

    public CompositionRest fromDomainToRest(CompositionDTO compositionDTO, List<ElementCompositionRest> elementCompositionRests, List<ImageCompositionRest> imageComposition){
        CompositionRest compositionRest = new CompositionRest();
        compositionRest.setId(compositionDTO.getId());
        compositionRest.setNom(compositionDTO.getNom());
        compositionRest.setDateCreation(compositionDTO.getDateCreation());
        compositionRest.setPrixUnitaire((float)compositionDTO.getPrixUnitaire() / 100);
        compositionRest.setDureeCreation((float)Duration.between(LocalTime.MIDNIGHT, compositionDTO.getDureeCreation()).toMinutes()/60);//float)compositionDTO.getDureeCreation().getSecond()/3600);
        compositionRest.setElements(elementCompositionRests);
        compositionRest.setImages(imageComposition);
        return compositionRest;
    }

    public Composition fromDomainToEntity(CompositionDTO compositionDTO, List<ElementComposition> elementCompositions, List<ImageComposition> imageCompositions){
        Composition composition = new Composition();
        composition.setId(compositionDTO.getId() == 0 ? null : compositionDTO.getId());
        composition.setNom(compositionDTO.getNom());
        composition.setDateCreation(compositionDTO.getDateCreation());
        composition.setDureeCreation(compositionDTO.getDureeCreation());
        composition.setPrixUnitaire(compositionDTO.getPrixUnitaire());
        composition.setElements(elementCompositions);
        composition.setImages(imageCompositions);
        return composition;
    }

    public CompositionDTO fromEntityToDomain(Composition composition, List<ElementCompositionDTO> elementCompositionDTOS, List<ImageCompositionDTO> imageCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(composition.getId());
        compositionDTO.setNom(composition.getNom());
        compositionDTO.setDateCreation(composition.getDateCreation());
        compositionDTO.setDureeCreation(composition.getDureeCreation());
        compositionDTO.setPrixUnitaire(composition.getPrixUnitaire());
        compositionDTO.setElementsComposition(elementCompositionDTOS);
        compositionDTO.setImagesComposition(imageCompositionDTOS);
        return compositionDTO;
    }
}
