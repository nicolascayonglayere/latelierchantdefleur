package fr.latelierchantdefleur.outilgestion.dao;

import fr.latelierchantdefleur.outilgestion.dao.contract.IBouquetDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IFleurDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IMateriauDao;

public interface IDaoFactory {

	IBouquetDao getBouquetDao();
	
	void setBouquetDao(IBouquetDao bouquetDao);
	
	IFleurDao getFleurDao();
	
	void setFleurDao(IFleurDao fleurDao);
	
	IMateriauDao getMateriauDao();
	
	void setMateriauDao(IMateriauDao materiauDao);
}
