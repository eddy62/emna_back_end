import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import facture, {
  FactureState
} from 'app/entities/facture/facture.reducer';
// prettier-ignore
import clientFournisseur, {
  ClientFournisseurState
} from 'app/entities/client-fournisseur/client-fournisseur.reducer';
// prettier-ignore
import produit, {
  ProduitState
} from 'app/entities/produit/produit.reducer';
// prettier-ignore
import operation, {
  OperationState
} from 'app/entities/operation/operation.reducer';
// prettier-ignore
import releve, {
  ReleveState
} from 'app/entities/releve/releve.reducer';
// prettier-ignore
import etatReleve, {
  EtatReleveState
} from 'app/entities/etat-releve/etat-releve.reducer';
// prettier-ignore
import devis, {
  DevisState
} from 'app/entities/devis/devis.reducer';
// prettier-ignore
import contrat, {
  ContratState
} from 'app/entities/contrat/contrat.reducer';
// prettier-ignore
import avenant, {
  AvenantState
} from 'app/entities/avenant/avenant.reducer';
// prettier-ignore
import clause, {
  ClauseState
} from 'app/entities/clause/clause.reducer';
// prettier-ignore
import article, {
  ArticleState
} from 'app/entities/article/article.reducer';
// prettier-ignore
import adresse, {
  AdresseState
} from 'app/entities/adresse/adresse.reducer';
// prettier-ignore
import employe, {
  EmployeState
} from 'app/entities/employe/employe.reducer';
// prettier-ignore
import typeAbsence, {
  TypeAbsenceState
} from 'app/entities/type-absence/type-absence.reducer';
// prettier-ignore
import absence, {
  AbsenceState
} from 'app/entities/absence/absence.reducer';
// prettier-ignore
import typePrime, {
  TypePrimeState
} from 'app/entities/type-prime/type-prime.reducer';
// prettier-ignore
import prime, {
  PrimeState
} from 'app/entities/prime/prime.reducer';
// prettier-ignore
import fichePaie, {
  FichePaieState
} from 'app/entities/fiche-paie/fiche-paie.reducer';
// prettier-ignore
import heuresSupplementaires, {
  HeuresSupplementairesState
} from 'app/entities/heures-supplementaires/heures-supplementaires.reducer';
// prettier-ignore
import noteDeFrais, {
  NoteDeFraisState
} from 'app/entities/note-de-frais/note-de-frais.reducer';
// prettier-ignore
import autresVariable, {
  AutresVariableState
} from 'app/entities/autres-variable/autres-variable.reducer';
// prettier-ignore
import etatFacture, {
  EtatFactureState
} from 'app/entities/etat-facture/etat-facture.reducer';
// prettier-ignore
import etatDevis, {
  EtatDevisState
} from 'app/entities/etat-devis/etat-devis.reducer';
// prettier-ignore
import comptable, {
  ComptableState
} from 'app/entities/comptable/comptable.reducer';
// prettier-ignore
import societe, {
  SocieteState
} from 'app/entities/societe/societe.reducer';
// prettier-ignore
import infoEntreprise, {
  InfoEntrepriseState
} from 'app/entities/info-entreprise/info-entreprise.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly facture: FactureState;
  readonly clientFournisseur: ClientFournisseurState;
  readonly produit: ProduitState;
  readonly operation: OperationState;
  readonly releve: ReleveState;
  readonly etatReleve: EtatReleveState;
  readonly devis: DevisState;
  readonly contrat: ContratState;
  readonly avenant: AvenantState;
  readonly clause: ClauseState;
  readonly article: ArticleState;
  readonly adresse: AdresseState;
  readonly employe: EmployeState;
  readonly typeAbsence: TypeAbsenceState;
  readonly absence: AbsenceState;
  readonly typePrime: TypePrimeState;
  readonly prime: PrimeState;
  readonly fichePaie: FichePaieState;
  readonly heuresSupplementaires: HeuresSupplementairesState;
  readonly noteDeFrais: NoteDeFraisState;
  readonly autresVariable: AutresVariableState;
  readonly etatFacture: EtatFactureState;
  readonly etatDevis: EtatDevisState;
  readonly comptable: ComptableState;
  readonly societe: SocieteState;
  readonly infoEntreprise: InfoEntrepriseState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  facture,
  clientFournisseur,
  produit,
  operation,
  releve,
  etatReleve,
  devis,
  contrat,
  avenant,
  clause,
  article,
  adresse,
  employe,
  typeAbsence,
  absence,
  typePrime,
  prime,
  fichePaie,
  heuresSupplementaires,
  noteDeFrais,
  autresVariable,
  etatFacture,
  etatDevis,
  comptable,
  societe,
  infoEntreprise,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
