import { Moment } from 'moment';
import { IDocument } from 'app/shared/model/document.model';
import { IProduit } from 'app/shared/model/produit.model';

export interface IFacture {
  id?: number;
  numfact?: number;
  nom?: string;
  message?: string;
  date?: Moment;
  dateEcheance?: Moment;
  prixHT?: number;
  prixTTC?: number;
  tva?: number;
  moyenDePaiement?: string;
  adresseId?: number;
  listeDocuments?: IDocument[];
  etatFactureId?: number;
  societeId?: number;
  operationId?: number;
  clientFournisseurId?: number;
  listeProduits?: IProduit[];
}

export const defaultValue: Readonly<IFacture> = {};
