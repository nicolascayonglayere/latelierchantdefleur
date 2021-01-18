package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ClientRest {
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
    private List<CommandeRest> evenementsRest;

    public void addEvenement(CommandeRest commandeRest){
        if(Objects.isNull(this.evenementsRest)){
            this.evenementsRest = new ArrayList<>();
        }
        this.evenementsRest.add(commandeRest);
    }
}
