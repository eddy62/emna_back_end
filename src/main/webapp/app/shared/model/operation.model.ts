import { IFacture } from 'app/shared/model/facture.model';

export interface IOperation {
  id?: number;
  date?: string;
  description?: string;
  type?: string;
  rapproche?: boolean;
  solde?: number;
  listeFactures?: IFacture[];
  releveId?: number;
}

export const defaultValue: Readonly<IOperation> = {
  rapproche: false
};
