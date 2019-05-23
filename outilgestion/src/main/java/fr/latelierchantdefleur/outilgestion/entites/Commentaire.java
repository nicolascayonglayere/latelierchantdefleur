package fr.latelierchantdefleur.outilgestion.entites;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commentaire", schema = "chantdefleur")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_commentaire")
	private Integer idCommentaire;
	@Column(name = "commentaire", nullable = false)
	private String commentaire;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur auteur;
	@Column(name = "date", nullable = false)
	private Date dateCommentaire;

	public Commentaire() {
	}

	public Commentaire(String commentaire, Utilisateur auteur, Date dateCommentaire) {
		this.commentaire = commentaire;
		this.auteur = auteur;
		this.dateCommentaire = dateCommentaire;
	}

	public Integer getIdCommentaire() {
		return this.idCommentaire;
	}

	public void setIdCommentaire(Integer idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Utilisateur getAuteur() {
		return this.auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Date getDateCommentaire() {
		return this.dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	@Override
	public String toString() {
		return "Commentaire [idCommentaire=" + this.idCommentaire + ", commentaire=" + this.commentaire + ", auteur="
				+ this.auteur + ", dateCommentaire=" + this.dateCommentaire + "]";
	}

}
