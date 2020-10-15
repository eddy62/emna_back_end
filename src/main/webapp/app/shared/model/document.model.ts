export interface IDocument {
  id?: number;
  cheminFichier?: string;
  nom?: string;
  typeDocumentId?: number;
  factureId?: number;
  releveId?: number;
  contratId?: number;
  employeId?: number;
  depenseId?: number;
  absenceId?: number;
  noteDeFraisId?: number;
  autresVariableId?: number;
  devisId?: number;
  dpaeId?: number;
  fichePaieId?: number;
  avenantId?: number;
}

export const defaultValue: Readonly<IDocument> = {};
