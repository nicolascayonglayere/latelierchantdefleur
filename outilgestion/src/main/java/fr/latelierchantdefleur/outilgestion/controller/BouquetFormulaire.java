package fr.latelierchantdefleur.outilgestion.controller;

import java.util.List;

import fr.latelierchantdefleur.outilgestion.entites.Fleur;
import fr.latelierchantdefleur.outilgestion.entites.Materiau;

public class BouquetFormulaire {

	private String saison;
	private Double prixUnitaire;
	private String couleur;
	private String taille;
	private String cheminImage;
	
	private List<Materiau> materiaux;
	
	private List<Fleur> fleursComp;

	private boolean compoFlorale;

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getCheminImage() {
		return cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}

	public List<Materiau> getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(List<Materiau> materiaux) {
		this.materiaux = materiaux;
	}

	public List<Fleur> getFleursComp() {
		return fleursComp;
	}

	public void setFleursComp(List<Fleur> fleursComp) {
		this.fleursComp = fleursComp;
	}

	public boolean isCompoFlorale() {
		return compoFlorale;
	}

	public void setCompoFlorale(boolean compoFlorale) {
		this.compoFlorale = compoFlorale;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}
	
	
}
