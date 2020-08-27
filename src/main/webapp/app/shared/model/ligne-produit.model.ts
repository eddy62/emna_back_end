export interface ILigneProduit {
  id?: number;
  quantite?: number;
  nom?: string;
  description?: string;
  tva?: number;
  prix?: number;
  factureId?: number;
  devisId?: number;
}

export const defaultValue: Readonly<ILigneProduit> = {};
