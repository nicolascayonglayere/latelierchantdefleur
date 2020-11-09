package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.MateriauRest;
import org.springframework.stereotype.Component;

@Component
public class MateriauMapper {

    public MateriauDTO fromEntityToDomain(Materiau materiau, FournisseurDTO fournisseurDTO){
        return new MateriauDTO(
            materiau.getId(),
            materiau.getNom(),
            materiau.getPrixUnitaire(),
            fournisseurDTO
        );
    }

    public MateriauRest fromDomainToRest(MateriauDTO materiauDTO, FournisseurRest fournisseurRest){
        MateriauRest materiauRest = new MateriauRest();
        materiauRest.setId(materiauDTO.getId());
        materiauRest.setNom(materiauDTO.getNom());
        materiauRest.setPrixUnitaire(materiauDTO.getPrixUnitaire());
        materiauRest.setFournisseurRest(fournisseurRest);
        return materiauRest;
    }

    public Materiau fromDomainToEntity(MateriauDTO materiauDTO){
        Materiau materiau = new Materiau();
        materiau.setId(materiauDTO.getId() == 0 ? null : materiauDTO.getId());
        materiau.setNom(materiauDTO.getNom());
        materiau.setPrixUnitaire(materiauDTO.getPrixUnitaire());
        return materiau;
    }

    public MateriauDTO fromRestToDomainWithFournisseur(MateriauRest materiauRest, FournisseurDTO fournisseurDTO){
        return new MateriauDTO(
                materiauRest.getId(),
                materiauRest.getNom(),
                materiauRest.getPrixUnitaire(),
                fournisseurDTO
        );
    }

    public MateriauDTO fromRestToDomainWithoutFournisseur(MateriauRest materiauRest){
        return new MateriauDTO(
                materiauRest.getId(),
                materiauRest.getNom(),
                materiauRest.getPrixUnitaire(),
                null
        );
    }
}
