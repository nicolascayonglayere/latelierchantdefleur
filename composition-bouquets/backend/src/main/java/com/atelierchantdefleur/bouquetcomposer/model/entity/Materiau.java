package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="materiau")
@Data
@PrimaryKeyJoinColumn(name = "element_id")
public class Materiau extends Element implements Serializable {

    @ManyToOne
    @JoinColumn(name="fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

}
