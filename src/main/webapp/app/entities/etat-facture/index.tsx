import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtatFacture from './etat-facture';
import EtatFactureDetail from './etat-facture-detail';
import EtatFactureUpdate from './etat-facture-update';
import EtatFactureDeleteDialog from './etat-facture-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtatFactureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtatFactureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtatFactureDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtatFacture} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtatFactureDeleteDialog} />
  </>
);

export default Routes;
