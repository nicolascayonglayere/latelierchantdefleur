package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Commentaire {

	private Integer idCommentaire;
	private String commentaire;
	private Utilisateur auteur;
	private Date dateCommentaire;
	
	
	public Commentaire() {}


	public Commentaire(String commentaire, Utilisateur auteur, Date dateCommentaire) {
		this.commentaire = commentaire;
		this.auteur = auteur;
		this.dateCommentaire = dateCommentaire;
	}


	public Integer getIdCommentaire() {
		return idCommentaire;
	}


	public void setIdCommentaire(Integer idCommentaire) {
		this.idCommentaire = idCommentaire;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public Utilisateur getAuteur() {
		return auteur;
	}


	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}


	public Date getDateCommentaire() {
		return dateCommentaire;
	}


	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}


	@Override
	public String toString() {
		return "Commentaire [idCommentaire=" + idCommentaire + ", commentaire=" + commentaire + ", auteur=" + auteur
				+ ", dateCommentaire=" + dateCommentaire + "]";
	}
	
	
}
