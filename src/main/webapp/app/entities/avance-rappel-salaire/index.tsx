import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AvanceRappelSalaire from './avance-rappel-salaire';
import AvanceRappelSalaireDetail from './avance-rappel-salaire-detail';
import AvanceRappelSalaireUpdate from './avance-rappel-salaire-update';
import AvanceRappelSalaireDeleteDialog from './avance-rappel-salaire-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AvanceRappelSalaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AvanceRappelSalaireUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AvanceRappelSalaireDetail} />
      <ErrorBoundaryRoute path={match.url} component={AvanceRappelSalaire} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AvanceRappelSalaireDeleteDialog} />
  </>
);

export default Routes;
