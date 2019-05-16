package fr.latelierchantdefleur.outilgestion.dao.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.latelierchantdefleur.outilgestion.entites.Utilisateur;

@Repository
public interface IUtilisateurDao extends JpaRepository<Utilisateur, Integer> {

}
