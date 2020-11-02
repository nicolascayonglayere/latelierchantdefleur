package fr.latelierchantdefleur.outilgestion.controller.formulaires;

public class ListeFleursBouquetFormulaire {

	private Integer idElementBouquet;
	private boolean ajoutBouquet;
	private Integer idElementFleur;

	public boolean isAjoutBouquet() {
		return this.ajoutBouquet;
	}

	public void setAjoutBouquet(boolean ajoutBouquet) {
		this.ajoutBouquet = ajoutBouquet;
	}

	public Integer getIdElementBouquet() {
		return this.idElementBouquet;
	}

	public void setIdElementBouquet(Integer idElementBouquet) {
		this.idElementBouquet = idElementBouquet;
	}

	public Integer getIdElementFleur() {
		return this.idElementFleur;
	}

	public void setIdElementFleur(Integer idElementFleur) {
		this.idElementFleur = idElementFleur;
	}

}
