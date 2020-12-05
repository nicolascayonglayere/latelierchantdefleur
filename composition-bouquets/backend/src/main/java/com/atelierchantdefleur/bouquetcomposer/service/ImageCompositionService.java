package com.atelierchantdefleur.bouquetcomposer.service;

import com.atelierchantdefleur.bouquetcomposer.repository.ImageCompositionRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageCompositionService {

    private final ImageCompositionRepository imageCompositionRepository;

    public ImageCompositionService(ImageCompositionRepository imageCompositionRepository){
        this.imageCompositionRepository = imageCompositionRepository;
    }

    public void delete(Long id){
        this.imageCompositionRepository.deleteById(id);
    }
}
