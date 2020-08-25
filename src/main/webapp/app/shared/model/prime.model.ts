export interface IPrime {
  id?: number;
  type?: string;
  montant?: number;
  typePrimeId?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IPrime> = {};
