package fr.latelierchantdefleur.outilgestion.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Element implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_element")
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
