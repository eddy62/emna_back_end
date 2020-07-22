import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import InfoEntreprise from './info-entreprise';
import InfoEntrepriseDetail from './info-entreprise-detail';
import InfoEntrepriseUpdate from './info-entreprise-update';
import InfoEntrepriseDeleteDialog from './info-entreprise-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InfoEntrepriseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InfoEntrepriseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InfoEntrepriseDetail} />
      <ErrorBoundaryRoute path={match.url} component={InfoEntreprise} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={InfoEntrepriseDeleteDialog} />
  </>
);

export default Routes;
