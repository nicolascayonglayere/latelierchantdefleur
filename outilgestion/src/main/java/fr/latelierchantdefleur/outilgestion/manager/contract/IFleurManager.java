package fr.latelierchantdefleur.outilgestion.manager.contract;

import java.util.List;

import fr.latelierchantdefleur.outilgestion.entites.Fleur;

public interface IFleurManager {
	
	public Fleur ajouterFleur(Fleur fleur);
	
	public Fleur supprimerFleur(Fleur fleur);
	
	public Fleur trouverFleur(int idFleur);
	
	public List<Fleur> trouverTtesFleurs ();

}
