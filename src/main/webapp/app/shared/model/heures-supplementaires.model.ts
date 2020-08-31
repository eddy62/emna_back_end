import { Moment } from 'moment';

export interface IHeuresSupplementaires {
  id?: number;
  date?: string;
  nombreHeure?: number;
  justificatif?: string;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IHeuresSupplementaires> = {};
