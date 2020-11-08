package com.atelierchantdefleur.bouquetcomposer.model.mapper;

import com.atelierchantdefleur.bouquetcomposer.model.domain.FournisseurDTO;
import com.atelierchantdefleur.bouquetcomposer.model.entity.Fournisseur;
import com.atelierchantdefleur.bouquetcomposer.model.rest.FournisseurRest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class FournisseurMapper {

    public FournisseurDTO fromEntityToDomain(Fournisseur fournisseur){
        return new FournisseurDTO(
                fournisseur.getId(),
                Objects.isNull(fournisseur.getNom()) ? null : fournisseur.getNom(),
                Objects.isNull(fournisseur.getNumeroSiret()) ? null : fournisseur.getNumeroSiret(),
                Objects.isNull(fournisseur.getAdresse()) ? null : fournisseur.getAdresse(),
                Objects.isNull(fournisseur.getCodePostal()) ? null : fournisseur.getCodePostal(),
                Objects.isNull(fournisseur.getVille()) ? null : fournisseur.getVille(),
                Objects.isNull(fournisseur.getEmail()) ? null : fournisseur.getEmail(),
                Objects.isNull(fournisseur.getTelephone()) ? null : fournisseur.getTelephone(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public FournisseurDTO fromRestToDomain(FournisseurRest fournisseurRest){
        return new FournisseurDTO(
                Objects.isNull(fournisseurRest.getId()) ? null : fournisseurRest.getId(),
                fournisseurRest.getNom(),
                Objects.isNull(fournisseurRest.getNumeroSiret())? null : fournisseurRest.getNumeroSiret(),
                Objects.isNull(fournisseurRest.getAdresse())? null : fournisseurRest.getAdresse(),
                Objects.isNull(fournisseurRest.getCodePostal())? null : fournisseurRest.getCodePostal(),
                Objects.isNull(fournisseurRest.getVille())? null : fournisseurRest.getVille(),
                Objects.isNull(fournisseurRest.getEmail())? null : fournisseurRest.getEmail(),
                Objects.isNull(fournisseurRest.getTelephone())? null : fournisseurRest.getTelephone(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public Fournisseur fromDomainToEntity(FournisseurDTO fournisseurDTO){
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(Objects.isNull(fournisseurDTO.getId()) ? null : fournisseurDTO.getId());
        fournisseur.setNom(fournisseurDTO.getNom());
        fournisseur.setAdresse(Objects.isNull(fournisseurDTO.getAdresse()) ? null : fournisseurDTO.getAdresse());
        fournisseur.setCodePostal(Objects.isNull(fournisseurDTO.getCodePostal()) ? null : fournisseurDTO.getCodePostal());
        fournisseur.setVille(Objects.isNull(fournisseurDTO.getVille()) ? null : fournisseurDTO.getVille());
        fournisseur.setEmail(Objects.isNull(fournisseurDTO.getEmail()) ? null : fournisseurDTO.getEmail());
        fournisseur.setTelephone(Objects.isNull(fournisseurDTO.getTelephone()) ? null : fournisseurDTO.getTelephone());
        fournisseur.setNumeroSiret(Objects.isNull(fournisseurDTO.getNumeroSiret()) ? null : fournisseurDTO.getNumeroSiret());
        fournisseur.setTiges(new ArrayList<>());
        fournisseur.setMateriaux(new ArrayList<>());
        return fournisseur;
    }

    public FournisseurRest fromDomainToRest(FournisseurDTO fournisseurDTO){
        FournisseurRest fournisseurRest = new FournisseurRest();
        fournisseurRest.setId(fournisseurDTO.getId());
        fournisseurRest.setNom(fournisseurDTO.getNom());
        fournisseurRest.setAdresse(Objects.isNull(fournisseurDTO.getAdresse()) ? null : fournisseurDTO.getAdresse());
        fournisseurRest.setCodePostal(Objects.isNull(fournisseurDTO.getCodePostal()) ? null : fournisseurDTO.getCodePostal());
        fournisseurRest.setVille(Objects.isNull(fournisseurDTO.getVille()) ? null : fournisseurDTO.getVille());
        fournisseurRest.setEmail(Objects.isNull(fournisseurDTO.getEmail()) ? null : fournisseurDTO.getEmail());
        fournisseurRest.setTelephone(Objects.isNull(fournisseurDTO.getTelephone()) ? null : fournisseurDTO.getTelephone());
        fournisseurRest.setNumeroSiret(Objects.isNull(fournisseurDTO.getNumeroSiret()) ? null : fournisseurDTO.getNumeroSiret());
        fournisseurRest.setTiges(new ArrayList<>());
        fournisseurRest.setMateriaux(new ArrayList<>());
        return fournisseurRest;
    }
}
