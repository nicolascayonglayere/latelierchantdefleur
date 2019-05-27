package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saison", schema = "chantdefleur")
public class Saison {
	@Id
	@Column(name = "id_saison")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "saison")
	private String saison;

	public Saison() {
	}

	public Saison(String saison) {
		this.saison = saison;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaison() {
		return this.saison;
	}

	public void setSaison(String string) {
		this.saison = string;
	}

}
