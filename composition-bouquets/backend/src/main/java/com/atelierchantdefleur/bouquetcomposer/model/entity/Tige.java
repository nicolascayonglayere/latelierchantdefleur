package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tige")
@Data
@PrimaryKeyJoinColumn(name = "element_id")
public class Tige extends ElementComposition implements Serializable{

    @Column(name = "nom_latin")
    private String nomLatin;

    @ManyToOne
    @JoinColumn(name="fournisseur_id", nullable = false)
    private Fournisseur fournisseur;
}
