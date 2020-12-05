import { Composition } from './Composition';
export class Evenement {
  id: number;
  nom: string;
  dateCreation: Date;
  datePrevue: Date;
  compositions: Composition[];
  forfaitMo: number;
  forfaitDplct: number;
}
