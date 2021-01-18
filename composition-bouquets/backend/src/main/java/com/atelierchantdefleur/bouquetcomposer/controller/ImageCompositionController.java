package com.atelierchantdefleur.bouquetcomposer.controller;

import com.atelierchantdefleur.bouquetcomposer.model.constante.HttpUrlConstantes;
import com.atelierchantdefleur.bouquetcomposer.service.CompositionService;
import com.atelierchantdefleur.bouquetcomposer.service.ImageCompositionService;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.ImageCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.CompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ElementMapper;
import com.atelierchantdefleur.bouquetcomposer.model.mapper.ImageCompositionMapper;
import com.atelierchantdefleur.bouquetcomposer.model.rest.CompositionRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementRest;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(ImageCompositionController.rootUrl)
@RestController
@CrossOrigin(HttpUrlConstantes.CROSS_ORIGIN)
public class ImageCompositionController {

    public static final String rootUrl = HttpUrlConstantes.ROOT_URL + "/"
            + HttpUrlConstantes.COMPOSITION_URL + "/"
            + HttpUrlConstantes.ID_PV +"/"
            + HttpUrlConstantes.IMAGES_URL;

    @Autowired
    private CompositionService compositionService;
    @Autowired
    private ImageCompositionMapper imageCompositionMapper;
    @Autowired
    private CompositionMapper compositionMapper;
    @Autowired
    private ElementMapper elementMapper;
    @Autowired
    private ImageCompositionService imageCompositionService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CompositionRest> save(@RequestParam("file")MultipartFile file, @PathVariable("id") Long id,
                                                @RequestParam("nom")String nom, @RequestParam ("description") String description){
        String message = "";
        try {
            ImageCompositionRest imageCompositionFake = new ImageCompositionRest();
            imageCompositionFake.setNom(nom);
            imageCompositionFake.setDescription(description);
            imageCompositionFake.setContent(file.getBytes());
            ImageCompositionDTO imageCompositionDTO = this.imageCompositionMapper.fromRestToDomain(imageCompositionFake);
            CompositionDTO compositionDTO = this.compositionService.updateImage(id, imageCompositionDTO);
            List<ElementRest> elementRests = compositionDTO.getElementsComposition().stream()
                    .map(this.elementMapper::fromDomainToRest)
                    .collect(Collectors.toList());
            List<ImageCompositionRest> imageCompositionRests = compositionDTO.getImagesComposition().stream()
                    .map(this.imageCompositionMapper::fromDomainToRest)
                    .collect(Collectors.toList());
            CompositionRest compositionRest = this.compositionMapper.fromDomainToRest(compositionDTO, elementRests, imageCompositionRests);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename() + " - " +imageCompositionFake.getNom();
            return ResponseEntity.status(HttpStatus.OK).body(compositionRest);
        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CompositionRest());
        }
    }

    @GetMapping()
    public List<ImageCompositionRest> getByIdComposition(@PathVariable("id") Long id){
        CompositionDTO compositionDTO = this.compositionService.getById(id);
        return compositionDTO.getImagesComposition().stream()
                .map(this.imageCompositionMapper::fromDomainToRest)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/"+HttpUrlConstantes.ID_IMG_PV)
    public ResponseEntity<String> delete(@PathVariable("id-img") Long idImg){
        this.imageCompositionService.delete(idImg);
        return ResponseEntity.ok(null);
    }
}
