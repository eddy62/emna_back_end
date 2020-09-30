import { IDocument } from 'app/shared/model/document.model';

export interface IFichePaie {
  id?: number;
  debutPeriode?: string;
  finPeriode?: string;
  mois?: number;
  annee?: number;
  listeDocuments?: IDocument[];
  employeId?: number;
}

export const defaultValue: Readonly<IFichePaie> = {};
