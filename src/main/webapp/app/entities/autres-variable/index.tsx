import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AutresVariable from './autres-variable';
import AutresVariableDetail from './autres-variable-detail';
import AutresVariableUpdate from './autres-variable-update';
import AutresVariableDeleteDialog from './autres-variable-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AutresVariableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AutresVariableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AutresVariableDetail} />
      <ErrorBoundaryRoute path={match.url} component={AutresVariable} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AutresVariableDeleteDialog} />
  </>
);

export default Routes;
