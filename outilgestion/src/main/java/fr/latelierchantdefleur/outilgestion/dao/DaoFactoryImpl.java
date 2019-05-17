package fr.latelierchantdefleur.outilgestion.dao;

import org.springframework.beans.factory.annotation.Autowired;

import fr.latelierchantdefleur.outilgestion.dao.contract.IBouquetDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IFleurDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IMateriauDao;

public class DaoFactoryImpl implements IDaoFactory {

	private IBouquetDao bouquetDao;
	private IFleurDao fleurDao;
	private IMateriauDao materiauDao;
	
	@Override
	public IBouquetDao getBouquetDao() {
		return this.bouquetDao;
	}

	@Autowired
	@Override
	public void setBouquetDao(IBouquetDao bouquetDao) {
		this.bouquetDao = bouquetDao;		
	}

	@Override
	public IFleurDao getFleurDao() {
		return this.fleurDao;
	}

	@Autowired
	@Override
	public void setFleurDao(IFleurDao fleurDao) {
		this.fleurDao = fleurDao;
	}

	@Override
	public IMateriauDao getMateriauDao() {
		return this.materiauDao;
	}

	@Override
	public void setMateriauDao(IMateriauDao materiauDao) {
		this.materiauDao = materiauDao;
	}

}
