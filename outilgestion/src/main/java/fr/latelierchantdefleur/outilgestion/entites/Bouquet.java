package fr.latelierchantdefleur.outilgestion.entites;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Bouquet {

	private Integer idBouquet;
	private Saison saison;
	private Double prixUnitaire;
	private String couleur;
	private String taille;
	private List<Materiau> materiaux;
	private List<Fleur> fleursComp;
	private boolean compoFlorale;
	
	public Bouquet() {}

	public Bouquet(Saison saison, Double prixUnitaire, String couleur, String taille, List<Materiau> materiaux,
			List<Fleur> fleursComp, boolean compoFlorale) {
		this.saison = saison;
		this.prixUnitaire = prixUnitaire;
		this.couleur = couleur;
		this.taille = taille;
		this.materiaux = materiaux;
		this.fleursComp = fleursComp;
		this.compoFlorale = compoFlorale;
	}

	public Integer getIdBouquet() {
		return idBouquet;
	}

	public void setIdBouquet(Integer idBouquet) {
		this.idBouquet = idBouquet;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

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

	@Override
	public String toString() {
		return "Bouquet [idBouquet=" + idBouquet + ", saison=" + saison + ", prixUnitaire=" + prixUnitaire
				+ ", couleur=" + couleur + ", taille=" + taille + ", materiaux=" + materiaux + ", fleursComp="
				+ fleursComp + ", compoFlorale=" + compoFlorale + "]";
	}
	
	
}
