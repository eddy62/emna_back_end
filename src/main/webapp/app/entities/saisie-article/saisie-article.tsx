import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './saisie-article.reducer';

export interface ISaisieArticleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SaisieArticle = (props: ISaisieArticleProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { saisieArticleList, match, loading } = props;
  return (
    <div>
      <h2 id="saisie-article-heading">
        <Translate contentKey="emnaBackEndApp.saisieArticle.home.title">Saisie Articles</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.saisieArticle.home.createLabel">Create new Saisie Article</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {saisieArticleList && saisieArticleList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.saisieArticle.libelle">Libelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.saisieArticle.article">Article</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.saisieArticle.contrat">Contrat</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {saisieArticleList.map((saisieArticle, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${saisieArticle.id}`} color="link" size="sm">
                      {saisieArticle.id}
                    </Button>
                  </td>
                  <td>{saisieArticle.libelle}</td>
                  <td>{saisieArticle.articleId ? <Link to={`article/${saisieArticle.articleId}`}>{saisieArticle.articleId}</Link> : ''}</td>
                  <td>{saisieArticle.contratId ? <Link to={`contrat/${saisieArticle.contratId}`}>{saisieArticle.contratId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${saisieArticle.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${saisieArticle.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${saisieArticle.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="emnaBackEndApp.saisieArticle.home.notFound">No Saisie Articles found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ saisieArticle }: IRootState) => ({
  saisieArticleList: saisieArticle.entities,
  loading: saisieArticle.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SaisieArticle);
