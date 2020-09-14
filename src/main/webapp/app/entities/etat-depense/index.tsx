import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtatDepense from './etat-depense';
import EtatDepenseDetail from './etat-depense-detail';
import EtatDepenseUpdate from './etat-depense-update';
import EtatDepenseDeleteDialog from './etat-depense-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtatDepenseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtatDepenseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtatDepenseDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtatDepense} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtatDepenseDeleteDialog} />
  </>
);

export default Routes;
