import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Clause from './clause';
import ClauseDetail from './clause-detail';
import ClauseUpdate from './clause-update';
import ClauseDeleteDialog from './clause-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ClauseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ClauseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ClauseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Clause} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ClauseDeleteDialog} />
  </>
);

export default Routes;
