import { IAvenant } from 'app/shared/model/avenant.model';

export interface ISaisieArticle {
  id?: number;
  libelle?: string;
  listeAvenants?: IAvenant[];
  articleId?: number;
  contratId?: number;
}

export const defaultValue: Readonly<ISaisieArticle> = {};
