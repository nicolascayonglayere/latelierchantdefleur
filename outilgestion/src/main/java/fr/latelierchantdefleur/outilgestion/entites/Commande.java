package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commande", schema = "chantdefleur")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_commande")
	private Integer idCommande;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;

	// private List<Bouquet> bouquets;
	@Column(name = "date", nullable = false)
	private Date dateCommande;
	@Enumerated(EnumType.STRING)
	private StatutCommande statut;

	public Commande() {
	}

	public Commande(Utilisateur utilisateur, List<Bouquet> bouquets, Date dateCommande, StatutCommande statut) {
		this.utilisateur = utilisateur;
		// this.bouquets = bouquets;
		this.dateCommande = dateCommande;
		this.statut = statut;
	}

	public Integer getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	// public List<Bouquet> getBouquets() {
	// return this.bouquets;
	// }
	//
	// public void setBouquets(List<Bouquet> bouquets) {
	// this.bouquets = bouquets;
	// }

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public StatutCommande getStatut() {
		return this.statut;
	}

	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + this.idCommande + ", utilisateur=" + this.utilisateur
				+ ", bouquets= , dateCommande=" + this.dateCommande + ", statut=" + this.statut + "]";
	}

}
