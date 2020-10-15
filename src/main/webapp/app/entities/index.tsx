import React from 'react';
import { Switch } from 'react-router-dom';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Facture from './facture';
import ClientFournisseur from './client-fournisseur';
import Produit from './produit';
import Operation from './operation';
import Releve from './releve';
import EtatReleve from './etat-releve';
import Devis from './devis';
import Contrat from './contrat';
import Avenant from './avenant';
import Clause from './clause';
import Article from './article';
import Adresse from './adresse';
import Employe from './employe';
import TypeAbsence from './type-absence';
import Absence from './absence';
import TypePrime from './type-prime';
import Prime from './prime';
import FichePaie from './fiche-paie';
import HeuresSupplementaires from './heures-supplementaires';
import NoteDeFrais from './note-de-frais';
import AutresVariable from './autres-variable';
import EtatFacture from './etat-facture';
import EtatDevis from './etat-devis';
import Comptable from './comptable';
import Societe from './societe';
import InfoEntreprise from './info-entreprise';
import StatutEmploye from './statut-employe';
import Document from './document';
import LigneProduit from './ligne-produit';
import TypeContrat from './type-contrat';
import EtatVariablePaie from './etat-variable-paie';
import AvanceRappelSalaire from './avance-rappel-salaire';
import Dpae from './dpae';
import Depense from './depense';
import EtatDepense from './etat-depense';
import SaisieArticle from './saisie-article';
import TypeDocument from './type-document';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}facture`} component={Facture} />
      <ErrorBoundaryRoute path={`${match.url}client-fournisseur`} component={ClientFournisseur} />
      <ErrorBoundaryRoute path={`${match.url}produit`} component={Produit} />
      <ErrorBoundaryRoute path={`${match.url}operation`} component={Operation} />
      <ErrorBoundaryRoute path={`${match.url}releve`} component={Releve} />
      <ErrorBoundaryRoute path={`${match.url}etat-releve`} component={EtatReleve} />
      <ErrorBoundaryRoute path={`${match.url}devis`} component={Devis} />
      <ErrorBoundaryRoute path={`${match.url}contrat`} component={Contrat} />
      <ErrorBoundaryRoute path={`${match.url}avenant`} component={Avenant} />
      <ErrorBoundaryRoute path={`${match.url}clause`} component={Clause} />
      <ErrorBoundaryRoute path={`${match.url}article`} component={Article} />
      <ErrorBoundaryRoute path={`${match.url}adresse`} component={Adresse} />
      <ErrorBoundaryRoute path={`${match.url}employe`} component={Employe} />
      <ErrorBoundaryRoute path={`${match.url}type-absence`} component={TypeAbsence} />
      <ErrorBoundaryRoute path={`${match.url}absence`} component={Absence} />
      <ErrorBoundaryRoute path={`${match.url}type-prime`} component={TypePrime} />
      <ErrorBoundaryRoute path={`${match.url}prime`} component={Prime} />
      <ErrorBoundaryRoute path={`${match.url}fiche-paie`} component={FichePaie} />
      <ErrorBoundaryRoute path={`${match.url}heures-supplementaires`} component={HeuresSupplementaires} />
      <ErrorBoundaryRoute path={`${match.url}note-de-frais`} component={NoteDeFrais} />
      <ErrorBoundaryRoute path={`${match.url}autres-variable`} component={AutresVariable} />
      <ErrorBoundaryRoute path={`${match.url}etat-facture`} component={EtatFacture} />
      <ErrorBoundaryRoute path={`${match.url}etat-devis`} component={EtatDevis} />
      <ErrorBoundaryRoute path={`${match.url}comptable`} component={Comptable} />
      <ErrorBoundaryRoute path={`${match.url}societe`} component={Societe} />
      <ErrorBoundaryRoute path={`${match.url}info-entreprise`} component={InfoEntreprise} />
      <ErrorBoundaryRoute path={`${match.url}statut-employe`} component={StatutEmploye} />
      <ErrorBoundaryRoute path={`${match.url}document`} component={Document} />
      <ErrorBoundaryRoute path={`${match.url}ligne-produit`} component={LigneProduit} />
      <ErrorBoundaryRoute path={`${match.url}type-contrat`} component={TypeContrat} />
      <ErrorBoundaryRoute path={`${match.url}etat-variable-paie`} component={EtatVariablePaie} />
      <ErrorBoundaryRoute path={`${match.url}avance-rappel-salaire`} component={AvanceRappelSalaire} />
      <ErrorBoundaryRoute path={`${match.url}dpae`} component={Dpae} />
      <ErrorBoundaryRoute path={`${match.url}depense`} component={Depense} />
      <ErrorBoundaryRoute path={`${match.url}etat-depense`} component={EtatDepense} />
      <ErrorBoundaryRoute path={`${match.url}saisie-article`} component={SaisieArticle} />
      <ErrorBoundaryRoute path={`${match.url}type-document`} component={TypeDocument} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
