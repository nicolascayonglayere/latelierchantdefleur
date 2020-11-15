package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="materiau")
@Data
@PrimaryKeyJoinColumn(name = "element_id")
public class Materiau extends ElementComposition implements Serializable {

    @ManyToOne
    @JoinColumn(name="fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

}
