package fr.latelierchantdefleur.outilgestion.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.entites.Element;
import fr.latelierchantdefleur.outilgestion.entites.Saison;
import fr.latelierchantdefleur.outilgestion.manager.IManagerFactory;

@Controller
public class GestionBDDController {

	private IManagerFactory managerFactory;

	@GetMapping("/bouquets")
	public String accueil(@ModelAttribute Bouquet bouquet, BindingResult errors, Model model) {
		List<Bouquet> mesBouquets = this.managerFactory.getBouquetManager().trouverTsBouquets();
		System.out.println("CTRL liste bouquet -------" + mesBouquets.size());
		model.addAttribute("mesBouquets", mesBouquets);

		return ("bouquets");
	}

	@GetMapping("/ajouter_bouquet")
	public String goAjouterBouquet(@ModelAttribute Bouquet bouquet, BindingResult errors, Model model) {
		System.out.println("CTRL cinematique ------------------------------------------------------------");
		BouquetFormulaire bf = new BouquetFormulaire();
		model.addAttribute(bf);
		return ("ajouter_bouquet");
	}

	@PostMapping("/ajouter_bouquet")
	public String ajouterBouquet(@ModelAttribute("bouquet") BouquetFormulaire bouquet, BindingResult errors,
			Model model) {
		System.out.println("CTRL ajout bouquet : " + bouquet.getCouleur());
		Element monElement = new Element();
		monElement.setDateAjout(Calendar.getInstance().getTime());
		this.managerFactory.getElementManager().ajouterElement(monElement);
		Saison maSaison = new Saison(bouquet.getSaison());
		Bouquet bouquetSave = new Bouquet(maSaison, bouquet.getPrixUnitaire(), bouquet.getCouleur(),
				bouquet.getTaille(), bouquet.getCheminImage(), bouquet.isCompoFlorale());
		bouquetSave.setIdElement(monElement.getIdElement());

		this.managerFactory.getBouquetManager().ajouterBouquet(bouquetSave);
		return ("bouquets");
	}

	public IManagerFactory getManagerFactory() {
		return this.managerFactory;
	}

	@Autowired
	public void setManagerFactory(IManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
