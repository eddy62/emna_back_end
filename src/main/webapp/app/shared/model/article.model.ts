import { IClause } from 'app/shared/model/clause.model';
import { IAvenant } from 'app/shared/model/avenant.model';
import { IContrat } from 'app/shared/model/contrat.model';

export interface IArticle {
  id?: number;
  reference?: string;
  titre?: string;
  description?: string;
  listeClauses?: IClause[];
  listeAvenants?: IAvenant[];
  listeContrats?: IContrat[];
  societeId?: number;
}

export const defaultValue: Readonly<IArticle> = {};
