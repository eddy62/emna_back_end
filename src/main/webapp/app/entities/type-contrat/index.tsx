import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeContrat from './type-contrat';
import TypeContratDetail from './type-contrat-detail';
import TypeContratUpdate from './type-contrat-update';
import TypeContratDeleteDialog from './type-contrat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeContratUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeContratUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeContratDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeContrat} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypeContratDeleteDialog} />
  </>
);

export default Routes;
