import { IClause } from 'app/shared/model/clause.model';

export interface IAvenant {
  id?: number;
  reference?: string;
  signe?: boolean;
  contratId?: number;
  listeClauses?: IClause[];
}

export const defaultValue: Readonly<IAvenant> = {
  signe: false,
};
