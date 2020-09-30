import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HeuresSupplementaires from './heures-supplementaires';
import HeuresSupplementairesDetail from './heures-supplementaires-detail';
import HeuresSupplementairesUpdate from './heures-supplementaires-update';
import HeuresSupplementairesDeleteDialog from './heures-supplementaires-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HeuresSupplementairesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HeuresSupplementairesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HeuresSupplementairesDetail} />
      <ErrorBoundaryRoute path={match.url} component={HeuresSupplementaires} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={HeuresSupplementairesDeleteDialog} />
  </>
);

export default Routes;
