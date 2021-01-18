package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
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

    public void addEvenement(CommandeDTO commandeDTO){
        if(Objects.isNull(this.evenementsDTO)){
            this.evenementsDTO = new ArrayList<>();
        }
        this.evenementsDTO.add(commandeDTO);
    }
}
