import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Societe from './societe';
import SocieteDetail from './societe-detail';
import SocieteUpdate from './societe-update';
import SocieteDeleteDialog from './societe-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SocieteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SocieteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SocieteDetail} />
      <ErrorBoundaryRoute path={match.url} component={Societe} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SocieteDeleteDialog} />
  </>
);

export default Routes;
