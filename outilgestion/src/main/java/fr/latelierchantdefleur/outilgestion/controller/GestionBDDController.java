package fr.latelierchantdefleur.outilgestion.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.latelierchantdefleur.outilgestion.controller.formulaires.BouquetFormulaire;
import fr.latelierchantdefleur.outilgestion.controller.formulaires.FleurFormulaire;
import fr.latelierchantdefleur.outilgestion.controller.formulaires.ListeFleursBouquetFormulaire;
import fr.latelierchantdefleur.outilgestion.entites.Bouquet;
import fr.latelierchantdefleur.outilgestion.entites.Element;
import fr.latelierchantdefleur.outilgestion.entites.Fleur;
import fr.latelierchantdefleur.outilgestion.entites.Saison;
import fr.latelierchantdefleur.outilgestion.manager.IManagerFactory;

@Controller
public class GestionBDDController {

	private IManagerFactory managerFactory;

	@GetMapping("/bouquets")
	public String accueil(@ModelAttribute Bouquet bouquet, BindingResult errors, Model model) {
		String dossierImage = "G:\\MES IMAGES";
		List<Bouquet> mesBouquets = this.managerFactory.getBouquetManager().trouverTsBouquets();
		System.out.println("CTRL liste bouquet -------" + mesBouquets.size());
		model.addAttribute("mesBouquets", mesBouquets);
		model.addAttribute("dossierImage", dossierImage);
		return ("bouquets");
	}

	@GetMapping("/ajouter_bouquet")
	public String goAjouterBouquet(@ModelAttribute Bouquet bouquet, BindingResult errors, Model model) {
		System.out.println("CTRL cinematique bouquet ------------------------------------------------------------");
		List<Saison> saisonListe = this.managerFactory.getSaisonManager().obtenirTouteSaison();
		model.addAttribute("saisonListe", saisonListe);
		List<String> tailleListe = new ArrayList<String>();
		tailleListe.add("mini");
		tailleListe.add("S");
		tailleListe.add("M");
		tailleListe.add("L");
		tailleListe.add("XL");
		model.addAttribute("tailleListe", tailleListe);
		BouquetFormulaire bf = new BouquetFormulaire();
		model.addAttribute(bf);
		return ("ajouter_bouquet");
	}

	@PostMapping("/ajouter_bouquet")
	public String ajouterBouquet(@ModelAttribute("bouquet") BouquetFormulaire bouquet, BindingResult errors,
			Model model, HttpSession session) {
		System.out.println("CTRL ajout bouquet : " + bouquet.getCouleur());
		Element monElement = new Element();
		monElement.setDateAjout(Calendar.getInstance().getTime());
		session.setAttribute("elementCree", this.managerFactory.getElementManager().ajouterElement(monElement));
		Saison maSaison = new Saison(bouquet.getSaison());
		Bouquet bouquetSave = new Bouquet(maSaison, bouquet.getPrixUnitaire(), bouquet.getCouleur(),
				bouquet.getTaille(), bouquet.getCheminImage(), bouquet.isCompoFlorale());
		bouquetSave.setIdElement(monElement.getIdElement());
		session.setAttribute("bouquetCree", this.managerFactory.getBouquetManager().ajouterBouquet(bouquetSave));
		// session.
		return ("redirect:/liste_fleurs_bouquet");
	}

	@GetMapping("/ajouter_fleur")
	public String goAjouterFleur(@ModelAttribute Fleur fleur, BindingResult errors, Model model) {
		System.out.println("CTRL cinematique fleur ------------------------------------------------------------");
		List<Saison> saisonListe = this.managerFactory.getSaisonManager().obtenirTouteSaison();
		model.addAttribute("saisonListe", saisonListe);
		FleurFormulaire ff = new FleurFormulaire();
		model.addAttribute(ff);
		return ("ajouter_fleur");
	}

	@PostMapping("/ajouter_fleur")
	public String ajouterFLeur(@ModelAttribute("fleur") FleurFormulaire fleur, BindingResult errors, Model model) {
		System.out.println("CTRL ajout fleur : " + fleur.getNom());
		Element monElement = new Element();
		monElement.setDateAjout(Calendar.getInstance().getTime());
		this.managerFactory.getElementManager().ajouterElement(monElement);
		Saison maSaison = new Saison(fleur.getSaison());
		Fleur fleurSave = new Fleur(fleur.getNom(), fleur.getNomLatin(), fleur.getCouleur(), maSaison);
		this.managerFactory.getFleurManager().ajouterFleur(fleurSave);
		return ("redirect:/bouquets");
	}

	@GetMapping("/liste_fleurs_bouquet")
	public String listeFleursBouquet(@ModelAttribute Fleur fleur, BindingResult errors, Model model,
			HttpSession session) {
		List<Fleur> mesFleurs = this.managerFactory.getFleurManager().trouverTtesFleurs();
		System.out.println("CTRL session element ---------" + (session.getAttribute("elementCree")).toString());
		System.out.println("CTRL session bouquet ---------" + (session.getAttribute("bouquetCree")).toString());
		model.addAttribute("bouquetCree", session.getAttribute("elementCree"));
		model.addAttribute("mesFleurs", mesFleurs);
		model.addAttribute(new ListeFleursBouquetFormulaire());
		return ("liste_fleurs_bouquet");
	}

	@PostMapping("/liste_fleurs_bouquet")
	public String ajouterListeFleursBouquet(@ModelAttribute("fleur") ListeFleursBouquetFormulaire fleur,
			BindingResult errors, Model model, HttpSession session) {
		System.out.println("CTRL ajout fleur bouquet : " + fleur.isAjoutBouquet() + " - " + fleur.getIdElementBouquet()
				+ " - " + fleur.getIdElementFleur());
		List<Fleur> mesFleursAjoutees = new ArrayList<Fleur>();
		if (fleur.isAjoutBouquet()) {
			mesFleursAjoutees.add(this.managerFactory.getFleurManager().trouverFleur(fleur.getIdElementFleur()));
		}

		this.managerFactory.getBouquetManager().ajouterFleursBouquet((Bouquet) session.getAttribute("bouquetCree"),
				mesFleursAjoutees);
		return ("redirect:/ajouter_bouquet");
	}

	public IManagerFactory getManagerFactory() {
		return this.managerFactory;
	}

	@Autowired
	public void setManagerFactory(IManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
