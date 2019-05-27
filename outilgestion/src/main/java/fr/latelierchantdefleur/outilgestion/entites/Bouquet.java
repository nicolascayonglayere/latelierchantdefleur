package fr.latelierchantdefleur.outilgestion.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bouquet", schema = "chantdefleur")
public class Bouquet extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "id_bouquet")
	// private Integer idBouquet;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private Saison saison;
	@Column(name = "prix_unitaire", nullable = false)
	private Double prixUnitaire;
	@Column(name = "couleur", nullable = true)
	private String couleur;
	@Column(name = "taille", nullable = true)
	private String taille;
	@Column(name = "chemin_image", nullable = false)
	private String cheminImage;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinTable(name = "chantdefleur.bouquet_liste_materiau", joinColumns = @JoinColumn(name = "id_bouquet"), inverseJoinColumns = @JoinColumn(name = "id_materiau"))
	private List<Materiau> materiaux;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinTable(name = "chantdefleur.bouquet_liste_fleur", joinColumns = @JoinColumn(name = "id_bouquet"), inverseJoinColumns = @JoinColumn(name = "id_fleur"))
	private List<Fleur> fleursComp;
	@Column(name = "composition_florale", nullable = false)
	private boolean compoFlorale;

	public Bouquet() {
		super();
	}

	public Bouquet(Saison saison, Double prixUnitaire, String couleur, String taille, String cheminImage,
			boolean compoFlorale) {
		super();
		this.saison = saison;
		this.prixUnitaire = prixUnitaire;
		this.couleur = couleur;
		this.taille = taille;
		this.cheminImage = cheminImage;
		this.compoFlorale = compoFlorale;
	}

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

	// public Integer getIdBouquet() {
	// return idBouquet;
	// }
	//
	// public void setIdBouquet(Integer idBouquet) {
	// this.idBouquet = idBouquet;
	// }

	public Saison getSaison() {
		return this.saison;
	}

	public void setSaison(Saison saison) {
		this.saison = saison;
	}

	public Double getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getCouleur() {
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getTaille() {
		return this.taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public List<Materiau> getMateriaux() {
		return this.materiaux;
	}

	public void setMateriaux(List<Materiau> materiaux) {
		this.materiaux = materiaux;
	}

	public List<Fleur> getFleursComp() {
		return this.fleursComp;
	}

	public void setFleursComp(List<Fleur> fleursComp) {
		this.fleursComp = fleursComp;
	}

	public boolean isCompoFlorale() {
		return this.compoFlorale;
	}

	public void setCompoFlorale(boolean compoFlorale) {
		this.compoFlorale = compoFlorale;
	}

	@Override
	public String toString() {
		return "Bouquet [saison=" + this.saison + ", prixUnitaire=" + this.prixUnitaire + ", couleur=" + this.couleur
				+ ", taille=" + this.taille + ", materiaux=" + this.materiaux + ", fleursComp=" + this.fleursComp
				+ ", compoFlorale=" + this.compoFlorale + "]";
	}

	public String getCheminImage() {
		return this.cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}

}
