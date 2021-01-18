package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.model.constante.HttpUrlConstantes;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ClientMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CommandeMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ClientRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CommandeRest;
import com.atelierchantdefleur.bouquetcomposer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(ClientController.rootUrl)
@RestController
@CrossOrigin(HttpUrlConstantes.CROSS_ORIGIN)
public class ClientController {

    public static final String rootUrl = HttpUrlConstantes.ROOT_URL + "/" + HttpUrlConstantes.CLIENTS_URL;

    @Autowired
    public ClientService clientService;
    @Autowired
    public ClientMapper clientMapper;
    @Autowired
    public CommandeMapper commandeMapper;

    @GetMapping()
    public List<ClientRest> getAll(){
        List<ClientDTO> clientDTOS = this.clientService.getAll();
        return clientDTOS.stream()
                .map(c -> this.clientMapper.fromDomainToRest(c, c.getEvenementsDTO().stream()
                        .map(e -> this.commandeMapper.fromDomainToRest(e, new ArrayList<>(), null))
                        .sorted(Comparator.comparing(CommandeRest::getDateCreation).reversed())
                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @GetMapping(value="/"+HttpUrlConstantes.ID_PV)
    public ClientRest getById(@PathVariable(name="id") Long id){
        ClientDTO clientDTO = this.clientService.getById(id);
        List<CommandeRest> commandeRests = clientDTO.getEvenementsDTO().stream()
                .map(e -> this.commandeMapper.fromDomainToRest(e, new ArrayList<>(), null))
                .collect(Collectors.toList());
        return this.clientMapper.fromDomainToRest(clientDTO, commandeRests);
    }

    @PostMapping("/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.EDIT)
    public ClientRest save(@RequestBody ClientRest clientRest){
        List<CommandeDTO> commandeDTOList = new ArrayList<>();
        ClientDTO clientDTO = this.clientMapper.fromRestToDomain(clientRest, commandeDTOList);
        ClientDTO clientSaved = this.clientService.save(clientDTO);
        return this.clientMapper.fromDomainToRest(clientSaved, new ArrayList<>());
    }

    @PutMapping("/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.EDIT)
    public ClientRest update(@RequestBody ClientRest clientRest){
        List<CommandeDTO> commandeDTOList = new ArrayList<>();
        ClientDTO clientDTO = this.clientMapper.fromRestToDomain(clientRest, commandeDTOList);
        ClientDTO clientSaved = this.clientService.save(clientDTO);
        return this.clientMapper.fromDomainToRest(clientSaved, new ArrayList<>());
    }
}
