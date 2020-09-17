import { Moment } from 'moment';
import { IAvenant } from 'app/shared/model/avenant.model';
import { IDocument } from 'app/shared/model/document.model';
import { IClause } from 'app/shared/model/clause.model';

export interface IContrat {
  id?: number;
  titre?: string;
  dateCreation?: string;
  signe?: boolean;
  archive?: boolean;
  listeAvenants?: IAvenant[];
  listeDocuments?: IDocument[];
  employeId?: number;
  societeId?: number;
  listeClauses?: IClause[];
}

export const defaultValue: Readonly<IContrat> = {
  signe: false,
  archive: false,
};
