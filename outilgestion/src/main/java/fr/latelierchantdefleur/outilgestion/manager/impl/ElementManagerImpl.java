package fr.latelierchantdefleur.outilgestion.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.dao.IDaoFactory;
import fr.latelierchantdefleur.outilgestion.entites.Element;
import fr.latelierchantdefleur.outilgestion.manager.contract.IElementManager;

@Service
public class ElementManagerImpl implements IElementManager {

	private IDaoFactory daoFacto;

	@Override
	public Element ajouterElement(Element element) {
		System.out.println("CTRL manager --------" + element.getDateAjout());
		return this.daoFacto.getElementDao().saveAndFlush(element);
	}

	public IDaoFactory getDaoFacto() {
		return this.daoFacto;
	}

	@Autowired
	public void setDaoFacto(IDaoFactory daoFacto) {
		this.daoFacto = daoFacto;
	}

}
