import { Moment } from 'moment';

export interface IHeuresSupplementaires {
  id?: number;
  date?: string;
  nombreHeure?: number;
  justificatif?: string;
  mois?: number;
  annee?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IHeuresSupplementaires> = {};
