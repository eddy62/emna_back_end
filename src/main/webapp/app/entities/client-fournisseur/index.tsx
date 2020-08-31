import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ClientFournisseur from './client-fournisseur';
import ClientFournisseurDetail from './client-fournisseur-detail';
import ClientFournisseurUpdate from './client-fournisseur-update';
import ClientFournisseurDeleteDialog from './client-fournisseur-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ClientFournisseurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ClientFournisseurUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ClientFournisseurDetail} />
      <ErrorBoundaryRoute path={match.url} component={ClientFournisseur} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ClientFournisseurDeleteDialog} />
  </>
);

export default Routes;
