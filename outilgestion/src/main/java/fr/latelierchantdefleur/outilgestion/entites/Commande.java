package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Commande {

	
	private Integer idCommande;
	private Utilisateur utilisateur;
	private List<Bouquet> bouquets;
	private Date dateCommande;
	private StatutCommande statut;
	
	
	public Commande() {}


	public Commande(Utilisateur utilisateur, List<Bouquet> bouquets, Date dateCommande, StatutCommande statut) {
		this.utilisateur = utilisateur;
		this.bouquets = bouquets;
		this.dateCommande = dateCommande;
		this.statut = statut;
	}


	public Integer getIdCommande() {
		return idCommande;
	}


	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public List<Bouquet> getBouquets() {
		return bouquets;
	}


	public void setBouquets(List<Bouquet> bouquets) {
		this.bouquets = bouquets;
	}


	public Date getDateCommande() {
		return dateCommande;
	}


	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}


	public StatutCommande getStatut() {
		return statut;
	}


	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}


	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", utilisateur=" + utilisateur + ", bouquets=" + bouquets
				+ ", dateCommande=" + dateCommande + ", statut=" + statut + "]";
	}
	
	
	
	
	
}
