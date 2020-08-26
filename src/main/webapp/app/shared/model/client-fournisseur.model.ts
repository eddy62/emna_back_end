import { IFacture } from 'app/shared/model/facture.model';
import { IDevis } from 'app/shared/model/devis.model';

export interface IClientFournisseur {
  id?: number;
  nom?: string;
  siren?: number;
  telephone?: string;
  email?: string;
  listeFactures?: IFacture[];
  listeDevis?: IDevis[];
  adresseId?: number;
  societeId?: number;
}

export const defaultValue: Readonly<IClientFournisseur> = {};
