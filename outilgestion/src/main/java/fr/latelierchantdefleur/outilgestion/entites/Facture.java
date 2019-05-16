package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Facture {
	
	private Integer idFacture;
	private Date dateFacture;
	private Date datePaiement;
	private Double montant;
	private Commande commande;
	private boolean acquitee;
	private TypePaiement typePaiement;
	
	public Facture() {}

	public Facture(Date dateFacture, Date datePaiement, Double montant, Commande commande, boolean acquitee,
			TypePaiement typePaiement) {
		this.dateFacture = dateFacture;
		this.datePaiement = datePaiement;
		this.montant = montant;
		this.commande = commande;
		this.acquitee = acquitee;
		this.typePaiement = typePaiement;
	}

	public Integer getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(Integer idFacture) {
		this.idFacture = idFacture;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public boolean isAcquitee() {
		return acquitee;
	}

	public void setAcquitee(boolean acquitee) {
		this.acquitee = acquitee;
	}

	public TypePaiement getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(TypePaiement typePaiement) {
		this.typePaiement = typePaiement;
	}

	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", dateFacture=" + dateFacture + ", datePaiement=" + datePaiement
				+ ", montant=" + montant + ", commande=" + commande + ", acquitee=" + acquitee + ", typePaiement="
				+ typePaiement + "]";
	}
	
	
	
}
