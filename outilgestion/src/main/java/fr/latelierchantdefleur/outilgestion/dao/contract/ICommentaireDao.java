package fr.latelierchantdefleur.outilgestion.dao.contract;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.latelierchantdefleur.outilgestion.entites.Commentaire;

public interface ICommentaireDao extends JpaRepository<Commentaire, Integer> {

}
