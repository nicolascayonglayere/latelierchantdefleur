package fr.latelierchantdefleur.outilgestion.controller.formulaires;

public class ListeFleursBouquetFormulaire {

	private Integer idBouquet;
	private boolean ajoutBouquet;
	private Integer idFleur;

	public Integer getIdBouquet() {
		return this.idBouquet;
	}

	public void setIdBouquet(Integer idBouquet) {
		this.idBouquet = idBouquet;
	}

	public boolean isAjoutBouquet() {
		return this.ajoutBouquet;
	}

	public void setAjoutBouquet(boolean ajoutBouquet) {
		this.ajoutBouquet = ajoutBouquet;
	}

	public Integer getIdFleur() {
		return this.idFleur;
	}

	public void setIdFleur(Integer idFleur) {
		this.idFleur = idFleur;
	}

}
