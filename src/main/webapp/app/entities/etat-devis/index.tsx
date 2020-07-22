import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtatDevis from './etat-devis';
import EtatDevisDetail from './etat-devis-detail';
import EtatDevisUpdate from './etat-devis-update';
import EtatDevisDeleteDialog from './etat-devis-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtatDevisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtatDevisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtatDevisDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtatDevis} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtatDevisDeleteDialog} />
  </>
);

export default Routes;
