import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FichePaie from './fiche-paie';
import FichePaieDetail from './fiche-paie-detail';
import FichePaieUpdate from './fiche-paie-update';
import FichePaieDeleteDialog from './fiche-paie-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FichePaieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FichePaieUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FichePaieDetail} />
      <ErrorBoundaryRoute path={match.url} component={FichePaie} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FichePaieDeleteDialog} />
  </>
);

export default Routes;
