package fr.latelierchantdefleur.outilgestion.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.manager.contract.IBouquetManager;

@Service
public class BouquetManagerImpl implements IBouquetManager {
	
	private IDaoFactory daoFacto;

	@Override
	public Bouquet ajouterBouquet(Bouquet bouquet) {
		this.daoFacto.getBouquetDao().saveAndFlush(bouquet);
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
		return daoFacto;
	}

	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
