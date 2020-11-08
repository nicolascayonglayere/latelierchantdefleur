package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="fournisseur")
@Data
public class Fournisseur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom", nullable = false)
    private String nom;
    @Column(name="numero_siret")
    private String numeroSiret;
    @Column(name = "adresse")
    private String adresse;
    @Column (name = "code_postal")
    private String codePostal;
    @Column (name="ville")
    private String ville;
    @Column(name="email")
    private String email;
    @Column(name="telephone")
    private String telephone;
    @OneToMany(mappedBy = "fournisseur")
    private List<Materiau> materiaux;
    @OneToMany(mappedBy = "fournisseur")
    private List<Tige> tiges;

    public void addMateriau(Materiau materiau){
        if(Objects.isNull(this.materiaux)){
            this.materiaux = new ArrayList<>();
        }
        this.materiaux.add(materiau);
        materiau.setFournisseur(this);
    }

    public void addTige(Tige tige){
        if(Objects.isNull(this.tiges)){
            this.tiges = new ArrayList<>();
        }
        this.tiges.add(tige);
        tige.setFournisseur(this);
    }

}
