import { ElementComposition } from './ElementComposition';

export class CompositionDisplay{
  id: number;
  nom: string;
  dateCreation: Date;
  dureeCreation: number;
  prixUnitaire: number;
  tiges: ElementComposition[];
  materiaux: ElementComposition[];
  qteImage: number;
}
