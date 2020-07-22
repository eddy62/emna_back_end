import { Moment } from 'moment';
import { IOperation } from 'app/shared/model/operation.model';

export interface IReleve {
  id?: number;
  dateDebut?: string;
  dateFin?: string;
  solde?: number;
  banque?: string;
  cheminFichier?: string;
  etatReleveId?: number;
  listeOperations?: IOperation[];
  societeId?: number;
}

export const defaultValue: Readonly<IReleve> = {};
