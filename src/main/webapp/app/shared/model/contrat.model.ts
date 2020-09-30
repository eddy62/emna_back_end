import { IAvenant } from 'app/shared/model/avenant.model';
import { IDocument } from 'app/shared/model/document.model';

export interface IContrat {
  id?: number;
  titre?: string;
  dateCreation?: string;
  signe?: boolean;
  archive?: boolean;
  listeAvenants?: IAvenant[];
  listeDocuments?: IDocument[];
  typeContratId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IContrat> = {
  signe: false,
  archive: false
};
