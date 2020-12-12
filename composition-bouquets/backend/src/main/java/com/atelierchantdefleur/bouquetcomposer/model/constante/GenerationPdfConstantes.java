package com.atelierchantdefleur.bouquetcomposer.model.constante;

public enum GenerationPdfConstantes {
    DEVIS_PATH ("Devis_"),
    PDF_EXT_FILE (".pdf"),
    QUANTITE_CELL ("Quantité"),
    PRIX_UNIT_CELL ("Prix unitaire"),
    MONTANT_CELL ("Montant"),
    COMPO_SANS_NOM ("Composition sans nom"),
    SOUS_TOTAL_CELL ("SOUS TOTAL"),
    DESIGN_LOGISTIQUE_CELL ("Design et logistique"),
    FORFAIT_DPLCT_CELL ("Forfait déplacement"),
    FORFAIT_MO_CELL ("Forfait main d'oeuvre"),
    TOTAL_CELL ("TOTAL"),
    TVA_CELL("dont TVA"),
    EURO ("€"),
    ;


    private String generationPdfConstante;

    GenerationPdfConstantes(String value) {
        this.generationPdfConstante = value;
    }

    @Override
    public String toString(){
        return this.generationPdfConstante;
    }
}
