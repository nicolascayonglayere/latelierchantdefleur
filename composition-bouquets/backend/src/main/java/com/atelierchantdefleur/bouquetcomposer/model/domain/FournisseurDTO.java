package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDTO {

    private Long id;
    @NotBlank
    @NotEmpty
    private String nom;
    private String numeroSiret;
    private String adresse;
    private String codePostal;
    private String ville;
    private String email;
    private String telephone;
    private List<MateriauDTO> materiaux;
    private List<TigeDTO> tiges;

    public void addMateriau(MateriauDTO materiau){
        if(Objects.isNull(this.materiaux)){
            this.materiaux = new ArrayList<>();
        }
        this.materiaux.add(materiau);
        materiau.setFournisseurDTO(this);
    }

    public void addTige(TigeDTO tige){
        if(Objects.isNull(this.tiges)){
            this.tiges = new ArrayList<>();
        }
        this.tiges.add(tige);
        tige.setFournisseurDTO(this);
    }
}
