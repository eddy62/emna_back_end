import { IFacture } from 'app/shared/model/facture.model';
import { IDevis } from 'app/shared/model/devis.model';

export interface IClientFournisseur {
  id?: number;
  infoEntrepriseId?: number;
  listeFactures?: IFacture[];
  listeDevis?: IDevis[];
  societeId?: number;
}

export const defaultValue: Readonly<IClientFournisseur> = {};
