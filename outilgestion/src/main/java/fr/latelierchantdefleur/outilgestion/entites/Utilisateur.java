package fr.latelierchantdefleur.outilgestion.entites;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Integer idUtilisateur;
	private String prenom;
	private String nom;
	private String pseudo;
	private String mdp;
	private CoordonneesUtilisateur coordonneeUtilisateur;
	private List<Commentaire> commentaires;
	private List<Commande> commandes;
	
	
	public Utilisateur() {}


	public Utilisateur(String prenom, String nom, String pseudo, String mdp,
			CoordonneesUtilisateur coordonneeUtilisateur) {
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}


	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public CoordonneesUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}


	public void setCoordonneeUtilisateur(CoordonneesUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}


	public List<Commentaire> getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}


	public List<Commande> getCommandes() {
		return commandes;
	}


	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}


	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", prenom=" + prenom + ", nom=" + nom + ", pseudo="
				+ pseudo + ", mdp=" + mdp + ", coordonneeUtilisateur=" + coordonneeUtilisateur + ", commentaires="
				+ commentaires + ", commandes=" + commandes + "]";
	}
	
	
	
}
