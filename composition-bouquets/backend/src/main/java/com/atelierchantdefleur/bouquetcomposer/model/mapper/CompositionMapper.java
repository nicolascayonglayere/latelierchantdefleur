package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ImageCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Component
public class CompositionMapper {

    public CompositionDTO fromRestToDomain(CompositionRest compositionRest, List<ElementDTO> elementDTOS, List<ImageCompositionDTO> imageCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(Objects.isNull(compositionRest.getId()) ? null : compositionRest.getId());
        compositionDTO.setNom(compositionRest.getNom());
        compositionDTO.setDateCreation(compositionRest.getDateCreation());
        compositionDTO.setDureeCreation(LocalTime.ofSecondOfDay((long) (compositionRest.getDureeCreation() * 3600)));
        compositionDTO.setPrixUnitaire(compositionRest.getPrixUnitaire());
        compositionDTO.setElementsComposition(elementDTOS);
        compositionDTO.setImagesComposition(imageCompositionDTOS);
        compositionDTO.setTva(compositionRest.getTva());
        compositionDTO.setTauxHoraire(compositionRest.getTauxHoraire());
        compositionDTO.setMarge(compositionRest.getMarge());
        return compositionDTO;
    }

    public CompositionRest fromDomainToRest(CompositionDTO compositionDTO, List<ElementRest> elementRests, List<ImageCompositionRest> imageComposition){
        CompositionRest compositionRest = new CompositionRest();
        compositionRest.setId(compositionDTO.getId());
        compositionRest.setNom(compositionDTO.getNom());
        compositionRest.setDateCreation(compositionDTO.getDateCreation());
        compositionRest.setPrixUnitaire(compositionDTO.getPrixUnitaire());
        compositionRest.setDureeCreation(Duration.between(LocalTime.MIDNIGHT, compositionDTO.getDureeCreation()).toMinutes()/60d);//float)compositionDTO.getDureeCreation().getSecond()/3600);
        compositionRest.setElements(elementRests);
        compositionRest.setImages(imageComposition);
        compositionRest.setTva(compositionDTO.getTva());
        compositionRest.setMarge(compositionDTO.getMarge());
        compositionRest.setTauxHoraire(compositionDTO.getTauxHoraire());
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
        composition.setTva(compositionDTO.getTva());
        composition.setTauxHoraire(compositionDTO.getTauxHoraire());
        composition.setMarge(compositionDTO.getMarge());
        return composition;
    }

    public CompositionDTO fromEntityToDomain(Composition composition, List<ElementDTO> elementDTOS, List<ImageCompositionDTO> imageCompositionDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(composition.getId());
        compositionDTO.setNom(composition.getNom());
        compositionDTO.setDateCreation(composition.getDateCreation());
        compositionDTO.setDureeCreation(composition.getDureeCreation());
        compositionDTO.setPrixUnitaire(composition.getPrixUnitaire());
        compositionDTO.setElementsComposition(elementDTOS);
        compositionDTO.setImagesComposition(imageCompositionDTOS);
        compositionDTO.setTva(composition.getTva());
        compositionDTO.setMarge(composition.getMarge());
        compositionDTO.setTauxHoraire(composition.getTauxHoraire());
        return compositionDTO;
    }
}
