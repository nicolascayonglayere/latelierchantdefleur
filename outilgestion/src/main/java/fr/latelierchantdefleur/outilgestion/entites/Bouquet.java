package fr.latelierchantdefleur.outilgestion.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bouquet")
public class Bouquet extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bouquet")
	private Integer idBouquet;
	
	private Saison saison;
	@Column(name="prix_unitaire", nullable = false)
	private Double prixUnitaire;
	@Column(name="couleur", nullable = true)
	private String couleur;
	@Column(name="taille", nullable=true)
	private String taille;
	@Column(name="chemin_image", nullable=false)
	private String cheminImage;
	
	private List<Materiau> materiaux;
	
	private List<Fleur> fleursComp;
	@Column(name="composition_florale", nullable=false)
	private boolean compoFlorale;
	
	public Bouquet() {super();}



	public Bouquet(Saison saison, Double prixUnitaire, String couleur, String taille, String cheminImage,
			List<Materiau> materiaux, List<Fleur> fleursComp, boolean compoFlorale) {
		super();
		this.saison = saison;
		this.prixUnitaire = prixUnitaire;
		this.couleur = couleur;
		this.taille = taille;
		this.setCheminImage(cheminImage);
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



	public String getCheminImage() {
		return cheminImage;
	}



	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	
	
}
