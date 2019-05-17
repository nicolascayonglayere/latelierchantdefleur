package fr.latelierchantdefleur.outilgestion.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.latelierchantdefleur.outilgestion.manager.contract.IBouquetManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IFleurManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IMateriauManager;

@Service
public class ManagerFactoryImpl implements IManagerFactory {
	
	private IBouquetManager bouquetManager;
	private IFleurManager fleurManager;
	private IMateriauManager materiauManager;

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

}
