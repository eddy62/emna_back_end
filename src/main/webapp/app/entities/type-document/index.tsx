import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeDocument from './type-document';
import TypeDocumentDetail from './type-document-detail';
import TypeDocumentUpdate from './type-document-update';
import TypeDocumentDeleteDialog from './type-document-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeDocumentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeDocumentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeDocumentDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeDocument} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypeDocumentDeleteDialog} />
  </>
);

export default Routes;
