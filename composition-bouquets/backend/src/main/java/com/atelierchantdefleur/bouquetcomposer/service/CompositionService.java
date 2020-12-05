package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.EvenementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ImageCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Composition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.repository.CompositionRepository;
import com.atelierchantdefleur.bouquetcomposer.repository.ImageCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CompositionService {

    private final EvenementService evenementService;
    private final CompositionRepository compositionRepository;
    private final ImageCompositionRepository imageCompositionRepository;
    private final CompositionMapper compositionMapper;
    private final ElementCompositionMapper elementCompositionMapper;
    private final ImageCompositionMapper imageCompositionMapper;

    @Autowired
    public CompositionService (CompositionRepository compositionRepository, CompositionMapper compositionMapper,
                               ElementCompositionMapper elementCompositionMapper, ImageCompositionMapper imageCompositionMapper,
                               ImageCompositionRepository imageCompositionRepository, EvenementService evenementService){
        this.compositionRepository = compositionRepository;
        this.compositionMapper = compositionMapper;
        this.elementCompositionMapper = elementCompositionMapper;
        this.imageCompositionMapper = imageCompositionMapper;
        this.imageCompositionRepository = imageCompositionRepository;
        this.evenementService = evenementService;
    }

    public CompositionDTO save(CompositionDTO compositionDTO){
        List<ElementComposition> elementCompositions = new ArrayList<>();
        compositionDTO.getElementsComposition().forEach(e ->{
            IntStream.range(0, e.getQuantite()).forEach(i ->{
                elementCompositions.add(this.elementCompositionMapper.fromDomainToEntity(e));
            });
        });
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, imageCompositions);
        Composition composition = this.compositionRepository.save(compositionToSave);
        return this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition(), new ArrayList<>());
    }

    public CompositionDTO saveInEvt(CompositionDTO compositionDTO, Long idEvt){
        List<ElementComposition> elementCompositions = new ArrayList<>();
        compositionDTO.getElementsComposition().forEach(e ->{
            IntStream.range(0, e.getQuantite()).forEach(i ->{
                elementCompositions.add(this.elementCompositionMapper.fromDomainToEntity(e));
            });
        });
        List<ImageComposition> imageCompositions = compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToEntity)
                .collect(Collectors.toList());
        Composition compositionToSave = this.compositionMapper.fromDomainToEntity(compositionDTO, elementCompositions, imageCompositions);
        Composition composition = this.compositionRepository.save(compositionToSave);
        EvenementDTO evenementDTO = this.evenementService.getById(idEvt);
        CompositionDTO compoDTOSaved = this.compositionMapper.fromEntityToDomain(composition, compositionDTO.getElementsComposition(), new ArrayList<>());
        evenementDTO.addComposition(compoDTOSaved);
        this.evenementService.save(evenementDTO);
        return compoDTOSaved;
    }

    public List<CompositionDTO> getAll(){
        List<Composition> composition = this.compositionRepository.findAll();
           return composition.stream()
                .map(c -> this.compositionMapper
                        .fromEntityToDomain(c,
                                c.getElements().stream()
                                        .map(elt -> this.elementCompositionMapper.fromEntityToDomain(elt, this.calculQuantiteElement(c.getElements(), elt.getId())))
                                        .collect(Collectors.toList()),
                                c.getImages().stream()
                                        .map(this.imageCompositionMapper::fromEntityToDomain)
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public CompositionDTO getById(Long id){
        Composition composition = this.compositionRepository.findById(id).orElseThrow(() -> new RuntimeException("La composition n'existe pas"));
        List<ElementCompositionDTO> elementCompositionDTO = composition.getElements().stream()
                .map(elt ->this.elementCompositionMapper.fromEntityToDomain(elt, this.calculQuantiteElement(composition.getElements(), elt.getId())))
                .collect(Collectors.toList());
        List<ImageCompositionDTO> imageCompositionDTOS = composition.getImages().stream().map(this.imageCompositionMapper::fromEntityToDomain)
                .collect(Collectors.toList());
        return this.compositionMapper.fromEntityToDomain(composition, elementCompositionDTO, imageCompositionDTOS);
    }

    public CompositionDTO updateImage(Long id, ImageCompositionDTO imageCompositionDTO){
        CompositionDTO compositionDTO = this.getById(id);
        compositionDTO.addImageComposition(imageCompositionDTO);
        List<ElementComposition> elementCompositions = compositionDTO.getElementsComposition().stream()
                .map(this.elementCompositionMapper::fromDomainToEntity)
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
        List<EvenementDTO> evenementDTOList = this.evenementService.getByIdCompo(id);
        evenementDTOList.forEach(e ->{
            e.setCompositions(e.getCompositions().stream().filter(c -> !Objects.equals(c.getId(), id)).collect(Collectors.toList()));
            this.evenementService.save(e);
        });
        compositionDTO.getImagesComposition().forEach(i -> this.imageCompositionRepository.deleteById(i.getId()));
        this.compositionRepository.deleteById(id);
    }

    private Long calculQuantiteElement(List<ElementComposition> eltComposition, Long id){
        return eltComposition.stream().filter(e -> Objects.equals(e.getId(), id)).count();
    }
}
