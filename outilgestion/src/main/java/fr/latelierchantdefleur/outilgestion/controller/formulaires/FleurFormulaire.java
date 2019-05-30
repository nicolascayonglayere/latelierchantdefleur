package fr.latelierchantdefleur.outilgestion.controller.formulaires;

public class FleurFormulaire {

	private String nom;
	private String nomLatin;
	private String couleur;
	private String saison;

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomLatin() {
		return this.nomLatin;
	}

	public void setNomLatin(String nomLatin) {
		this.nomLatin = nomLatin;
	}

	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getSaison() {
		return this.saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

}
