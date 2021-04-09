package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String nom1;
    private String prenom1;
    private String nom2;
    private String prenom2;
    private Integer budget;
    private String adresse;
    private String codePostal;
    private String ville;
    private String email;
    private String telephone;
    private List<CommandeDTO> evenementsDTO;

    public ClientDTO(Long id) {
        this.id = id;
    }

    public void addEvenement(CommandeDTO commandeDTO){
        if(Objects.isNull(this.evenementsDTO)){
            this.evenementsDTO = new ArrayList<>();
        }
        this.evenementsDTO.add(commandeDTO);
    }
}
