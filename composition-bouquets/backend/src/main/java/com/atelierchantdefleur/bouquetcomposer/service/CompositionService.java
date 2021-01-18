package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.*;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.ElementCompositionRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.ImageCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompositionService {

    private final CommandeService commandeService;
    private final CompositionRepository compositionRepository;
    private final ImageCompositionRepository imageCompositionRepository;
    private final ElementCompositionRepository elementCompositionRepository;
    private final CompositionMapper compositionMapper;
    private final ElementMapper elementMapper;
    private final ImageCompositionMapper imageCompositionMapper;
    private final ElementService elementService;

    @Autowired
    public CompositionService (CompositionRepository compositionRepository, CompositionMapper compositionMapper,
                               ElementMapper elementMapper, ImageCompositionMapper imageCompositionMapper,
                               ImageCompositionRepository imageCompositionRepository, CommandeService commandeService,
                               ElementService elementService, ElementCompositionRepository elementCompositionRepository){
        this.compositionRepository = compositionRepository;
        this.compositionMapper = compositionMapper;
        this.elementMapper = elementMapper;
        this.imageCompositionMapper = imageCompositionMapper;
        this.imageCompositionRepository = imageCompositionRepository;
        this.commandeService = commandeService;
        this.elementService = elementService;
        this.elementCompositionRepository = elementCompositionRepository;
    }

    public CompositionDTO save(CompositionDTO compositionDTO){
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition()
                .stream()
                .map(this.elementMapper::fromDomainToEntityQteEltCompo)
                .collect(Collectors.toList());

//                .forEach(e ->{
//            IntStream.range(0, e.getQuantite()).forEach(i ->{
//                elementCompositions.add(this.elementCompositionMapper.fromDomainToEntity(e));
//            });
//        });
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

    public CompositionDTO saveInEvt(CompositionDTO compositionDTO, Long idEvt){
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition().stream()
                .map(this.elementMapper::fromDomainToEntityQteEltCompo)
                .collect(Collectors.toList());



//                .forEach(e ->{
//            IntStream.range(0, e.getQuantite()).forEach(i ->{
//                elementCompositions.add(this.elementCompositionMapper.fromDomainToEntity(e));
//            });
//        });
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, imageCompositions);
        elementCompositions.stream().forEach(e -> e.setComposition(compositionToSave));
        Composition composition = this.compositionRepository.save(compositionToSave);
        CommandeDTO commandeDTO = this.commandeService.getById(idEvt);
        CompositionDTO compoDTOSaved = this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition(), new ArrayList<>());
        commandeDTO.addComposition(new CompositionCommandeDTO(
                compoDTOSaved.getId(),
                compositionDTO,
                1
        ));
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
        CompositionDTO compositionDTO = this.getById(id);
        compositionDTO.getImagesComposition().forEach(i -> this.imageCompositionRepository.deleteById(i.getId()));
        this.compositionRepository.deleteById(id);
    }

    public void deleteByIdFromEvt(Long id){
        CompositionDTO compositionDTO = this.getById(id);
        List<CommandeDTO> commandeDTOList = this.commandeService.getByIdCompo(id);
        commandeDTOList.forEach(e ->{
            e.setCompositions(e.getCompositions().stream().filter(c -> !Objects.equals(c.getId(), id)).collect(Collectors.toList()));
            this.commandeService.save(e);
        });
        compositionDTO.getImagesComposition().forEach(i -> this.imageCompositionRepository.deleteById(i.getId()));
        this.compositionRepository.deleteById(id);
    }

//    private Long calculQuantiteElement(List<ElementComposition> eltComposition, Long id){
//        return eltComposition.stream().filter(e -> Objects.equals(e.getId(), id)).count();
//    }
}
