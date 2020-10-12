import { Moment } from 'moment';
import { IDocument } from 'app/shared/model/document.model';

export interface IContrat {
  id?: number;
  titre?: string;
  dateCreation?: string;
  signe?: boolean;
  archive?: boolean;
  listeDocuments?: IDocument[];
  typeContratId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IContrat> = {
  signe: false,
  archive: false
};
