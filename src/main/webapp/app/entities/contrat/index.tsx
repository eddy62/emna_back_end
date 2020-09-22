import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Contrat from './contrat';
import ContratDetail from './contrat-detail';
import ContratUpdate from './contrat-update';
import ContratDeleteDialog from './contrat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContratUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContratUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContratDetail} />
      <ErrorBoundaryRoute path={match.url} component={Contrat} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContratDeleteDialog} />
  </>
);

export default Routes;
