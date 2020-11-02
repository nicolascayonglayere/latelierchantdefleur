package fr.latelierchantdefleur.outilgestion.dao.contract;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.latelierchantdefleur.outilgestion.entites.Fleur;

@Repository
public interface IFleurDao extends JpaRepository<Fleur, Integer> {

	@Query("SELECT f FROM Fleur f WHERE f.idElement = :idElement")
	Optional<Fleur> findByIdElement(@Param("idElement") int idElement);

}
