package fr.latelierchantdefleur.outilgestion.manager.contract;

import java.util.List;

import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.entites.Fleur;

public interface IBouquetManager {

	public Bouquet ajouterBouquet(Bouquet bouquet);

	public Bouquet supprimerBouquet(Bouquet bouquet);

	public Bouquet trouverBouquet(int idBouquet);

	public List<Bouquet> trouverTsBouquets();

	public Bouquet ajouterFleursBouquet(Bouquet bouquet, List<Fleur> listeFleurs);

}
