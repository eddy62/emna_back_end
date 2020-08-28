import { Moment } from 'moment';

export interface INoteDeFrais {
  id?: number;
  designation?: string;
  date?: string;
  montant?: number;
  justificatif?: string;
  mois?: number;
  annee?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<INoteDeFrais> = {};
