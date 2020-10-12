import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Avenant from './avenant';
import AvenantDetail from './avenant-detail';
import AvenantUpdate from './avenant-update';
import AvenantDeleteDialog from './avenant-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AvenantUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AvenantUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvenantDetail} />
      <ErrorBoundaryRoute path={match.url} component={Avenant} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AvenantDeleteDialog} />
  </>
);

export default Routes;
