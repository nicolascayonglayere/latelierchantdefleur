package fr.latelierchantdefleur.outilgestion.entites;

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
@Table(name = "utilisateur", schema = "chantdefleur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	private Integer idUtilisateur;
	@Column(name = "prenom", nullable = false)
	private String prenom;
	@Column(name = "nom", nullable = false)
	private String nom;
	@Column(name = "pseudo", nullable = false)
	private String pseudo;
	@Column(name = "mot_de_passe", nullable = false)
	private String mdp;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "id_coordonnee")
	private CoordonneesUtilisateur coordonneeUtilisateur;

	// private List<Commentaire> commentaires;
	// private List<Commande> commandes;

	public Utilisateur() {
	}

	public Utilisateur(String prenom, String nom, String pseudo, String mdp,
			CoordonneesUtilisateur coordonneeUtilisateur) {
		this.prenom = prenom;
		this.nom = nom;
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}

	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public CoordonneesUtilisateur getCoordonneeUtilisateur() {
		return this.coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneesUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}

	// public List<Commentaire> getCommentaires() {
	// return commentaires;
	// }
	//
	//
	// public void setCommentaires(List<Commentaire> commentaires) {
	// this.commentaires = commentaires;
	// }
	//
	//
	// public List<Commande> getCommandes() {
	// return commandes;
	// }
	//
	//
	// public void setCommandes(List<Commande> commandes) {
	// this.commandes = commandes;
	// }

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + this.idUtilisateur + ", prenom=" + this.prenom + ", nom=" + this.nom
				+ ", pseudo=" + this.pseudo + ", mdp=" + this.mdp + ", coordonneeUtilisateur="
				+ this.coordonneeUtilisateur + ", commentaires= , commandes= ]";
	}

}
