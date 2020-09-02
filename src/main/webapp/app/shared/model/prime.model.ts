export interface IPrime {
  id?: number;
  type?: string;
  montant?: number;
  mois?: number;
  annee?: number;
  typePrimeId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IPrime> = {};
