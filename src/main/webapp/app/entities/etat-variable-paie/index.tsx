import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtatVariablePaie from './etat-variable-paie';
import EtatVariablePaieDetail from './etat-variable-paie-detail';
import EtatVariablePaieUpdate from './etat-variable-paie-update';
import EtatVariablePaieDeleteDialog from './etat-variable-paie-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtatVariablePaieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtatVariablePaieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtatVariablePaieDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtatVariablePaie} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtatVariablePaieDeleteDialog} />
  </>
);

export default Routes;
