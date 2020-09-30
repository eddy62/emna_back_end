import { ILigneProduit } from 'app/shared/model/ligne-produit.model';
import { IDocument } from 'app/shared/model/document.model';

export interface IDevis {
  id?: number;
  numDevis?: number;
  nom?: string;
  message?: string;
  dateCreation?: string;
  dateLimite?: string;
  listeLigneProduits?: ILigneProduit[];
  listeDocuments?: IDocument[];
  etatDevisId?: number;
  clientFournisseurId?: number;
}

export const defaultValue: Readonly<IDevis> = {};
