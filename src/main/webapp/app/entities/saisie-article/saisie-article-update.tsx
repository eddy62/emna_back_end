import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IArticle } from 'app/shared/model/article.model';
import { getEntities as getArticles } from 'app/entities/article/article.reducer';
import { IContrat } from 'app/shared/model/contrat.model';
import { getEntities as getContrats } from 'app/entities/contrat/contrat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './saisie-article.reducer';
import { ISaisieArticle } from 'app/shared/model/saisie-article.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISaisieArticleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SaisieArticleUpdate = (props: ISaisieArticleUpdateProps) => {
  const [articleId, setArticleId] = useState('0');
  const [contratId, setContratId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { saisieArticleEntity, articles, contrats, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/saisie-article');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getArticles();
    props.getContrats();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...saisieArticleEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="emnaBackEndApp.saisieArticle.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.saisieArticle.home.createOrEditLabel">Create or edit a SaisieArticle</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : saisieArticleEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="saisie-article-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="saisie-article-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="libelleLabel" for="saisie-article-libelle">
                  <Translate contentKey="emnaBackEndApp.saisieArticle.libelle">Libelle</Translate>
                </Label>
                <AvField
                  id="saisie-article-libelle"
                  type="text"
                  name="libelle"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="saisie-article-article">
                  <Translate contentKey="emnaBackEndApp.saisieArticle.article">Article</Translate>
                </Label>
                <AvInput id="saisie-article-article" type="select" className="form-control" name="articleId" required>
                  {articles
                    ? articles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>
                  <Translate contentKey="entity.validation.required">This field is required.</Translate>
                </AvFeedback>
              </AvGroup>
              <AvGroup>
                <Label for="saisie-article-contrat">
                  <Translate contentKey="emnaBackEndApp.saisieArticle.contrat">Contrat</Translate>
                </Label>
                <AvInput id="saisie-article-contrat" type="select" className="form-control" name="contratId" required>
                  {contrats
                    ? contrats.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>
                  <Translate contentKey="entity.validation.required">This field is required.</Translate>
                </AvFeedback>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/saisie-article" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  articles: storeState.article.entities,
  contrats: storeState.contrat.entities,
  saisieArticleEntity: storeState.saisieArticle.entity,
  loading: storeState.saisieArticle.loading,
  updating: storeState.saisieArticle.updating,
  updateSuccess: storeState.saisieArticle.updateSuccess,
});

const mapDispatchToProps = {
  getArticles,
  getContrats,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SaisieArticleUpdate);
