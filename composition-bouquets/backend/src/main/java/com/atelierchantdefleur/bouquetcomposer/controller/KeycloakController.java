//package com.atelierchantdefleur.bouquetcomposer.controller;
//
//import com.atelierchantdefleur.bouquetcomposer.conf.KeycloakProperties;
//import com.atelierchantdefleur.bouquetcomposer.model.constante.HttpUrlConstantes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(KeycloakController.rootUrl)
//
//public class KeycloakController {
//
//    public static final String rootUrl = HttpUrlConstantes.ROOT_URL + "/" + HttpUrlConstantes.KEYCLOAK_API;
//
//    @Autowired
//    private KeycloakProperties config;
//
//    @Value("${keycloakRequiredUserRole}")
//    private String keycloakRequiredUserRole;
//
//    private void init(String requiredUserRole) {
//        this.config.setRequiredUserRole(requiredUserRole);
//    }
//
//    @CrossOrigin(origins = HttpUrlConstantes.CROSS_ORIGIN)
//    @GetMapping()
//    public KeycloakProperties config(){
//        this.init(this.keycloakRequiredUserRole);
//        return this.config;
//    }
//
//    public void setKeycloakRequiredUserRole(String keycloakRequiredUserRole) {
//        this.keycloakRequiredUserRole = keycloakRequiredUserRole;
//    }
//
//
//}
