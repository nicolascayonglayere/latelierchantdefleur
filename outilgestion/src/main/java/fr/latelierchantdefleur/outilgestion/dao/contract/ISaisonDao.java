package fr.latelierchantdefleur.outilgestion.dao.contract;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.latelierchantdefleur.outilgestion.entites.Saison;

public interface ISaisonDao extends JpaRepository<Saison, Integer> {

	public Saison findBySaison(String saison);
}
