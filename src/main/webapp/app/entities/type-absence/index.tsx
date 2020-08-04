import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeAbsence from './type-absence';
import TypeAbsenceDetail from './type-absence-detail';
import TypeAbsenceUpdate from './type-absence-update';
import TypeAbsenceDeleteDialog from './type-absence-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypeAbsenceDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeAbsenceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeAbsenceUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeAbsenceDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeAbsence} />
    </Switch>
  </>
);

export default Routes;
