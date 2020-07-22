import { Moment } from 'moment';
import { IContrat } from 'app/shared/model/contrat.model';
import { IAbsence } from 'app/shared/model/absence.model';
import { IPrime } from 'app/shared/model/prime.model';
import { IFichePaie } from 'app/shared/model/fiche-paie.model';
import { IHeuresSupplementaires } from 'app/shared/model/heures-supplementaires.model';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';

export interface IEmploye {
  id?: number;
  matricule?: string;
  civilite?: string;
  nomNaissance?: string;
  nomUsage?: string;
  prenom?: string;
  dateNaissance?: string;
  villeNaissance?: string;
  departementNaissance?: string;
  paysNaisance?: string;
  numeroSecuriteSociale?: string;
  email?: string;
  telephoneFix?: string;
  telephonePortable?: string;
  fax?: string;
  salaireHoraire?: number;
  salaireBrutMensuelle?: number;
  heuresMensuelle?: number;
  categorie?: string;
  statut?: string;
  adresseId?: number;
  listeContrats?: IContrat[];
  listeAbsences?: IAbsence[];
  listePrimes?: IPrime[];
  listeFichePaies?: IFichePaie[];
  listeHeureSupplementaires?: IHeuresSupplementaires[];
  listeNoteDeFrais?: INoteDeFrais[];
  listeAutresVariables?: IAutresVariable[];
  societeId?: number;
}

export const defaultValue: Readonly<IEmploye> = {};
