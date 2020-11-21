package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ImageCompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ImageComposition;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ImageCompositionRest;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Objects;

@Component
public class ImageCompositionMapper {

    public ImageCompositionDTO fromEntityToDomain(ImageComposition imageComposition){
        return new ImageCompositionDTO(
                imageComposition.getId(),
                Objects.isNull(imageComposition.getNom()) ? null : imageComposition.getNom(),
                Objects.isNull(imageComposition.getDescription()) ? null : imageComposition.getDescription(),
                imageComposition.getContent());
    }

    public ImageComposition fromDomainToEntity(ImageCompositionDTO imageCompositionDTO){
        ImageComposition imageComposition = new ImageComposition();
        imageComposition.setId(Objects.equals(imageCompositionDTO.getId(), 0) ? null : imageCompositionDTO.getId());
        imageComposition.setNom(Objects.isNull(imageCompositionDTO.getNom()) ? null : imageCompositionDTO.getNom());
        imageComposition.setContent(imageCompositionDTO.getContent());
        imageComposition.setDescription(Objects.isNull(imageCompositionDTO.getDescription()) ? null : imageCompositionDTO.getDescription());
        return imageComposition;
    }

    public ImageCompositionRest fromDomainToRest(ImageCompositionDTO imageCompositionDTO){
        ImageCompositionRest imageCompositionRest = new ImageCompositionRest();
        imageCompositionRest.setId(imageCompositionDTO.getId());
        imageCompositionRest.setNom(Objects.isNull(imageCompositionDTO.getNom()) ? null : imageCompositionDTO.getNom());
        imageCompositionRest.setDescription(Objects.isNull(imageCompositionDTO.getDescription()) ? null : imageCompositionDTO.getDescription());
        imageCompositionRest.setContent(imageCompositionDTO.getContent());
        return imageCompositionRest;
    }

    public ImageCompositionDTO fromRestToDomain(ImageCompositionRest imageCompositionRest){
        return new ImageCompositionDTO(
                Objects.isNull(imageCompositionRest.getId()) ? null : imageCompositionRest.getId(),
                Objects.isNull(imageCompositionRest.getNom()) ? null : imageCompositionRest.getNom(),
                Objects.isNull(imageCompositionRest.getDescription()) ? null : imageCompositionRest.getDescription(),
                Objects.isNull(imageCompositionRest.getContent()) ? new byte[0] : imageCompositionRest.getContent());
    }
}
