import { IContrat } from 'app/shared/model/contrat.model';
import { IAvenant } from 'app/shared/model/avenant.model';

export interface IClause {
  id?: number;
  reference?: string;
  description?: string;
  listeContrats?: IContrat[];
  listeAvenants?: IAvenant[];
  societeId?: number;
}

export const defaultValue: Readonly<IClause> = {};
