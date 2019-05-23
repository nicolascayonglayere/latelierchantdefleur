package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;

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
@Table(name = "facture", schema = "chantdefleur")
public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_facture")
	private Integer idFacture;
	@Column(name = "date_facture", nullable = false)
	private Date dateFacture;
	@Column(name = "date_paiement", nullable = true)
	private Date datePaiement;
	@Column(name = "montant", nullable = false)
	private Double montant;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_commande")
	private Commande commande;
	@Column(name = "acquitte", nullable = false)
	private boolean acquitee;
	@Enumerated(EnumType.STRING)
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
