export interface IPrime {
  id?: number;
  montant?: number;
  mois?: number;
  annee?: number;
  typePrimeId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IPrime> = {};
