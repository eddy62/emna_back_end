import { IDocument } from 'app/shared/model/document.model';

export interface INoteDeFrais {
  id?: number;
  designation?: string;
  date?: string;
  montant?: number;
  mois?: number;
  annee?: number;
  listeDocuments?: IDocument[];
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<INoteDeFrais> = {};
