import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Releve from './releve';
import ReleveDetail from './releve-detail';
import ReleveUpdate from './releve-update';
import ReleveDeleteDialog from './releve-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ReleveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ReleveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ReleveDetail} />
      <ErrorBoundaryRoute path={match.url} component={Releve} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ReleveDeleteDialog} />
  </>
);

export default Routes;
