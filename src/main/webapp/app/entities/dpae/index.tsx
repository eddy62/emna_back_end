import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Dpae from './dpae';
import DpaeDetail from './dpae-detail';
import DpaeUpdate from './dpae-update';
import DpaeDeleteDialog from './dpae-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DpaeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DpaeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DpaeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Dpae} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DpaeDeleteDialog} />
  </>
);

export default Routes;
