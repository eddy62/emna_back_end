import { ISociete } from 'app/shared/model/societe.model';

export interface IComptable {
  id?: number;
  civilite?: string;
  infoEntrepriseId?: number;
  userId?: number;
  listeSocietes?: ISociete[];
  adresseId?: number;
}

export const defaultValue: Readonly<IComptable> = {};
