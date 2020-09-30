import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SaisieArticle from './saisie-article';
import SaisieArticleDetail from './saisie-article-detail';
import SaisieArticleUpdate from './saisie-article-update';
import SaisieArticleDeleteDialog from './saisie-article-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SaisieArticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SaisieArticleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SaisieArticleDetail} />
      <ErrorBoundaryRoute path={match.url} component={SaisieArticle} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SaisieArticleDeleteDialog} />
  </>
);

export default Routes;
