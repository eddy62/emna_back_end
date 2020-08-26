import { Moment } from 'moment';

export interface IAbsence {
  id?: number;
  debutAbsence?: string;
  finAbsence?: string;
  justificatif?: string;
  typeAbsenceId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAbsence> = {};
