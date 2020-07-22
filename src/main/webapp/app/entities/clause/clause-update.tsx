import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IContrat } from 'app/shared/model/contrat.model';
import { getEntities as getContrats } from 'app/entities/contrat/contrat.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { getEntities as getAvenants } from 'app/entities/avenant/avenant.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { IArticle } from 'app/shared/model/article.model';
import { getEntities as getArticles } from 'app/entities/article/article.reducer';
import { getEntity, updateEntity, createEntity, reset } from './clause.reducer';
import { IClause } from 'app/shared/model/clause.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClauseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClauseUpdate = (props: IClauseUpdateProps) => {
  const [idslisteContrats, setIdslisteContrats] = useState([]);
  const [idslisteAvenants, setIdslisteAvenants] = useState([]);
  const [societeId, setSocieteId] = useState('0');
  const [articleId, setArticleId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { clauseEntity, contrats, avenants, societes, articles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/clause');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getContrats();
    props.getAvenants();
    props.getSocietes();
    props.getArticles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...clauseEntity,
        ...values,
        listeContrats: mapIdList(values.listeContrats),
        listeAvenants: mapIdList(values.listeAvenants),
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
          <h2 id="emnaBackEndApp.clause.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.clause.home.createOrEditLabel">Create or edit a Clause</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : clauseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="clause-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="clause-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="referenceLabel" for="clause-reference">
                  <Translate contentKey="emnaBackEndApp.clause.reference">Reference</Translate>
                </Label>
                <AvField
                  id="clause-reference"
                  type="text"
                  name="reference"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="clause-description">
                  <Translate contentKey="emnaBackEndApp.clause.description">Description</Translate>
                </Label>
                <AvField
                  id="clause-description"
                  type="text"
                  name="description"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="clause-listeContrats">
                  <Translate contentKey="emnaBackEndApp.clause.listeContrats">Liste Contrats</Translate>
                </Label>
                <AvInput
                  id="clause-listeContrats"
                  type="select"
                  multiple
                  className="form-control"
                  name="listeContrats"
                  value={clauseEntity.listeContrats && clauseEntity.listeContrats.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {contrats
                    ? contrats.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="clause-listeAvenants">
                  <Translate contentKey="emnaBackEndApp.clause.listeAvenants">Liste Avenants</Translate>
                </Label>
                <AvInput
                  id="clause-listeAvenants"
                  type="select"
                  multiple
                  className="form-control"
                  name="listeAvenants"
                  value={clauseEntity.listeAvenants && clauseEntity.listeAvenants.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {avenants
                    ? avenants.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="clause-societe">
                  <Translate contentKey="emnaBackEndApp.clause.societe">Societe</Translate>
                </Label>
                <AvInput id="clause-societe" type="select" className="form-control" name="societeId">
                  <option value="" key="0" />
                  {societes
                    ? societes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="clause-article">
                  <Translate contentKey="emnaBackEndApp.clause.article">Article</Translate>
                </Label>
                <AvInput id="clause-article" type="select" className="form-control" name="articleId">
                  <option value="" key="0" />
                  {articles
                    ? articles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/clause" replace color="info">
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
  contrats: storeState.contrat.entities,
  avenants: storeState.avenant.entities,
  societes: storeState.societe.entities,
  articles: storeState.article.entities,
  clauseEntity: storeState.clause.entity,
  loading: storeState.clause.loading,
  updating: storeState.clause.updating,
  updateSuccess: storeState.clause.updateSuccess,
});

const mapDispatchToProps = {
  getContrats,
  getAvenants,
  getSocietes,
  getArticles,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClauseUpdate);
