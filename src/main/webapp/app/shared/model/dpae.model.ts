import { IDocument } from 'app/shared/model/document.model';

export interface IDpae {
  id?: number;
  lieu?: string;
  date?: string;
  heureEmbauche?: string;
  commentaire?: string;
  retourApiUrssaf?: string;
  contratId?: number;
  listeDocuments?: IDocument[];
}

export const defaultValue: Readonly<IDpae> = {};
