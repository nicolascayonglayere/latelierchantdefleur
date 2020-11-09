import { Materiau } from './Materiau';
import { Tige } from './Tige';
export class Composition {
  id: number;
  dateCreation: Date;
  dureeCreation: number;
  prixUnitaire: number;
  tiges: Tige[];
  materiaux: Materiau[];
}
