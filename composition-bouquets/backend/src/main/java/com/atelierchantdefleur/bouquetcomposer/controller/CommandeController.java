package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.model.constante.HttpUrlConstantes;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ClientDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionCommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.*;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ClientRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionCommandeRest;
import com.atelierchantdefleur.bouquetcomposer.service.CommandeService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CommandeRest;
import com.atelierchantdefleur.bouquetcomposer.service.edition.ConstructionDevis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(CommandeController.rootUrl)
@RestController
@CrossOrigin(HttpUrlConstantes.CROSS_ORIGIN)
public class CommandeController {

    public static final String rootUrl = HttpUrlConstantes.ROOT_URL + "/" + HttpUrlConstantes.EVENEMENT_URL;

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private ConstructionDevis constructionDevis;
    @Autowired
    private CommandeMapper commandeMapper;
    @Autowired
    private CompositionMapper compositionMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private CommandeCompositionMapper commandeCompositionMapper;

    @PostMapping()
    public CommandeRest save(@RequestBody CommandeRest commandeRest){
        List<CompositionCommandeDTO> compositionDTOS = commandeRest.getCompositions().stream()
                .map(c -> this.commandeCompositionMapper.fromRestToDomain(c, this.compositionMapper.fromRestToDomain(c.getComposition(), new ArrayList<>(), new ArrayList<>())))
                .collect(Collectors.toList());
        ClientDTO clientDTO = new ClientDTO(commandeRest.getIdClientRest());//this.clientMapper.fromRestToDomain(commandeRest.getClientRest(), new ArrayList<>());
        CommandeDTO commandeDTO = this.commandeMapper.fromRestToDomain(commandeRest, compositionDTOS, clientDTO);
        CommandeDTO evtSave = this.commandeService.save(commandeDTO);
        ClientRest clientSave = this.clientMapper.fromDomainToRest(evtSave.getClientDTO(), new ArrayList<>());
        return this.commandeMapper.fromDomainToRest(evtSave, commandeRest.getCompositions(), clientSave);
    }

    @PutMapping()
    public CommandeRest update(@RequestBody CommandeRest commandeRest){
        List<CompositionCommandeDTO> compositionDTOS = commandeRest.getCompositions().stream()
                .map(c -> this.commandeCompositionMapper.fromRestToDomain(c,
                        this.compositionMapper.fromRestToDomain(c.getComposition(), new ArrayList<>(), new ArrayList<>())))
                .collect(Collectors.toList());
        ClientDTO clientDTO = new ClientDTO(commandeRest.getIdClientRest());//this.clientMapper.fromRestToDomain(commandeRest.getClientRest(), new ArrayList<>());
        CommandeDTO commandeDTO = this.commandeMapper.fromRestToDomain(commandeRest, compositionDTOS, clientDTO);
        CommandeDTO evtSave = this.commandeService.save(commandeDTO);
        ClientRest clientSave = this.clientMapper.fromDomainToRest(evtSave.getClientDTO(), new ArrayList<>());
        return this.commandeMapper.fromDomainToRest(evtSave, commandeRest.getCompositions(), clientSave);
    }

    @GetMapping(value="/"+HttpUrlConstantes.ID_PV)
    public CommandeRest getById(@PathVariable("id") Long id){
        CommandeDTO commandeDTO = this.commandeService.getById(id);
        ClientRest clientSave = this.clientMapper.fromDomainToRest(commandeDTO.getClientDTO(), new ArrayList<>());
        List<CompositionCommandeRest> compositionRests = commandeDTO.getCompositions().stream()
                .map(c -> this.commandeCompositionMapper.fromDomainToRest(c, this.compositionMapper.fromDomainToRest(c.getCompositionDTO(),
                        c.getCompositionDTO().getElementsComposition().stream()
                                .map(e -> this.elementMapper.fromDomainToRest(e))
                                .collect(Collectors.toList()), new ArrayList<>())))
                .collect(Collectors.toList());
        return this.commandeMapper.fromDomainToRest(commandeDTO, compositionRests, clientSave);
    }

    @GetMapping("/")
    public List<CommandeRest> getAll(){
        List<CommandeDTO> commandeDTOList = this.commandeService.getAll();
        return commandeDTOList.stream()
                .map(e -> this.commandeMapper.fromDomainToRest(e,
                        e.getCompositions().stream()
                            .map(c -> this.commandeCompositionMapper.fromDomainToRest(c, this.compositionMapper.fromDomainToRest(c.getCompositionDTO(),
                                    c.getCompositionDTO().getElementsComposition().stream()
                                        .map(elt -> this.elementMapper.fromDomainToRest(elt))
                                    .collect(Collectors.toList()), new ArrayList<>())))
                            .collect(Collectors.toList()), this.clientMapper.fromDomainToRest(e.getClientDTO(), new ArrayList<>())))
                .sorted(Comparator.comparing(CommandeRest::getNom))
                .collect(Collectors.toList());
    }

    @GetMapping(params={HttpUrlConstantes.ID_COMPO_PARAM})
    public List<CommandeRest> getByIdCompo(@RequestParam(name="id-compo") Long idCompo){
        List<CommandeDTO> commandeDTOList = this.commandeService.getByIdCompo(idCompo);
        return commandeDTOList.stream()
                .map(e -> this.commandeMapper.fromDomainToRest(e, new ArrayList<>(), this.clientMapper.fromDomainToRest(e.getClientDTO(), new ArrayList<>())))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/"+HttpUrlConstantes.ID_PV)
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        this.commandeService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/"+HttpUrlConstantes.ID_PV+"/"+HttpUrlConstantes.DEVIS)
    public ResponseEntity<byte[]> downloadFile(@PathVariable(name="id") Long id) throws Exception {
        CommandeDTO commandeDTO = this.commandeService.getById(id);
        byte[] isr = Files.readAllBytes(this.constructionDevis.manipulatePdf(commandeDTO));
        String fileName = "employees.json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(isr.length);
        respHeaders.setContentType(MediaType.APPLICATION_PDF);
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(isr, respHeaders, HttpStatus.OK);
    }
}
