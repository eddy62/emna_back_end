export interface IProduit {
  id?: number;
  nom?: string;
  reference?: string;
  tva?: number;
  prix?: number;
  unite?: string;
  description?: string;
  societeId?: number;
}

export const defaultValue: Readonly<IProduit> = {};
