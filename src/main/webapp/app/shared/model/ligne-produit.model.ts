export interface ILigneProduit {
  id?: number;
  quantite?: number;
  commentaire?: string;
  remise?: number;
  produitId?: number;
  factureId?: number;
  devisId?: number;
}

export const defaultValue: Readonly<ILigneProduit> = {};
