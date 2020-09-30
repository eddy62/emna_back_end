import { IFacture } from 'app/shared/model/facture.model';
import { IReleve } from 'app/shared/model/releve.model';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { IEmploye } from 'app/shared/model/employe.model';

export interface ISociete {
  id?: number;
  civilite?: string;
  infoEntrepriseId?: number;
  userId?: number;
  listeFactures?: IFacture[];
  listeReleves?: IReleve[];
  listeClientsFournisseurs?: IClientFournisseur[];
  listeEmployes?: IEmploye[];
  adresseId?: number;
  comptableId?: number;
}

export const defaultValue: Readonly<ISociete> = {};
