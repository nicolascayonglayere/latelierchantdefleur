package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionCommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Client;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import com.atelierchantdefleur.bouquetcomposer.model.entity.CompositionsCommande;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ClientRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CommandeRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionCommandeRest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CommandeMapper {

    public Commande fromDomainToEntity(CommandeDTO commandeDTO, List<CompositionsCommande> compositions, Client client){
        Commande commande = new Commande();
        commande.setId(Objects.equals(0, commandeDTO.getId()) ? null : commandeDTO.getId());
        commande.setNom(commandeDTO.getNom());
        commande.setDateCreation(commandeDTO.getDateCreation());
        commande.setDatePrevue(commandeDTO.getDatePrevue());
        commande.setCompositions(compositions);
        commande.setForfaitDplct(commandeDTO.getForfaitDplct());
        commande.setForfaitMo(commandeDTO.getForfaitMo());
        commande.setClient(Objects.isNull(client) ? null : client);
        commande.setBudget(commandeDTO.getBudget());
        return commande;
    }

    public CommandeDTO fromEntityToDomain(Commande commande, List<CompositionCommandeDTO> compositionsDTO, ClientDTO clientDTO){
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commande.getId());
        commandeDTO.setNom(commande.getNom());
        commandeDTO.setDateCreation(commande.getDateCreation());
        commandeDTO.setDatePrevue(commande.getDatePrevue());
        commandeDTO.setCompositions(compositionsDTO);
        commandeDTO.setForfaitDplct(commande.getForfaitDplct());
        commandeDTO.setForfaitMo(commande.getForfaitMo());
        commandeDTO.setClientDTO(Objects.isNull(clientDTO) ? null : clientDTO);
        commandeDTO.setBudget(commande.getBudget());
        return commandeDTO;
    }

    public CommandeDTO fromRestToDomain(CommandeRest commandeRest, List<CompositionCommandeDTO> compositionsDTO, ClientDTO clientDTO){
        CommandeDTO commandeDTO = new CommandeDTO();
        commandeDTO.setId(commandeRest.getId());
        commandeDTO.setNom(commandeRest.getNom());
        commandeDTO.setDateCreation(commandeRest.getDateCreation());
        commandeDTO.setDatePrevue(commandeRest.getDatePrevue());
        commandeDTO.setCompositions(compositionsDTO);
        commandeDTO.setForfaitMo(commandeRest.getForfaitMo());
        commandeDTO.setForfaitDplct(commandeRest.getForfaitDplct());
        commandeDTO.setClientDTO(Objects.isNull(clientDTO) ? null : clientDTO);
        commandeDTO.setBudget(commandeRest.getBudget());
        return commandeDTO;
    }

    public CommandeRest fromDomainToRest(CommandeDTO commandeDTO, List<CompositionCommandeRest> compositionsRest, ClientRest clientRest){
        CommandeRest commandeRest = new CommandeRest();
        commandeRest.setId(commandeDTO.getId());
        commandeRest.setNom(commandeDTO.getNom());
        commandeRest.setDateCreation(commandeDTO.getDateCreation());
        commandeRest.setDatePrevue(commandeDTO.getDatePrevue());
        commandeRest.setCompositions(compositionsRest);
        commandeRest.setForfaitDplct(commandeDTO.getForfaitDplct());
        commandeRest.setForfaitMo(commandeDTO.getForfaitMo());
        commandeRest.setIdClientRest(Objects.isNull(clientRest) ? null : clientRest.getId());
        commandeRest.setBudget(commandeDTO.getBudget());
        return commandeRest;
    }
}
