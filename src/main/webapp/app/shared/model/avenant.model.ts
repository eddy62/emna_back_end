import { IDocument } from 'app/shared/model/document.model';

export interface IAvenant {
  id?: number;
  reference?: string;
  signe?: boolean;
  listeDocuments?: IDocument[];
  saisieArticleId?: number;
}

export const defaultValue: Readonly<IAvenant> = {
  signe: false
};
