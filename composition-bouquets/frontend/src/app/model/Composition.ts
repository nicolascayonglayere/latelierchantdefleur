import { ElementComposition } from 'src/app/model/ElementComposition';
export class Composition {
  id: number;
  dateCreation: Date;
  dureeCreation: number;
  prixUnitaire: number;
  elements: ElementComposition[];
}
