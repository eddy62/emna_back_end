import { Moment } from 'moment';
import { ILigneProduit } from 'app/shared/model/ligne-produit.model';

export interface IDevis {
  id?: number;
  numDevis?: number;
  nom?: string;
  message?: string;
  dateCreation?: string;
  dateLimite?: string;
  prixHT?: number;
  prixTTC?: number;
  tva?: number;
  cheminFichier?: string;
  listeLigneProduits?: ILigneProduit[];
  etatDevisId?: number;
  societeId?: number;
  clientFournisseurId?: number;
}

export const defaultValue: Readonly<IDevis> = {};
