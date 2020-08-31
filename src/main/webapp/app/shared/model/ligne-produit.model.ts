export interface ILigneProduit {
  id?: number;
  quantite?: number;
  produitId?: number;
  factureId?: number;
  devisId?: number;
}

export const defaultValue: Readonly<ILigneProduit> = {};
