import { IDocument } from 'app/shared/model/document.model';

export interface IAbsence {
  id?: number;
  debutAbsence?: string;
  finAbsence?: string;
  mois?: number;
  annee?: number;
  listeDocuments?: IDocument[];
  typeAbsenceId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAbsence> = {};
