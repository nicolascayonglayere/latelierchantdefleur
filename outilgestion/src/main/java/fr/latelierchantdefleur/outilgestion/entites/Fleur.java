package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "fleur", schema = "chantdefleur")
public class Fleur extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="id_fleur")
	// private Integer idFleur;
	@Column(name = "nom", nullable = false)
	private String nom;
	@Column(name = "mon_latin", nullable = true)
	private String nomLatin;
	@Column(name = "couleur", nullable = true)
	private String couleur;
	@Enumerated(EnumType.STRING)
	private Saison saison;

	public Fleur() {
		super();
	}

	public Fleur(String nom, String nomLatin, String couleur, Saison saison) {
		super();
		this.nom = nom;
		this.nomLatin = nomLatin;
		this.couleur = couleur;
		this.saison = saison;
	}

	// public Integer getIdFleur() {
	// return idFleur;
	// }
	//
	// public void setIdFleur(Integer idFleur) {
	// this.idFleur = idFleur;
	// }

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

	public Saison getSaison() {
		return this.saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	@Override
	public String toString() {
		return "Fleur [nom=" + this.nom + ", nomLatin=" + this.nomLatin + ", couleur=" + this.couleur + ", saison="
				+ this.saison + "]";
	}

}
