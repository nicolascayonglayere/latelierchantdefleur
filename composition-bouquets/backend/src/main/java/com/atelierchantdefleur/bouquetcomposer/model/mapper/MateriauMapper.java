package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.MateriauDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.rest.DataTablesMateriau;
import com.atelierchantdefleur.bouquetcomposer.model.rest.MateriauRest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MateriauMapper {

    public MateriauDTO fromEntityToDomain(Materiau materiau){
        return new MateriauDTO(
            materiau.getId(),
            materiau.getNom(),
            materiau.getPrixUnitaire()
        );
    }

    public MateriauRest fromDomainToRest(MateriauDTO materiauDTO){
        MateriauRest materiauRest = new MateriauRest();
        materiauRest.setId(materiauDTO.getId());
        materiauRest.setNom(materiauDTO.getNom());
        materiauRest.setPrixUnitaire(materiauDTO.getPrixUnitaire());
        return materiauRest;
    }

    public DataTablesMateriau fromDomainToRest(List<MateriauDTO> materiauDTOS){
        DataTablesMateriau dataTablesMateriau = new DataTablesMateriau();
        dataTablesMateriau.setData(materiauDTOS.stream().map(this::fromDomainToRest).collect(Collectors.toList()));
        dataTablesMateriau.setRecordsTotal(materiauDTOS.size());
        dataTablesMateriau.setDraw(1);
        dataTablesMateriau.setRecordsFiltered(0);
        return dataTablesMateriau;
    }
}
