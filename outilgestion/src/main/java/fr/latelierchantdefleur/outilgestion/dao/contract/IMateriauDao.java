package fr.latelierchantdefleur.outilgestion.dao.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.latelierchantdefleur.outilgestion.entites.Materiau;

@Repository
public interface IMateriauDao extends JpaRepository<Materiau, Integer> {

}
