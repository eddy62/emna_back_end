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
  moyenDePaiement?: string;
  devisId?: number;
  listeDocuments?: IDocument[];
  listeLigneProduits?: ILigneProduit[];
  etatFactureId?: number;
  adresseId?: number;
  societeId?: number;
  operationId?: number;
  clientFournisseurId?: number;
}

export const defaultValue: Readonly<IFacture> = {};
