package fr.latelierchantdefleur.outilgestion.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.latelierchantdefleur.outilgestion.manager.contract.IBouquetManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IElementManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IFleurManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IMateriauManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.ISaisonManager;

@Component
public class ManagerFactoryImpl implements IManagerFactory {

	private IBouquetManager bouquetManager;
	private IFleurManager fleurManager;
	private IMateriauManager materiauManager;
	private IElementManager elementManager;
	private ISaisonManager saisonManager;

	@Override
	public IBouquetManager getBouquetManager() {
		return this.bouquetManager;
	}

	@Autowired
	@Override
	public void setBouquetManager(IBouquetManager bouquetManager) {
		this.bouquetManager = bouquetManager;
	}

	@Override
	public IFleurManager getFleurManager() {
		return this.fleurManager;
	}

	@Autowired
	@Override
	public void setFleurManager(IFleurManager fleurManager) {
		this.fleurManager = fleurManager;
	}

	@Override
	public IMateriauManager getMateriauManager() {
		return this.materiauManager;
	}

	@Override
	public void setMateriauManager(IMateriauManager materiauManager) {
		this.materiauManager = materiauManager;
	}

	@Override
	public IElementManager getElementManager() {
		return this.elementManager;
	}

	@Autowired
	@Override
	public void setElementManager(IElementManager elementManager) {
		this.elementManager = elementManager;
	}

	@Override
	public ISaisonManager getSaisonManager() {
		return this.saisonManager;
	}

	@Autowired
	@Override
	public void setSaisonManager(ISaisonManager saisonManager) {
		this.saisonManager = saisonManager;
	}

}
