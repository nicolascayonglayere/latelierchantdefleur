package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionCommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.CompositionsCommande;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionCommandeRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CommandeCompositionMapper {

    public CompositionCommandeDTO fromRestToDomain(CompositionCommandeRest compositionCommandeRest, CompositionDTO compositionDTO){
        return new CompositionCommandeDTO(
                compositionCommandeRest.getId(),
                compositionDTO,
                compositionCommandeRest.getQuantite()
        );
    }

    public CompositionCommandeRest fromDomainToRest(CompositionCommandeDTO compositionCommandeDTO, CompositionRest compositionRest){
        return new CompositionCommandeRest(
                compositionCommandeDTO.getId(),
                compositionRest,
                compositionCommandeDTO.getQuantite()
        );
    }

    public CompositionsCommande fromDomainToEntity(CompositionCommandeDTO compositionCommandeDTO, Composition composition, Commande commande){
        CompositionsCommande compositionsCommande =  new CompositionsCommande();
        compositionsCommande.setId(Objects.isNull(compositionCommandeDTO.getId()) ? null : compositionCommandeDTO.getId());
        compositionsCommande.setCommande(commande);
        compositionsCommande.setComposition(composition);
        compositionsCommande.setQuantite(compositionCommandeDTO.getQuantite());
        return compositionsCommande;
    }

    public CompositionCommandeDTO fromEntityToDomain(CompositionsCommande compositionsCommande, CompositionDTO compositionDTO){
        return new CompositionCommandeDTO(
                compositionsCommande.getId(),
                compositionDTO,
                compositionsCommande.getQuantite()
        );
    }
}
