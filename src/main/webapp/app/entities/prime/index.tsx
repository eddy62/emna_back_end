import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Prime from './prime';
import PrimeDetail from './prime-detail';
import PrimeUpdate from './prime-update';
import PrimeDeleteDialog from './prime-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PrimeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PrimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PrimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PrimeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Prime} />
    </Switch>
  </>
);

export default Routes;
