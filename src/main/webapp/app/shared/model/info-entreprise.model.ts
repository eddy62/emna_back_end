import { Moment } from 'moment';

export interface IInfoEntreprise {
  id?: number;
  raisonSociale?: string;
  telephone?: string;
  fax?: string;
  formeJuridique?: string;
  dateDeCreation?: string;
  siren?: string;
  siret?: string;
  domaineDactivite?: string;
  description?: string;
  email?: string;
  codeUrssaf?: string;
  serviceSanteTravail?: string;
}

export const defaultValue: Readonly<IInfoEntreprise> = {};
