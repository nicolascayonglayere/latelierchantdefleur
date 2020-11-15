package com.atelierchantdefleur.bouquetcomposer.model.domain;

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
    private List<ElementCompositionDTO> elementsComposition;

    public void addElement(ElementCompositionDTO elementCompositionDTO){
        if(Objects.isNull(this.elementsComposition)){
            this.elementsComposition = new ArrayList<>();
        }
        this.elementsComposition.add(elementCompositionDTO);
    }
}
