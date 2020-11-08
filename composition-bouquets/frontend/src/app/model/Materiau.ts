import { Fournisseur } from './Fournisseur';
export class Materiau {
    id: number;
    nom: string;
    prixUnitaire: number;
    fournisseurRest: Fournisseur;
}
