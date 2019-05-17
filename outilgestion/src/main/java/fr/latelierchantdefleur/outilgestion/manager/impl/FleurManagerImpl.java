package fr.latelierchantdefleur.outilgestion.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Fleur;
import fr.latelierchantdefleur.outilgestion.manager.contract.IFleurManager;

@Service
public class FleurManagerImpl implements IFleurManager {

	private IDaoFactory daoFacto;
 
	@Override
	public Fleur ajouterFleur(Fleur fleur) {
		this.getDaoFacto().getFleurDao().saveAndFlush(fleur);
		return fleur;
	}

	@Override
	public Fleur supprimerFleur(Fleur fleur) {
		this.getDaoFacto().getFleurDao().delete(fleur);
		return fleur;
	}

	@Override
	public Fleur trouverFleur(int idFleur) {
		return this.getDaoFacto().getFleurDao().findById(idFleur).get();
	}

	@Override
	public List<Fleur> trouverTtesFleurs() {
		return this.daoFacto.getFleurDao().findAll();
	}

	public IDaoFactory getDaoFacto() {
		return daoFacto;
	}
	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
