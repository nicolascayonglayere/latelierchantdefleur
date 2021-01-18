package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDTO {
    private Long id;
    private String nom;
    private LocalDate dateCreation;
    private LocalDate datePrevue;
    private List<CompositionCommandeDTO> compositions;
    private Integer forfaitMo;
    private Integer forfaitDplct;
    private ClientDTO clientDTO;
    private Double budget;

    public void addComposition(CompositionCommandeDTO compositionDTO){
        if(Objects.isNull(this.compositions)){
            this.compositions = new ArrayList<>();
        }
        this.compositions.add(compositionDTO);
    }
}
