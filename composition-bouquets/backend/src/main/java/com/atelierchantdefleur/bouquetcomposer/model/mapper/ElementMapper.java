package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.ElementDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.ElementComposition;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Element;
import com.atelierchantdefleur.bouquetcomposer.model.rest.ElementRest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ElementMapper {

    public ElementRest fromDomainToRest(ElementDTO elementDTO){
        ElementRest elementRest = new ElementRest();
        elementRest.setId(elementDTO.getId());
        elementRest.setNom(elementDTO.getNom());
        elementRest.setType(elementDTO.getType());
        elementRest.setQuantite(elementDTO.getQuantite());
        elementRest.setPrixUnitaire(elementDTO.getPrixUnitaire());
        return elementRest;
    }

    public ElementDTO fromRestToDomain(ElementRest elementRest){
        return new ElementDTO(
        elementRest.getId(),
        elementRest.getType(),
        elementRest.getNom(),
        elementRest.getQuantite(),
        elementRest.getPrixUnitaire());
    }

    public ElementDTO fromEntityToDomain(Element element, Double quantite){
        return new ElementDTO(
                element.getId(),
                element.getType(),
                element.getNom(),
                quantite,
                element.getPrixUnitaire());
    }

    public Element fromDomainToEntityEltCompo(ElementDTO elementDTO){
        Element element = new Element();
        element.setId(Objects.isNull(elementDTO.getId()) ? null : elementDTO.getId());
        element.setNom(elementDTO.getNom());
        element.setType(element.getType());
        element.setPrixUnitaire(element.getPrixUnitaire());
        return element;
    }

    public ElementComposition fromDomainToEntityQteEltCompo(ElementDTO elementDTO){
        ElementComposition elementComposition = new ElementComposition();
        elementComposition.setElement(this.fromDomainToEntityEltCompo(elementDTO));
        elementComposition.setComposition(null);
        elementComposition.setId(null);
        elementComposition.setQuantite(elementDTO.getQuantite());
        return elementComposition;
    }
}
