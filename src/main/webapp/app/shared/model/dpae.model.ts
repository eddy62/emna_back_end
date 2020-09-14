import { Moment } from 'moment';

export interface IDpae {
  id?: number;
  lieu?: string;
  date?: string;
  lienDocument?: string;
  employeId?: number;
  societeId?: number;
}

export const defaultValue: Readonly<IDpae> = {};
