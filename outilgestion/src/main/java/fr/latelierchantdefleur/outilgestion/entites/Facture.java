package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_facture")
	private Integer idFacture;
	private Date dateFacture;
	private Date datePaiement;
	private Double montant;
	private Commande commande;
	private boolean acquitee;
	private TypePaiement typePaiement;

	public Facture() {
	}

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
		return this.idFacture;
	}

	public void setIdFacture(Integer idFacture) {
		this.idFacture = idFacture;
	}

	public Date getDateFacture() {
		return this.dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Date getDatePaiement() {
		return this.datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Double getMontant() {
		return this.montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public boolean isAcquitee() {
		return this.acquitee;
	}

	public void setAcquitee(boolean acquitee) {
		this.acquitee = acquitee;
	}

	public TypePaiement getTypePaiement() {
		return this.typePaiement;
	}

	public void setTypePaiement(TypePaiement typePaiement) {
		this.typePaiement = typePaiement;
	}

	@Override
	public String toString() {
		return "Facture [idFacture=" + this.idFacture + ", dateFacture=" + this.dateFacture + ", datePaiement="
				+ this.datePaiement + ", montant=" + this.montant + ", commande=" + this.commande + ", acquitee="
				+ this.acquitee + ", typePaiement=" + this.typePaiement + "]";
	}

}
