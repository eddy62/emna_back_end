import { Moment } from 'moment';
import { IAvenant } from 'app/shared/model/avenant.model';
import { IArticle } from 'app/shared/model/article.model';
import { IClause } from 'app/shared/model/clause.model';

export interface IContrat {
  id?: number;
  titre?: string;
  dateCreation?: Moment;
  signe?: boolean;
  archive?: boolean;
  listeAvenants?: IAvenant[];
  employeId?: number;
  societeId?: number;
  listeArticles?: IArticle[];
  listeClauses?: IClause[];
}

export const defaultValue: Readonly<IContrat> = {
  signe: false,
  archive: false
};
