import { ElementComposition } from './ElementComposition';

export class CompositionDisplay{
  id: number;
  dateCreation: Date;
  dureeCreation: number;
  prixUnitaire: number;
  tiges: ElementComposition[];
  materiaux: ElementComposition[];
}
