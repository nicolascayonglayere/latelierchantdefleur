import { Fournisseur } from './Fournisseur';
export class Tige {
    id: number;
    nom: string;
    nomLatin: string;
    prixUnitaire: number;
    fournisseurRest: Fournisseur;
}
