package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="image_composition")
@Data
public class ImageComposition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="description")
    private String description;
    @Lob
    @Column(name="content", nullable = false, columnDefinition="BLOB")
    private byte[] content;
    @ManyToOne
    @JoinColumn(name = "composition_id", nullable = false)
    private Composition composition;

    @Override
    public String toString() {
        return "ImageComposition{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
