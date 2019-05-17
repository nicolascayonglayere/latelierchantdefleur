package fr.latelierchantdefleur.outilgestion.manager.contract;

import java.util.List;

import fr.latelierchantdefleur.outilgestion.entites.Materiau;

public interface IMateriauManager {

	public Materiau ajouterMateriau(Materiau materiau);
	
	public Materiau supprimerMateriau(Materiau materiau);
	
	public Materiau trouverMateriau(int idMateriau);
	
	public List<Materiau> trouverTsMateriaux ();
}
