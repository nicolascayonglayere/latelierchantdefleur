package fr.latelierchantdefleur.outilgestion.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Saison;
import fr.latelierchantdefleur.outilgestion.manager.contract.ISaisonManager;

@Service
public class SaisonManager implements ISaisonManager {

	private IDaoFactory daoFacto;

	@Override
	public List<Saison> obtenirTouteSaison() {
		List<Saison> saisonListe = new ArrayList<Saison>();
		saisonListe = this.daoFacto.getSaisonDao().findAll();
		return saisonListe;
	}

	public IDaoFactory getDaoFacto() {
		return this.daoFacto;
	}

	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
