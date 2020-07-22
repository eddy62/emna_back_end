import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './article.reducer';
import { IArticle } from 'app/shared/model/article.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IArticleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ArticleDetail = (props: IArticleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { articleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.article.detail.title">Article</Translate> [<b>{articleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="reference">
              <Translate contentKey="emnaBackEndApp.article.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{articleEntity.reference}</dd>
          <dt>
            <span id="titre">
              <Translate contentKey="emnaBackEndApp.article.titre">Titre</Translate>
            </span>
          </dt>
          <dd>{articleEntity.titre}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.article.description">Description</Translate>
            </span>
          </dt>
          <dd>{articleEntity.description}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.article.listeAvenants">Liste Avenants</Translate>
          </dt>
          <dd>
            {articleEntity.listeAvenants
              ? articleEntity.listeAvenants.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {articleEntity.listeAvenants && i === articleEntity.listeAvenants.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.article.listeContrats">Liste Contrats</Translate>
          </dt>
          <dd>
            {articleEntity.listeContrats
              ? articleEntity.listeContrats.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {articleEntity.listeContrats && i === articleEntity.listeContrats.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.article.societe">Societe</Translate>
          </dt>
          <dd>{articleEntity.societeId ? articleEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/article" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/article/${articleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ article }: IRootState) => ({
  articleEntity: article.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ArticleDetail);
