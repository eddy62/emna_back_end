import { IFacture } from 'app/shared/model/facture.model';
import { IDevis } from 'app/shared/model/devis.model';

export interface IProduit {
  id?: number;
  nom?: string;
  reference?: number;
  tva?: number;
  prix?: number;
  unite?: string;
  quantite?: string;
  description?: string;
  listeFactures?: IFacture[];
  listeDevis?: IDevis[];
  societeId?: number;
}

export const defaultValue: Readonly<IProduit> = {};
