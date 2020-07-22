import { Moment } from 'moment';

export interface IAutresVariable {
  id?: number;
  description?: string;
  date?: string;
  montant?: number;
  justificatif?: string;
  employeId?: number;
}

export const defaultValue: Readonly<IAutresVariable> = {};
