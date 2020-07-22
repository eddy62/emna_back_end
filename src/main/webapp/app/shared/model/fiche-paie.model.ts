import { Moment } from 'moment';

export interface IFichePaie {
  id?: number;
  debutPeriode?: string;
  finPeriode?: string;
  lienDocument?: string;
  employeId?: number;
}

export const defaultValue: Readonly<IFichePaie> = {};
