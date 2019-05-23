package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coordonnee_utilisateur", schema = "chantdefleur")
public class CoordonneesUtilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coordonnee")
	private Integer idCoordonneesUtilisateur;
	private String numPortable;
	private String email;
	private String adresse;
	private String ville;
	private Integer codePostal;

	public CoordonneesUtilisateur() {
	}

	public CoordonneesUtilisateur(String numPortable, String email, String adresse, String ville, Integer codePostal) {
		this.numPortable = numPortable;
		this.email = email;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public Integer getIdCoordonneesUtilisateur() {
		return this.idCoordonneesUtilisateur;
	}

	public void setIdCoordonneesUtilisateur(Integer idCoordonneesUtilisateur) {
		this.idCoordonneesUtilisateur = idCoordonneesUtilisateur;
	}

	public String getNumPortable() {
		return this.numPortable;
	}

	public void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "CoordonneesUtilisateur [idCoordonneesUtilisateur=" + this.idCoordonneesUtilisateur + ", numPortable="
				+ this.numPortable + ", email=" + this.email + ", adresse=" + this.adresse + ", ville=" + this.ville
				+ ", codePostal=" + this.codePostal + "]";
	}

}
