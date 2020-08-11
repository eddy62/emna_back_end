import { Moment } from 'moment';
import { IOperation } from 'app/shared/model/operation.model';
import { IDocument } from 'app/shared/model/document.model';

export interface IReleve {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  solde?: number;
  banque?: string;
  cheminFichier?: string;
  listeOperations?: IOperation[];
  listeDocuments?: IDocument[];
  etatReleveId?: number;
  societeId?: number;
}

export const defaultValue: Readonly<IReleve> = {};
