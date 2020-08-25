import { Moment } from 'moment';
import { IDocument } from 'app/shared/model/document.model';
import { ILigneProduit } from 'app/shared/model/ligne-produit.model';

export interface IFacture {
  id?: number;
  numfact?: number;
  nom?: string;
  type?: string;
  message?: string;
  date?: string;
  dateEcheance?: string;
  prixHT?: number;
  prixTTC?: number;
  tva?: number;
  moyenDePaiement?: string;
  listeDocuments?: IDocument[];
  listeLigneProduits?: ILigneProduit[];
  etatFactureId?: number;
  adresseId?: number;
  societeId?: number;
  operationId?: number;
  clientFournisseurId?: number;
}

export const defaultValue: Readonly<IFacture> = {};
