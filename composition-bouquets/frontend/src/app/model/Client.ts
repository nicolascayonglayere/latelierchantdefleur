import { Evenement } from './Evenement';
export class Client {
  id: number;
  nom1: string;
  prenom1: string;
  nom2: string;
  prenom2: string;
  adresse: string;
  codePostal: string;
  ville: string;
  email: string;
  telephone: string;
  evenementsRest: Evenement[];
}
