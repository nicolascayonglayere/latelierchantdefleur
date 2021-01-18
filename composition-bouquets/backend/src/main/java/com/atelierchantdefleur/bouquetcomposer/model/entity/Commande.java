package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="commande")
@Data
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom", nullable = false)
    private String nom;
    @Column(name="date_creation", nullable = false)
    private LocalDate dateCreation;
    @Column(name="date_prevue", nullable = false)
    private LocalDate datePrevue;
    @Column(name="forfait_main_oeuvre", nullable = false, columnDefinition = "integer default 0")
    private Integer forfaitMo;
    @Column(name="forfait_deplacement", nullable = false, columnDefinition = "integer default 0")
    private Integer forfaitDplct;
    @Column(name="budget")
    private Double budget;
    @OneToMany(mappedBy = "commande")
    private List<CompositionsCommande> compositions;
//    @ManyToMany(mappedBy = "commandes")
////    @JoinTable(
////            name = "evenement_composition",
////            joinColumns = @JoinColumn(name = "commande_id"),
////            inverseJoinColumns = @JoinColumn(name = "composition_id"))
//    private List<Composition> compositions;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateCreation=" + dateCreation +
                ", datePrevue=" + datePrevue +
                ", forfaitMo=" + forfaitMo +
                ", forfaitDplct=" + forfaitDplct +
                ", compositions=" + compositions +
                '}';
    }
}
