package fr.latelierchantdefleur.outilgestion.manager;

import fr.latelierchantdefleur.outilgestion.manager.contract.IBouquetManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IElementManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IFleurManager;
import fr.latelierchantdefleur.outilgestion.manager.contract.IMateriauManager;

public interface IManagerFactory {

	IBouquetManager getBouquetManager();

	void setBouquetManager(IBouquetManager bouquetManager);

	IFleurManager getFleurManager();

	void setFleurManager(IFleurManager fleurManager);

	IMateriauManager getMateriauManager();

	void setMateriauManager(IMateriauManager materiauManager);

	IElementManager getElementManager();

	void setElementManager(IElementManager elementManager);
}
