export interface IHeuresSupplementaires {
  id?: number;
  date?: string;
  nombreHeure?: number;
  mois?: number;
  annee?: number;
  etatVariablePaieId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IHeuresSupplementaires> = {};
