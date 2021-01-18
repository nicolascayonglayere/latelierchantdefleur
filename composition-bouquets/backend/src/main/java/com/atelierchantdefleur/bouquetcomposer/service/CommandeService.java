package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionCommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Client;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Commande;
import com.atelierchantdefleur.bouquetcomposer.model.entity.CompositionsCommande;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.*;
import com.atelierchantdefleur.bouquetcomposer.repository.ClientRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.CommandeRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionCommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final CompositionMapper compositionMapper;
    private final ElementMapper elementMapper;
    private final ClientMapper clientMapper;
    private final ElementService elementService;
    private final ClientRepository clientRepository;
    private final CommandeCompositionMapper commandeCompositionMapper;
    private final CompositionCommandeRepository compositionCommandeRepository;

    public CommandeService(CommandeRepository commandeRepository, CommandeMapper commandeMapper,
                           CompositionMapper compositionMapper, ElementMapper elementMapper,
                           ClientMapper clientMapper, ElementService elementService, ClientRepository ClientRepository,
                           CommandeCompositionMapper commandeCompositionMapper, CompositionCommandeRepository compositionCommandeRepository){
        this.compositionMapper = compositionMapper;
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
        this.elementMapper = elementMapper;
        this.clientMapper = clientMapper;
        this.elementService = elementService;
        this.clientRepository = ClientRepository;
        this.commandeCompositionMapper = commandeCompositionMapper;
        this.compositionCommandeRepository = compositionCommandeRepository;
    }

    public CommandeDTO save(CommandeDTO commandeDTO){
        Client client = this.clientRepository.findById(commandeDTO.getClientDTO().getId())
                .orElseThrow(() -> new RuntimeException("Le client n'existe pas"));
        Commande commandeToSave = this.commandeMapper.fromDomainToEntity(commandeDTO, new ArrayList<>(), client);
        Commande commande = this.commandeRepository.save(commandeToSave);
        List<CompositionsCommande> compositions = commandeDTO.getCompositions().stream()
                .map(c -> this.commandeCompositionMapper.fromDomainToEntity(c, this.compositionMapper.fromDomainToEntity(c.getCompositionDTO(), new ArrayList<>(), new ArrayList<>()), commande))
                .collect(Collectors.toList());
        client.addCommande(commande);
        client = this.clientRepository.save(client);
        this.compositionCommandeRepository.saveAll(compositions);
        ClientDTO clientSaved = this.clientMapper.fromEntityToDomain(client, new ArrayList<>());
        return this.commandeMapper.fromEntityToDomain(commande, commandeDTO.getCompositions(), clientSaved);
    }

    public CommandeDTO getById(Long id){
        Commande commande = this.commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'evenement n'existe pas"));
        List<CompositionCommandeDTO> compositionDTOS = commande.getCompositions().stream()
                .map(c -> this.commandeCompositionMapper.fromEntityToDomain(c, this.compositionMapper.fromEntityToDomain(c.getComposition(), c.getComposition().getElements().stream()
                        .map(e -> this.elementMapper.fromEntityToDomain(e.getElement(), e.getQuantite()))
                        .collect(Collectors.toList()), new ArrayList<>())))
                .collect(Collectors.toList());
        ClientDTO clientDTO = this.clientMapper.fromEntityToDomain(commande.getClient(), new ArrayList<>());
        CommandeDTO commandeDTO =  this.commandeMapper.fromEntityToDomain(commande, compositionDTOS, clientDTO);
        return commandeDTO;
    }

    public List<CommandeDTO> getAll(){
        List<Commande> commandeList = this.commandeRepository.findAll();
        return commandeList.stream()
                .map(e -> this.commandeMapper.fromEntityToDomain(e,
                    e.getCompositions().stream()
                        .map(c -> this.commandeCompositionMapper.fromEntityToDomain(c, this.compositionMapper.fromEntityToDomain(c.getComposition(),
                                c.getComposition().getElements().stream()
                                    .map(elt -> this.elementMapper.fromEntityToDomain(elt.getElement(), elt.getQuantite()))
                                    .collect(Collectors.toList()), new ArrayList<>())))
                        .collect(Collectors.toList()),
                        this.clientMapper.fromEntityToDomain(e.getClient(), new ArrayList<>())))
                .collect(Collectors.toList());
    }

    public List<CommandeDTO> getByIdCompo(Long idCompo){
        List<Commande> commandeList = this.commandeRepository.findByCompositionsId(idCompo);
        return commandeList.stream()
                .map(e -> this.commandeMapper.fromEntityToDomain(e, new ArrayList<>(),
                        this.clientMapper.fromEntityToDomain(e.getClient(), new ArrayList<>())))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        this.compositionCommandeRepository.deleteAllByCommandeId(id);
        this.commandeRepository.deleteById(id);
    }
}
