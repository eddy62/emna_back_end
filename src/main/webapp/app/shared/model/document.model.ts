export interface IDocument {
  id?: number;
  cheminFichier?: string;
  type?: string;
  nom?: string;
  factureId?: number;
  releveId?: number;
  contratId?: number;
  employeId?: number;
}

export const defaultValue: Readonly<IDocument> = {};
