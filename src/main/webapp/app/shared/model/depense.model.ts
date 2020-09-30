import { IDocument } from 'app/shared/model/document.model';

export interface IDepense {
  id?: number;
  numero?: number;
  date?: string;
  prix?: number;
  moyenDePaiement?: string;
  raison?: string;
  listeDocuments?: IDocument[];
  societeId?: number;
  operationId?: number;
  clientFournisseurId?: number;
  etatDepenseId?: number;
}

export const defaultValue: Readonly<IDepense> = {};
