package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Entity;

@Entity
public class Fleur extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idFleur;
	private String nom;
	private String nomLatin;
	private String couleur;
	private Double prixAchat;
	private Saison saison;
	
	public Fleur() {}

	public Fleur(String nom, String nomLatin, String couleur, Double prixAchat, Saison saison) {
		this.nom = nom;
		this.nomLatin = nomLatin;
		this.couleur = couleur;
		this.prixAchat = prixAchat;
		this.saison = saison;
	}

	public Integer getIdFleur() {
		return idFleur;
	}

	public void setIdFleur(Integer idFleur) {
		this.idFleur = idFleur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomLatin() {
		return nomLatin;
	}

	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Double getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	@Override
	public String toString() {
		return "Fleur [idFleur=" + idFleur + ", nom=" + nom + ", nomLatin=" + nomLatin + ", couleur=" + couleur
				+ ", prixAchat=" + prixAchat + ", saison=" + saison + "]";
	}
	
	
}
