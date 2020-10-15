import { Moment } from 'moment';
import { ISaisieArticle } from 'app/shared/model/saisie-article.model';
import { IDocument } from 'app/shared/model/document.model';

export interface IAvenant {
  id?: number;
  reference?: string;
  signe?: boolean;
  dateDeCreation?: string;
  dateDeSignature?: string;
  listeSaisieArticles?: ISaisieArticle[];
  listeDocuments?: IDocument[];
}

export const defaultValue: Readonly<IAvenant> = {
  signe: false
};
