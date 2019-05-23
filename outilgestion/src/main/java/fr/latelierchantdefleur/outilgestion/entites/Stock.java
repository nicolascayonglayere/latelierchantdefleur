package fr.latelierchantdefleur.outilgestion.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock", schema = "chantdefleur")
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stock")
	private Integer idStock;
	private Date dateEntree;
	private Date dateSortie;
	private Integer quantite;
	private Double prixAchat;
	private Integer dureeConservation;

	public Stock() {
	}

	public Stock(Date dateEntree, Date dateSortie, Integer quantite, Double prixAchat, Integer dureeConservation) {
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.quantite = quantite;
		this.prixAchat = prixAchat;
		this.dureeConservation = dureeConservation;
	}

	public Integer getIdStock() {
		return this.idStock;
	}

	public void setIdStock(Integer idStock) {
		this.idStock = idStock;
	}

	public Date getDateEntree() {
		return this.dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateSortie() {
		return this.dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Double getPrixAchat() {
		return this.prixAchat;
	}

	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Integer getDureeConservation() {
		return this.dureeConservation;
	}

	public void setDureeConservation(Integer dureeConservation) {
		this.dureeConservation = dureeConservation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Stock [idStock=" + this.idStock + ", dateEntree=" + this.dateEntree + ", dateSortie=" + this.dateSortie
				+ ", quantite=" + this.quantite + ", prixAchat=" + this.prixAchat + ", dureeConservation="
				+ this.dureeConservation + "]";
	}

}
