import { IFacture } from 'app/shared/model/facture.model';
import { IDevis } from 'app/shared/model/devis.model';
import { IReleve } from 'app/shared/model/releve.model';
import { IProduit } from 'app/shared/model/produit.model';
import { IContrat } from 'app/shared/model/contrat.model';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { IClause } from 'app/shared/model/clause.model';
import { IArticle } from 'app/shared/model/article.model';
import { IEmploye } from 'app/shared/model/employe.model';
import { IDpae } from 'app/shared/model/dpae.model';

export interface ISociete {
  id?: number;
  civilite?: string;
  infoEntrepriseId?: number;
  userId?: number;
  listeFactures?: IFacture[];
  listeDevis?: IDevis[];
  listeReleves?: IReleve[];
  listeProduits?: IProduit[];
  listeContrats?: IContrat[];
  listeClientsFournisseurs?: IClientFournisseur[];
  listeClauses?: IClause[];
  listeArticles?: IArticle[];
  listeEmployes?: IEmploye[];
  listeDpaes?: IDpae[];
  adresseId?: number;
  comptableId?: number;
}

export const defaultValue: Readonly<ISociete> = {};
