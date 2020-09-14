import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LigneProduit from './ligne-produit';
import LigneProduitDetail from './ligne-produit-detail';
import LigneProduitUpdate from './ligne-produit-update';
import LigneProduitDeleteDialog from './ligne-produit-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LigneProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LigneProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LigneProduitDetail} />
      <ErrorBoundaryRoute path={match.url} component={LigneProduit} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LigneProduitDeleteDialog} />
  </>
);

export default Routes;
