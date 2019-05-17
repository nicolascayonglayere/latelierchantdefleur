package fr.latelierchantdefleur.outilgestion.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="materiau")
public class Materiau extends Element{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id_materiau")
	//private Integer idMateriau;
	@Column(name="nom", nullable=false)
	private String nom;
	
	public Materiau() {super();}

	public Materiau(String nom) {
		super();
		this.nom = nom;
	}

	//public Integer getIdMateriau() {
	//	return idMateriau;
	//}
    //
	//public void setIdMateriau(Integer idMateriau) {
	//	this.idMateriau = idMateriau;
	//}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Materiau [nom=" + nom + "]";
	}
	
	
	
}
