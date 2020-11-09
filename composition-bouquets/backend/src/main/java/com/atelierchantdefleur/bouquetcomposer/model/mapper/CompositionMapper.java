package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.TigeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.MateriauRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.TigeRest;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
public class CompositionMapper {

    public CompositionDTO fromRestToDomain(CompositionRest compositionRest, List<TigeDTO> tigeDTOS, List<MateriauDTO> materiauDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(compositionRest.getId());
        compositionDTO.setDateCreation(compositionRest.getDateCreation());
        compositionDTO.setDureeCreation(LocalTime.ofSecondOfDay((long) (compositionRest.getDureeCreation() * 3600)));
        compositionDTO.setPrixUnitaire((int) (compositionRest.getPrixUnitaire() * 100));
        compositionDTO.setTiges(tigeDTOS);
        compositionDTO.setMateriaux(materiauDTOS);
        return compositionDTO;
    }

    public CompositionRest fromDomainToRest(CompositionDTO compositionDTO, List<TigeRest> tigeRests, List<MateriauRest> materiauRests){
        CompositionRest compositionRest = new CompositionRest();
        compositionRest.setId(compositionDTO.getId());
        compositionRest.setDateCreation(compositionDTO.getDateCreation());
        compositionRest.setPrixUnitaire((float)compositionDTO.getPrixUnitaire() / 100);
        compositionRest.setDureeCreation((float)Duration.between(LocalTime.MIDNIGHT, compositionDTO.getDureeCreation()).toMinutes()/60);//float)compositionDTO.getDureeCreation().getSecond()/3600);
        compositionRest.setTiges(tigeRests);
        compositionRest.setMateriaux(materiauRests);
        return compositionRest;
    }

    public Composition fromDomainToEntity(CompositionDTO compositionDTO, List<Tige> tiges, List<Materiau> materiaux){
        Composition composition = new Composition();
        composition.setId(compositionDTO.getId() == 0 ? null : compositionDTO.getId());
        composition.setDateCreation(compositionDTO.getDateCreation());
        composition.setDureeCreation(compositionDTO.getDureeCreation());
        composition.setPrixUnitaire(compositionDTO.getPrixUnitaire());
        composition.setTiges(tiges);
        composition.setMateriaux(materiaux);
        return composition;
    }

    public CompositionDTO fromEntityToDomain(Composition composition, List<TigeDTO> tigeDTOS, List<MateriauDTO> materiauDTOS){
        CompositionDTO compositionDTO = new CompositionDTO();
        compositionDTO.setId(composition.getId());
        compositionDTO.setDateCreation(composition.getDateCreation());
        compositionDTO.setDureeCreation(composition.getDureeCreation());
        compositionDTO.setPrixUnitaire(composition.getPrixUnitaire());
        compositionDTO.setTiges(tigeDTOS);
        compositionDTO.setMateriaux(materiauDTOS);
        return compositionDTO;
    }
}
