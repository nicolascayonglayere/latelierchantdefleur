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
    private String nom;
    private LocalDate dateCreation;
    private LocalTime dureeCreation;
    private Double prixUnitaire;
    private List<ElementDTO> elementsComposition;
    private List<ImageCompositionDTO> imagesComposition;
    private Integer tva;
    private Integer marge;
    private Double tauxHoraire;

    public void addElement(ElementDTO elementDTO){
        if(Objects.isNull(this.elementsComposition)){
            this.elementsComposition = new ArrayList<>();
        }
        this.elementsComposition.add(elementDTO);
    }

    public void addImageComposition(ImageCompositionDTO imageCompositionDTO){
        if(Objects.isNull(this.imagesComposition)){
            this.imagesComposition = new ArrayList<>();
        }
        this.imagesComposition.add(imageCompositionDTO);
    }
}
