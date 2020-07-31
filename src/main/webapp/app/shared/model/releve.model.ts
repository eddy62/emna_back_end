import { Moment } from 'moment';
import { IOperation } from 'app/shared/model/operation.model';

export interface IReleve {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  solde?: number;
  banque?: string;
  cheminFichier?: string;
  listeOperations?: IOperation[];
  etatReleveId?: number;
  societeId?: number;
}

export const defaultValue: Readonly<IReleve> = {};
