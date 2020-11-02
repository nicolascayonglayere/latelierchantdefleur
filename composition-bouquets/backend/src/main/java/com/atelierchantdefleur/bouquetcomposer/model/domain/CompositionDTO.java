package com.atelierchantdefleur.bouquetcomposer.model.domain;

import com.atelierchantdefleur.bouquetcomposer.model.entity.Materiau;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Tige;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionDTO {

    private Long id;
    private LocalDate dateCreation;
    private LocalTime dureeCreation;
    private Integer prixUnitaire;
    private List<Tige> tiges;
    private List<Materiau> materiaux;

    public void addTige(Tige tige){
        if(Objects.isNull(this.tiges)){
            this.tiges = new ArrayList<>();
        }
        this.tiges.add(tige);
    }

    public void addMateriau(Materiau materiau){
        if(Objects.isNull(this.materiaux)){
            this.materiaux = new ArrayList<>();
        }
        this.materiaux.add(materiau);
    }
}
