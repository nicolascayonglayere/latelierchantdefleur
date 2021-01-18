package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom_1", nullable = false)
    private String nom1;
    @Column(name="prenom_1")
    private String prenom1;
    @Column(name="nom_2")
    private String nom2;
    @Column(name="prenom_2")
    private String prenom2;
    @Column(name = "adresse")
    private String adresse;
    @Column (name = "code_postal")
    private String codePostal;
    @Column (name="ville")
    private String ville;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="telephone")
    private String telephone;
    @OneToMany(mappedBy = "client")
    List<Commande> commandes;

    public void addCommande(Commande commande){
        if(Objects.isNull(this.commandes)){
            this.commandes = new ArrayList<>();
        }
        this.commandes.add(commande);
        commande.setClient(this);
    }
}
