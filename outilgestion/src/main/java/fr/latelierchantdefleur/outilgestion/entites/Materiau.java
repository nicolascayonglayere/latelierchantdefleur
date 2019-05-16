package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Entity;

@Entity
public class Materiau {

	private Integer idMateriau;
	private String nom;
	private Double prixAchat;
	
	public Materiau() {}

	public Materiau(String nom, Double prixAchat) {
		this.nom = nom;
		this.prixAchat = prixAchat;
	}

	public Integer getIdMateriau() {
		return idMateriau;
	}

	public void setIdMateriau(Integer idMateriau) {
		this.idMateriau = idMateriau;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}

	@Override
	public String toString() {
		return "Materiau [idMateriau=" + idMateriau + ", nom=" + nom + ", prixAchat=" + prixAchat + "]";
	}
	
	
	
}
