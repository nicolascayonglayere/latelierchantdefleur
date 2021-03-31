package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.*;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionCommandeRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.ElementCompositionRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.ImageCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompositionService {

    private final CommandeService commandeService;
    private final CompositionRepository compositionRepository;
    private final ImageCompositionRepository imageCompositionRepository;
    private final ElementCompositionRepository elementCompositionRepository;
    private final CompositionCommandeRepository compositionCommandeRepository;
    private final CompositionMapper compositionMapper;
    private final ElementMapper elementMapper;
    private final ImageCompositionMapper imageCompositionMapper;
    private final ElementService elementService;

    @Autowired
    public CompositionService (CompositionRepository compositionRepository, CompositionMapper compositionMapper,
                               ElementMapper elementMapper, ImageCompositionMapper imageCompositionMapper,
                               ImageCompositionRepository imageCompositionRepository, CommandeService commandeService,
                               ElementService elementService, ElementCompositionRepository elementCompositionRepository,
                               CompositionCommandeRepository compositionCommandeRepository){
        this.compositionRepository = compositionRepository;
        this.compositionMapper = compositionMapper;
        this.elementMapper = elementMapper;
        this.imageCompositionMapper = imageCompositionMapper;
        this.imageCompositionRepository = imageCompositionRepository;
        this.commandeService = commandeService;
        this.elementService = elementService;
        this.elementCompositionRepository = elementCompositionRepository;
        this.compositionCommandeRepository = compositionCommandeRepository;
    }

    public CompositionDTO save(CompositionDTO compositionDTO){
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition()
                .stream()
                .map(this.elementMapper::fromDomainToEntityQteEltCompo)
                .collect(Collectors.toList());
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, imageCompositions);
        Composition composition = this.compositionRepository.save(compositionToSave);
        compositionToSave.getElements().stream().forEach(e -> {
            e.setComposition(composition);
            this.elementCompositionRepository.save(e);
        });
        return this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition(), new ArrayList<>());
    }

    public CompositionDTO update(CompositionDTO compositionDTO){
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition()
                .stream()
                .map(this.elementMapper::fromDomainToEntityQteEltCompo)
                .collect(Collectors.toList());
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, imageCompositions);
        this.elementCompositionRepository.deleteAllByCompositionId(compositionToSave.getId());
        Composition composition = this.compositionRepository.save(compositionToSave);
        compositionToSave.getElements().stream().forEach(e -> {
            e.setComposition(composition);
            this.elementCompositionRepository.save(e);
        });
        return this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition(), new ArrayList<>());
    }

    public CompositionDTO saveInEvt(CompositionDTO compositionDTO, Long idEvt){
        CompositionDTO compoDTOSaved = this.save(compositionDTO);
        CommandeDTO commandeDTO = this.commandeService.getById(idEvt);
        CompositionCommandeDTO compositionCommandeDTO = new CompositionCommandeDTO(null, compoDTOSaved, 1);
        commandeDTO.addComposition(compositionCommandeDTO);
        this.commandeService.save(commandeDTO);
        return compoDTOSaved;
    }

    public List<CompositionDTO> getAll(){
        List<Composition> composition = this.compositionRepository.findAll();
           return composition.stream()
                .map(c -> this.compositionMapper
                        .fromEntityToDomain(c,
                                c.getElements().stream()
                                        .map(elt -> this.elementService.getByIdWithQuantite(elt.getElement().getId(), elt.getQuantite()))
                                        .collect(Collectors.toList()),
                                c.getImages().stream()
                                        .map(this.imageCompositionMapper::fromEntityToDomain)
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public CompositionDTO getById(Long id){
        Composition composition = this.compositionRepository.findById(id).orElseThrow(() -> new RuntimeException("La composition n'existe pas"));
        List<ElementDTO> elementDTO = composition.getElements().stream()
                .map(elt -> this.elementService.getByIdWithQuantite(elt.getElement().getId(), elt.getQuantite()))
                .collect(Collectors.toList());
        List<ImageCompositionDTO> imageCompositionDTOS = composition.getImages().stream().map(this.imageCompositionMapper::fromEntityToDomain)
                .collect(Collectors.toList());
        return this.compositionMapper.fromEntityToDomain(composition, elementDTO, imageCompositionDTOS);
    }

    public CompositionDTO updateImage(Long id, ImageCompositionDTO imageCompositionDTO){
        CompositionDTO compositionDTO = this.getById(id);
        compositionDTO.addImageComposition(imageCompositionDTO);
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToEntityQteEltCompo)
                .collect(Collectors.toList());
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compoToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, new ArrayList<>());
        imageCompositions.forEach(img -> img.setComposition(compoToSave));
        this.imageCompositionRepository.saveAll(imageCompositions);
        Composition compSave = this.compositionRepository.saveAndFlush(compoToSave);
        return compositionDTO;
    }

    public void deleteById(Long id){
        this.imageCompositionRepository.deleteAllByCompositionId(id);
        this.elementCompositionRepository.deleteAllByCompositionId(id);
        this.compositionRepository.deleteById(id);
    }

    public void deleteByIdFromEvt(Long id){
        this.compositionCommandeRepository.deleteAllByCompositionId(id);
        this.imageCompositionRepository.deleteAllByCompositionId(id);
        this.elementCompositionRepository.deleteAllByCompositionId(id);
        this.compositionRepository.deleteById(id);
    }
}
