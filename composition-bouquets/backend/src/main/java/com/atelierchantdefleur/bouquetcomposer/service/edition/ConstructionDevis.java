package com.atelierchantdefleur.bouquetcomposer.service.edition;

import com.atelierchantdefleur.bouquetcomposer.conf.CheminConf;
import com.atelierchantdefleur.bouquetcomposer.model.constante.GenerationPdfConstantes;
import com.atelierchantdefleur.bouquetcomposer.model.domain.CompositionDTO;
import com.atelierchantdefleur.bouquetcomposer.model.domain.EvenementDTO;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class ConstructionDevis {

    private final CheminConf cheminConf;

    @Autowired
    public ConstructionDevis(CheminConf cheminConf) {
        this.cheminConf = cheminConf;
    }

    public Path manipulatePdf(EvenementDTO evenementDTO) throws IOException {
        List<CompositionDTO> compoSansDoublon = new ArrayList<>(new HashSet<>(evenementDTO.getCompositions()));
        String pdfOutput = Paths.get(this.cheminConf.getPdfTemplate()).getParent().toString() +
                "\\" +
                GenerationPdfConstantes.DEVIS_PATH.toString() +
                LocalDate.now().toString() +
                GenerationPdfConstantes.PDF_EXT_FILE.toString();

        PdfDocument newDoc = new PdfDocument(new PdfReader(this.cheminConf.getPdfTemplate()), new PdfWriter(pdfOutput));
        Document doc = new Document(newDoc);

        Table table1 = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table1.addHeaderCell("");
        Cell cell = new Cell().add(new Paragraph(GenerationPdfConstantes.QUANTITE_CELL.toString())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY))
                .setTextAlignment(TextAlignment.CENTER);
        table1.addHeaderCell(cell);
        table1.addHeaderCell(new Cell().add(new Paragraph(GenerationPdfConstantes.PRIX_UNIT_CELL.toString())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)))
                .setTextAlignment(TextAlignment.CENTER);
        table1.addHeaderCell(new Cell().add(new Paragraph(GenerationPdfConstantes.MONTANT_CELL.toString())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY))
                .setTextAlignment(TextAlignment.CENTER));
        compoSansDoublon.forEach(c -> {
            Cell cellNom = new Cell().add(new Paragraph(Objects.isNull(c.getNom()) ? GenerationPdfConstantes.COMPO_SANS_NOM.toString() : c.getNom())
                    .setTextAlignment(TextAlignment.LEFT));
            table1.addCell(cellNom);
            Cell cellQte = new Cell().add(new Paragraph(String.valueOf(this.calculQuantiteCompo(evenementDTO.getCompositions(), c.getId())))
                    .setTextAlignment(TextAlignment.RIGHT));
            table1.addCell(cellQte);
            Cell cellPu = new Cell().add(new Paragraph(String.valueOf(c.getPrixUnitaire() / 100f))
                    .setTextAlignment(TextAlignment.RIGHT));
            table1.addCell(cellPu);
            Cell cellMnt = new Cell().add(new Paragraph(new DecimalFormat("#.0#")
                    .format(c.getPrixUnitaire() / 100f * this.calculQuantiteCompo(evenementDTO.getCompositions(), c.getId())))
                    .setTextAlignment(TextAlignment.RIGHT));
            table1.addCell(cellMnt);
        });
        Cell cellVide = new Cell(0, 2);
        Cell cellSsTot = new Cell().add(new Paragraph(GenerationPdfConstantes.SOUS_TOTAL_CELL.toString() + " "
                + GenerationPdfConstantes.EURO.toString())
                .setTextAlignment(TextAlignment.LEFT));
        table1.addFooterCell(cellSsTot);
        table1.addFooterCell(cellVide);
        Double sousTot = this.calculSousTotal(evenementDTO.getCompositions(), compoSansDoublon);
        table1.addFooterCell(new DecimalFormat("#.0#").format(sousTot));

        Table table2 = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        Cell cell1 = new Cell(0, 4).add(new Paragraph(GenerationPdfConstantes.DESIGN_LOGISTIQUE_CELL.toString())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table2.addHeaderCell(cell1);
        Cell cellVide2 = new Cell(0, 2);
        table2.addCell(GenerationPdfConstantes.FORFAIT_DPLCT_CELL.toString());
        table2.addCell(cellVide2);
        Cell mntForfaitDplct = new Cell().add(new Paragraph(evenementDTO.getForfaitDplct().toString())
                .setTextAlignment(TextAlignment.RIGHT));
        table2.addCell(mntForfaitDplct);

        table2.addCell(GenerationPdfConstantes.FORFAIT_MO_CELL.toString());
        table2.addCell(cellVide2);
        Cell mntForfaitMo = new Cell().add(new Paragraph(evenementDTO.getForfaitMo().toString())
                .setTextAlignment(TextAlignment.RIGHT));
        table2.addCell(mntForfaitMo);

        table2.addFooterCell(GenerationPdfConstantes.TOTAL_CELL.toString() + " " + GenerationPdfConstantes.EURO.toString());
        Double total = sousTot + evenementDTO.getForfaitDplct() + evenementDTO.getForfaitMo();
        Cell cellTot = new Cell().add(new Paragraph(new DecimalFormat("#.0#").format(total))
                .setTextAlignment(TextAlignment.CENTER));
        table2.addFooterCell(cellTot);
        table2.addFooterCell(GenerationPdfConstantes.TVA_CELL.toString() + " " + GenerationPdfConstantes.EURO.toString());
        Double tva = this.calculTva(evenementDTO.getCompositions());
        Cell cellTotTva = new Cell().add(new Paragraph(new DecimalFormat("#.0#").format(tva))
                .setTextAlignment(TextAlignment.CENTER));
        table2.addFooterCell(cellTotTva);
        table2.setMarginTop(15);

        doc.add(table1);
        doc.add(table2);
        doc.close();
        return Paths.get(pdfOutput);
    }

    private Long calculQuantiteCompo(List<CompositionDTO> compositions, Long id){
        return compositions.
                stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .count();
    }

    private Double calculSousTotal(List<CompositionDTO> compositions, List<CompositionDTO> compoSansDoublon){
        return compoSansDoublon.stream()
            .mapToDouble(c -> (c.getPrixUnitaire() / 100f) * this.calculQuantiteCompo(compositions, c.getId()))
            .sum();
    }

    private Double calculTva(List<CompositionDTO> compositions){
        return compositions.stream()
                .mapToDouble(c -> (c.getPrixUnitaire()/100d) * (c.getTva() / 100d))
                .sum();
    }
}
