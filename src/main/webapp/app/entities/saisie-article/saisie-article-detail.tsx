import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './saisie-article.reducer';
import { ISaisieArticle } from 'app/shared/model/saisie-article.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISaisieArticleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SaisieArticleDetail = (props: ISaisieArticleDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { saisieArticleEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.saisieArticle.detail.title">SaisieArticle</Translate> [<b>{saisieArticleEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="libelle">
              <Translate contentKey="emnaBackEndApp.saisieArticle.libelle">Libelle</Translate>
            </span>
          </dt>
          <dd>{saisieArticleEntity.libelle}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.saisieArticle.article">Article</Translate>
          </dt>
          <dd>{saisieArticleEntity.articleId ? saisieArticleEntity.articleId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.saisieArticle.contrat">Contrat</Translate>
          </dt>
          <dd>{saisieArticleEntity.contratId ? saisieArticleEntity.contratId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.saisieArticle.avenant">Avenant</Translate>
          </dt>
          <dd>{saisieArticleEntity.avenantId ? saisieArticleEntity.avenantId : ''}</dd>
        </dl>
        <Button tag={Link} to="/saisie-article" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/saisie-article/${saisieArticleEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ saisieArticle }: IRootState) => ({
  saisieArticleEntity: saisieArticle.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SaisieArticleDetail);
