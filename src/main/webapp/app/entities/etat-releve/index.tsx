import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtatReleve from './etat-releve';
import EtatReleveDetail from './etat-releve-detail';
import EtatReleveUpdate from './etat-releve-update';
import EtatReleveDeleteDialog from './etat-releve-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtatReleveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtatReleveUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtatReleveDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtatReleve} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EtatReleveDeleteDialog} />
  </>
);

export default Routes;
