import { Moment } from 'moment';
import { IProduit } from 'app/shared/model/produit.model';

export interface IFacture {
  id?: number;
  numfact?: number;
  nom?: string;
  message?: string;
  date?: Moment;
  dateEcheance?: Moment;
  prixHT?: number;
  prixTTC?: number;
  tva?: number;
  fichier?: string;
  cheminFichier?: string;
  type?: string;
  moyenDePaiement?: string;
  adresseId?: number;
  etatFactureId?: number;
  societeId?: number;
  operationId?: number;
  clientFournisseurId?: number;
  listeProduits?: IProduit[];
}

export const defaultValue: Readonly<IFacture> = {};
