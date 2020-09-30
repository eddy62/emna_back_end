import { IDocument } from 'app/shared/model/document.model';

export interface IAutresVariable {
  id?: number;
  description?: string;
  date?: string;
  montant?: number;
  mois?: number;
  annee?: number;
  listeDocuments?: IDocument[];
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAutresVariable> = {};
