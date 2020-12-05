package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.EvenementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Evenement;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.EvenementRest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EvenementMapper {

    public Evenement fromDomainToEntity(EvenementDTO evenementDTO, List<Composition> compositions){
        Evenement evenement = new Evenement();
        evenement.setId(Objects.equals(0, evenementDTO.getId()) ? null : evenementDTO.getId());
        evenement.setNom(evenementDTO.getNom());
        evenement.setDateCreation(evenementDTO.getDateCreation());
        evenement.setDatePrevue(evenementDTO.getDatePrevue());
        evenement.setCompositions(compositions);
        evenement.setForfaitDplct(evenementDTO.getForfaitDplct());
        evenement.setForfaitMo(evenementDTO.getForfaitMo());
        return evenement;
    }

    public EvenementDTO fromEntityToDomain(Evenement evenement, List<CompositionDTO> compositionsDTO){
        EvenementDTO evenementDTO = new EvenementDTO();
        evenementDTO.setId(evenement.getId());
        evenementDTO.setNom(evenement.getNom());
        evenementDTO.setDateCreation(evenement.getDateCreation());
        evenementDTO.setDatePrevue(evenement.getDatePrevue());
        evenementDTO.setCompositions(compositionsDTO);
        evenementDTO.setForfaitDplct(evenement.getForfaitDplct());
        evenementDTO.setForfaitMo(evenement.getForfaitMo());
        return evenementDTO;
    }

    public EvenementDTO fromRestToDomain(EvenementRest evenementRest, List<CompositionDTO> compositionsDTO){
        EvenementDTO evenementDTO = new EvenementDTO();
        evenementDTO.setId(evenementRest.getId());
        evenementDTO.setNom(evenementRest.getNom());
        evenementDTO.setDateCreation(evenementRest.getDateCreation());
        evenementDTO.setDatePrevue(evenementRest.getDatePrevue());
        evenementDTO.setCompositions(compositionsDTO);
        evenementDTO.setForfaitMo(evenementRest.getForfaitMo());
        evenementDTO.setForfaitDplct(evenementRest.getForfaitDplct());
        return evenementDTO;
    }

    public EvenementRest fromDomainToRest(EvenementDTO evenementDTO, List<CompositionRest> compositionsRest){
        EvenementRest evenementRest = new EvenementRest();
        evenementRest.setId(evenementDTO.getId());
        evenementRest.setNom(evenementDTO.getNom());
        evenementRest.setDateCreation(evenementDTO.getDateCreation());
        evenementRest.setDatePrevue(evenementDTO.getDatePrevue());
        evenementRest.setCompositions(compositionsRest);
        evenementRest.setForfaitDplct(evenementDTO.getForfaitDplct());
        evenementRest.setForfaitMo(evenementDTO.getForfaitMo());
        return evenementRest;
    }
}
