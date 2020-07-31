import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import StatutEmploye from './statut-employe';
import StatutEmployeDetail from './statut-employe-detail';
import StatutEmployeUpdate from './statut-employe-update';
import StatutEmployeDeleteDialog from './statut-employe-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={StatutEmployeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StatutEmployeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StatutEmployeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StatutEmployeDetail} />
      <ErrorBoundaryRoute path={match.url} component={StatutEmploye} />
    </Switch>
  </>
);

export default Routes;
