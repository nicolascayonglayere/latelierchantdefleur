package fr.latelierchantdefleur.outilgestion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.latelierchantdefleur.outilgestion.dao.contract.IBouquetDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IElementDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IFleurDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.IMateriauDao;
import fr.latelierchantdefleur.outilgestion.dao.contract.ISaisonDao;

@Component
public class DaoFactoryImpl implements IDaoFactory {

	private IBouquetDao bouquetDao;
	private IFleurDao fleurDao;
	private IMateriauDao materiauDao;
	private IElementDao elementDao;
	private ISaisonDao saisonDao;

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

	@Override
	public IElementDao getElementDao() {
		return this.elementDao;
	}

	@Autowired
	@Override
	public void setElementDao(IElementDao elementDao) {
		this.elementDao = elementDao;
	}

	@Override
	public ISaisonDao getSaisonDao() {
		return this.saisonDao;
	}

	@Autowired
	@Override
	public void setSaisonDao(ISaisonDao saisonDao) {
		this.saisonDao = saisonDao;
	}

}
