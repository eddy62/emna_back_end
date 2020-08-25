import { Moment } from 'moment';
import { IProduit } from 'app/shared/model/produit.model';

export interface IDevis {
  id?: number;
  numDevis?: number;
  nom?: string;
  message?: string;
  dateCreation?: Moment;
  dateLimite?: Moment;
  prixHT?: number;
  prixTTC?: number;
  tva?: number;
  cheminFichier?: string;
  etatDevisId?: number;
  societeId?: number;
  clientFournisseurId?: number;
  listeProduits?: IProduit[];
}

export const defaultValue: Readonly<IDevis> = {};
