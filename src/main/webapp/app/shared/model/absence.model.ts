import { Moment } from 'moment';

export interface IAbsence {
  id?: number;
  debutAbsence?: string;
  finAbsence?: string;
  justificatif?: string;
  mois?: number;
  annee?: number;
  typeAbsenceId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAbsence> = {};
