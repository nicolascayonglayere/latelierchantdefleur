package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fleur")
public class Fleur extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fleur")
	private Integer idFleur;
	@Column(name="nom", nullable=false)
	private String nom;
	@Column(name="mon_latin", nullable = true)
	private String nomLatin;
	@Column (name="couleur", nullable = true)
	private String couleur;
	
	private Saison saison;
	
	public Fleur() {super();}

	public Fleur(String nom, String nomLatin, String couleur, Saison saison) {
		super();
		this.nom = nom;
		this.nomLatin = nomLatin;
		this.couleur = couleur;
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

	public Saison getSaison() {
		return saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	@Override
	public String toString() {
		return "Fleur [idFleur=" + idFleur + ", nom=" + nom + ", nomLatin=" + nomLatin + ", couleur=" + couleur
				+ ", saison=" + saison + "]";
	}
	
	
}
