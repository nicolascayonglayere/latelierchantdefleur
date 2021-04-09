import {Client} from './Client';
import {CompositionCommande} from './CompositionCommande';

export class Evenement {
  id: number;
  nom: string;
  dateCreation: Date;
  datePrevue: Date;
  compositions: CompositionCommande[];
  forfaitMo: number;
  forfaitDplct: number;
  idClientRest: number;
  budget: number;
}
