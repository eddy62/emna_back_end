import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Comptable from './comptable';
import ComptableDetail from './comptable-detail';
import ComptableUpdate from './comptable-update';
import ComptableDeleteDialog from './comptable-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ComptableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ComptableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ComptableDetail} />
      <ErrorBoundaryRoute path={match.url} component={Comptable} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ComptableDeleteDialog} />
  </>
);

export default Routes;
