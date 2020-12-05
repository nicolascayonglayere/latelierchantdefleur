import { ImageComposition } from './ImageComposition';
import { ElementComposition } from './ElementComposition';
export class Composition {
  id: number;
  nom: string;
  dateCreation: Date;
  dureeCreation: number;
  prixUnitaire: number;
  elements: ElementComposition[];
  images: ImageComposition[];
  tva: number;
}
