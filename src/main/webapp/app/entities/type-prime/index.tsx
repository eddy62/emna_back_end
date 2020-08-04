import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypePrime from './type-prime';
import TypePrimeDetail from './type-prime-detail';
import TypePrimeUpdate from './type-prime-update';
import TypePrimeDeleteDialog from './type-prime-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypePrimeDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypePrimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypePrimeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypePrimeDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypePrime} />
    </Switch>
  </>
);

export default Routes;
