package fr.latelierchantdefleur.outilgestion.entites;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Element implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idElement;
	private Commentaire commentaire;
	private Stock stock;
	
	
	public Element() {}


	public Element(Commentaire commentaire, Stock stock) {
		this.commentaire = commentaire;
		this.stock = stock;
	}


	public Integer getIdElement() {
		return idElement;
	}


	public void setIdElement(Integer idElement) {
		this.idElement = idElement;
	}


	public Commentaire getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Element [idElement=" + idElement + ", commentaire=" + commentaire + ", stock=" + stock + "]";
	}	
}
