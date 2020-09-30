import { IDocument } from 'app/shared/model/document.model';

export interface IDpae {
  id?: number;
  lieu?: string;
  date?: string;
  listeDocuments?: IDocument[];
  employeId?: number;
}

export const defaultValue: Readonly<IDpae> = {};
