package fr.latelierchantdefleur.outilgestion.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.entites.Saison;
import fr.latelierchantdefleur.outilgestion.manager.contract.IBouquetManager;

@Service
public class BouquetManagerImpl implements IBouquetManager {

	private IDaoFactory daoFacto;

	@Override
	public Bouquet ajouterBouquet(Bouquet bouquet) {
		Saison saison = bouquet.getSaison();
		System.out.println("CTRL input ----- " + saison.getSaison());
		System.out.println(
				"CTRL BDD ----- " + this.daoFacto.getSaisonDao().findById(Integer.valueOf(saison.getSaison())));
		saison.setId(this.daoFacto.getSaisonDao().findById(Integer.valueOf(saison.getSaison())).get().getId());
		bouquet.setSaison(saison);
		this.daoFacto.getBouquetDao().save(bouquet);
		return bouquet;
	}

	@Override
	public Bouquet supprimerBouquet(Bouquet bouquet) {
		this.daoFacto.getBouquetDao().delete(bouquet);
		return bouquet;
	}

	@Override
	public Bouquet trouverBouquet(int idBouquet) {
		return this.daoFacto.getBouquetDao().findById(idBouquet).get();
	}

	@Override
	public List<Bouquet> trouverTsBouquets() {
		return this.daoFacto.getBouquetDao().findAll();
	}

	public IDaoFactory getDaoFacto() {
		return this.daoFacto;
	}

	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
