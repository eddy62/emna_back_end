import { Moment } from 'moment';

export interface IAvanceRappelSalaire {
  id?: number;
  type?: string;
  debutPeriode?: string;
  finPeriode?: string;
  montant?: number;
  mois?: number;
  annee?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IAvanceRappelSalaire> = {};
