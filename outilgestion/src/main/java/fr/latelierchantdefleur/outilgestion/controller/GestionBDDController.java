package fr.latelierchantdefleur.outilgestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.entites.Saison;
import fr.latelierchantdefleur.outilgestion.manager.IManagerFactory;

@Controller
public class GestionBDDController {

	private IManagerFactory managerFactory;

	@GetMapping("/accueil")
	public String accueil(@ModelAttribute Bouquet bouquet, BindingResult errors, Model model) {
		List<Bouquet> mesBouquets = this.managerFactory.getBouquetManager().trouverTsBouquets();
		model.addAllAttributes(mesBouquets);
		BouquetFormulaire bf = new BouquetFormulaire();
		model.addAttribute(bf);
		return ("bouquets");
	}
	
	@PostMapping("/accueil")
	public String ajouterBouquet(@ModelAttribute BouquetFormulaire bf, BindingResult errors, Model model) {
		Bouquet bouquet = new Bouquet(Saison.valueOf(bf.getSaison()), bf.getPrixUnitaire(), bf.getCouleur(), bf.getTaille(), bf.getCheminImage(), bf.isCompoFlorale());
		this.managerFactory.getBouquetManager().ajouterBouquet(bouquet);
		return ("bouquets");
	}
	
	
	
	public IManagerFactory getManagerFactory() {
		return managerFactory;
	}

	@Autowired
	public void setManagerFactory(IManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
