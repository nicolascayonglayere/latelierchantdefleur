package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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
	
	public CoordonneesUtilisateur() {}

	public CoordonneesUtilisateur(String numPortable, String email, String adresse, String ville, Integer codePostal) {
		this.numPortable = numPortable;
		this.email = email;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public Integer getIdCoordonneesUtilisateur() {
		return idCoordonneesUtilisateur;
	}

	public void setIdCoordonneesUtilisateur(Integer idCoordonneesUtilisateur) {
		this.idCoordonneesUtilisateur = idCoordonneesUtilisateur;
	}

	public String getNumPortable() {
		return numPortable;
	}

	public void setNumPortable(String numPortable) {
		this.numPortable = numPortable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "CoordonneesUtilisateur [idCoordonneesUtilisateur=" + idCoordonneesUtilisateur + ", numPortable="
				+ numPortable + ", email=" + email + ", adresse=" + adresse + ", ville=" + ville + ", codePostal="
				+ codePostal + "]";
	}
	
	
	
	
}
