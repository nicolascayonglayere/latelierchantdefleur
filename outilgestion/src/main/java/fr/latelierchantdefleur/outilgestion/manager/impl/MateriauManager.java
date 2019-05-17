package fr.latelierchantdefleur.outilgestion.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Materiau;
import fr.latelierchantdefleur.outilgestion.manager.contract.IMateriauManager;

@Service
public class MateriauManager implements IMateriauManager {

	private IDaoFactory daoFacto;
	
	@Override
	public Materiau ajouterMateriau(Materiau materiau) {
		this.getDaoFacto().getMateriauDao().saveAndFlush(materiau);
		return materiau;
	}

	@Override
	public Materiau supprimerMateriau(Materiau materiau) {
		this.getDaoFacto().getMateriauDao().delete(materiau);
		return materiau;
	}

	@Override
	public Materiau trouverMateriau(int idMateriau) {
		return this.getDaoFacto().getMateriauDao().findById(idMateriau).get();
	}

	@Override
	public List<Materiau> trouverTsMateriaux() {
		return this.getDaoFacto().getMateriauDao().findAll();
	}

	public IDaoFactory getDaoFacto() {
		return daoFacto;
	}

	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
