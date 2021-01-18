//package com.atelierchantdefleur.bouquetcomposer.service.edition;
//
//import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
//import com.atelierchantdefleur.bouquetcomposer.model.domain.CommandeDTO;
//import org.dom4j.DocumentException;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ConstructionDevisITTest {
//
//    private ConstructionDevis constructionDevis = new ConstructionDevis(null);
//
//    private CommandeDTO commandeDTOMock;
//
//    @Test
//    public void test() throws IOException, DocumentException {
//        this.commandeDTOMock = new CommandeDTO();
//        this.commandeDTOMock.setId(100L);
//        this.commandeDTOMock.setNom("TEST");
//        this.commandeDTOMock.setForfaitDplct(100);
//        this.commandeDTOMock.setForfaitMo(250);
//
//        List<CompositionDTO> compositionDTOSMock = new ArrayList<>();
//        CompositionDTO compositionDTOMock = new CompositionDTO();
//        compositionDTOMock.setId(1L);
//        compositionDTOMock.setTva(20);
//        compositionDTOMock.setNom("COMPO 1");
//        compositionDTOMock.setPrixUnitaire(25d);
//        compositionDTOMock.setImagesComposition(new ArrayList<>());
//        compositionDTOMock.setDureeCreation(LocalTime.of(0, 10));
//        compositionDTOMock.setDateCreation(LocalDate.of(2020, 12, 12));
//        compositionDTOMock.setElementsComposition(new ArrayList<>());
//
//        CompositionDTO compositionDTOMock1 = new CompositionDTO();
//        compositionDTOMock1.setId(2L);
//        compositionDTOMock1.setTva(20);
//        compositionDTOMock1.setNom("COMPO 2");
//        compositionDTOMock1.setPrixUnitaire(75d);
//        compositionDTOMock1.setImagesComposition(new ArrayList<>());
//        compositionDTOMock1.setDureeCreation(LocalTime.of(0, 10));
//        compositionDTOMock1.setDateCreation(LocalDate.of(2020, 12, 12));
//        compositionDTOMock1.setElementsComposition(new ArrayList<>());
//
//        CompositionDTO compositionDTOMock2 = new CompositionDTO();
//        compositionDTOMock2.setId(3L);
//        compositionDTOMock2.setTva(20);
//        compositionDTOMock2.setNom("COMPO 3");
//        compositionDTOMock2.setPrixUnitaire(750d);
//        compositionDTOMock2.setImagesComposition(new ArrayList<>());
//        compositionDTOMock2.setDureeCreation(LocalTime.of(0, 10));
//        compositionDTOMock2.setDateCreation(LocalDate.of(2020, 12, 12));
//        compositionDTOMock2.setElementsComposition(new ArrayList<>());
//
//        compositionDTOSMock.add(compositionDTOMock);
//        compositionDTOSMock.add(compositionDTOMock1);
//        compositionDTOSMock.add(compositionDTOMock2);
//
//        this.commandeDTOMock.setCompositions(compositionDTOSMock);
//        this.constructionDevis.manipulatePdf(this.commandeDTOMock);
//    }
//}
