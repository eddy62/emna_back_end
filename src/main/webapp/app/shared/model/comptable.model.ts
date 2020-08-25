import { ISociete } from 'app/shared/model/societe.model';

export interface IComptable {
  id?: number;
  civilite?: string;
  adresseId?: number;
  infoEntrepriseId?: number;
  userId?: number;
  listeSocietes?: ISociete[];
}

export const defaultValue: Readonly<IComptable> = {};
