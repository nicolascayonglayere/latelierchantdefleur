package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Client;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ClientRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CommandeRest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientMapper {

    public Client fromDomainToEntity(ClientDTO clientDTO, List<Commande> commandes){
        if(Objects.isNull(clientDTO)){
            return null;
        }
        Client client = new Client();
        client.setId(Objects.isNull(clientDTO.getId()) ? null : clientDTO.getId());
        client.setAdresse(Objects.isNull(clientDTO.getAdresse()) ? null : clientDTO.getAdresse());
        client.setCodePostal(Objects.isNull(clientDTO.getCodePostal()) ? null : clientDTO.getCodePostal());
        client.setEmail(clientDTO.getEmail());
        client.setNom1(clientDTO.getNom1());
        client.setPrenom1(Objects.isNull(clientDTO.getPrenom1()) ? null : clientDTO.getPrenom1());
        client.setNom2(Objects.isNull(clientDTO.getNom2()) ? null : clientDTO.getNom2());
        client.setPrenom2(Objects.isNull(clientDTO.getPrenom2()) ? null : clientDTO.getPrenom2());
        client.setTelephone(Objects.isNull(clientDTO.getTelephone()) ? null : clientDTO.getTelephone());
        client.setVille(Objects.isNull(clientDTO.getVille()) ? null : clientDTO.getVille());
        client.setCommandes(commandes);
        return client;
    }

    public ClientDTO fromEntityToDomain(Client client, List<CommandeDTO> commandeDTO){
        if(Objects.isNull(client)){
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setAdresse(Objects.isNull(client.getAdresse()) ? null : client.getAdresse());
        clientDTO.setCodePostal(Objects.isNull(client.getCodePostal()) ? null : client.getCodePostal());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setNom1(client.getNom1());
        clientDTO.setPrenom1(Objects.isNull(client.getPrenom1()) ? null : client.getPrenom1());
        clientDTO.setNom2(Objects.isNull(client.getNom2()) ? null : client.getNom2());
        clientDTO.setPrenom2(Objects.isNull(client.getPrenom2()) ? null : client.getPrenom2());
        clientDTO.setTelephone(Objects.isNull(client.getTelephone()) ? null : client.getTelephone());
        clientDTO.setVille(Objects.isNull(client.getVille()) ? null : client.getVille());
        clientDTO.setEvenementsDTO(commandeDTO);
        return clientDTO;
    }

    public ClientRest fromDomainToRest(ClientDTO clientDTO, List<CommandeRest> commandeRest){
        if(Objects.isNull(clientDTO)){
            return null;
        }
        ClientRest clientRest = new ClientRest();
        clientRest.setId(Objects.isNull(clientDTO.getId()) ? null : clientDTO.getId());
        clientRest.setAdresse(Objects.isNull(clientDTO.getAdresse()) ? null : clientDTO.getAdresse());
        clientRest.setCodePostal(Objects.isNull(clientDTO.getCodePostal()) ? null : clientDTO.getCodePostal());
        clientRest.setEmail(clientDTO.getEmail());
        clientRest.setNom1(clientDTO.getNom1());
        clientRest.setPrenom1(Objects.isNull(clientDTO.getPrenom1()) ? null : clientDTO.getPrenom1());
        clientRest.setNom2(Objects.isNull(clientDTO.getNom2()) ? null : clientDTO.getNom2());
        clientRest.setPrenom2(Objects.isNull(clientDTO.getPrenom2()) ? null : clientDTO.getPrenom2());
        clientRest.setTelephone(Objects.isNull(clientDTO.getTelephone()) ? null : clientDTO.getTelephone());
        clientRest.setVille(Objects.isNull(clientDTO.getVille()) ? null : clientDTO.getVille());
        clientRest.setEvenementsRest(commandeRest);
        return clientRest;
    }

    public ClientDTO fromRestToDomain(ClientRest clientRest, List<CommandeDTO> commandeDTOS){
        if(Objects.isNull(clientRest)){
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(Objects.isNull(clientRest.getId()) ? null : clientRest.getId());
        clientDTO.setAdresse(Objects.isNull(clientRest.getAdresse()) ? null : clientRest.getAdresse());
        clientDTO.setCodePostal(Objects.isNull(clientRest.getCodePostal()) ? null : clientRest.getCodePostal());
        clientDTO.setEmail(clientRest.getEmail());
        clientDTO.setNom1(clientRest.getNom1());
        clientDTO.setPrenom1(Objects.isNull(clientRest.getPrenom1()) ? null : clientRest.getPrenom1());
        clientDTO.setNom2(Objects.isNull(clientRest.getNom2()) ? null : clientRest.getNom2());
        clientDTO.setPrenom2(Objects.isNull(clientRest.getPrenom2()) ? null : clientRest.getPrenom2());
        clientDTO.setTelephone(Objects.isNull(clientRest.getTelephone()) ? null : clientRest.getTelephone());
        clientDTO.setVille(Objects.isNull(clientRest.getVille()) ? null : clientRest.getVille());
        clientDTO.setEvenementsDTO(commandeDTOS);
        return clientDTO;
    }
}
