import { Moment } from 'moment';

export interface IAbsence {
  id?: number;
  debutAbsence?: Moment;
  finAbsence?: Moment;
  justificatif?: string;
  typeAbsenceId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAbsence> = {};
