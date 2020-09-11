export interface IDocument {
  id?: number;
  cheminFichier?: string;
  type?: string;
  nom?: string;
  absenceId?: number;
  noteDeFraisId?: number;
  autresVariablesId?: number;
  factureId?: number;
  releveId?: number;
  contratId?: number;
  employeId?: number;
  depenseId?: number;
}

export const defaultValue: Readonly<IDocument> = {};
