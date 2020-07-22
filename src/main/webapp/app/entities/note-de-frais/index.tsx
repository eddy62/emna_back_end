import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NoteDeFrais from './note-de-frais';
import NoteDeFraisDetail from './note-de-frais-detail';
import NoteDeFraisUpdate from './note-de-frais-update';
import NoteDeFraisDeleteDialog from './note-de-frais-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NoteDeFraisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NoteDeFraisUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NoteDeFraisDetail} />
      <ErrorBoundaryRoute path={match.url} component={NoteDeFrais} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NoteDeFraisDeleteDialog} />
  </>
);

export default Routes;
