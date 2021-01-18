package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Client;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ClientMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CommandeMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CommandeMapper commandeMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, CommandeMapper commandeMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.commandeMapper = commandeMapper;
    }

    public List<ClientDTO> getAll(){
        List<Client> clients = this.clientRepository.findAll(Sort.by("nom1"));
        return clients.stream().map(c -> this.clientMapper.fromEntityToDomain(c,
                c.getCommandes().stream()
                        .map(e -> this.commandeMapper.fromEntityToDomain(e, new ArrayList<>(), null))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public ClientDTO getById(Long id){
        Client client = this.clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Le client n'existe pas"));
        List<CommandeDTO> commandeDTOS = client.getCommandes().stream()
                .map(e -> this.commandeMapper.fromEntityToDomain(e, new ArrayList<>(), null))
                .sorted(Comparator.comparing(CommandeDTO::getDateCreation))
                .collect(Collectors.toList());
        return this.clientMapper.fromEntityToDomain(client, commandeDTOS);
    }

    public ClientDTO save(ClientDTO clientDTO){
        List<Commande> commandes = clientDTO.getEvenementsDTO().stream()
            .map(e ->this.commandeMapper.fromDomainToEntity(e, new ArrayList<>(), null))
            .collect(Collectors.toList());
        Client clientToSave = this.clientMapper.fromDomainToEntity(clientDTO, commandes);
        Client client = this.clientRepository.save(clientToSave);
        List<CommandeDTO> commandeDTOS = client.getCommandes().stream()
                    .map(e -> this.commandeMapper.fromEntityToDomain(e, new ArrayList<>(), null))
                    .collect(Collectors.toList());
        return this.clientMapper.fromEntityToDomain(client, commandeDTOS);
    }

}
