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
import { IArticle } from 'app/shared/model/article.model';
import { getEntities as getArticles } from 'app/entities/article/article.reducer';
import { IClause } from 'app/shared/model/clause.model';
import { getEntities as getClauses } from 'app/entities/clause/clause.reducer';
import { getEntity, updateEntity, createEntity, reset } from './avenant.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvenantUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvenantUpdate = (props: IAvenantUpdateProps) => {
  const [contratId, setContratId] = useState('0');
  const [listeArticlesId, setListeArticlesId] = useState('0');
  const [listeClausesId, setListeClausesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { avenantEntity, contrats, articles, clauses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/avenant');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getContrats();
    props.getArticles();
    props.getClauses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...avenantEntity,
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
          <h2 id="emnaBackEndApp.avenant.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.avenant.home.createOrEditLabel">Create or edit a Avenant</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : avenantEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="avenant-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="avenant-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="referenceLabel" for="avenant-reference">
                  <Translate contentKey="emnaBackEndApp.avenant.reference">Reference</Translate>
                </Label>
                <AvField
                  id="avenant-reference"
                  type="text"
                  name="reference"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="signeLabel">
                  <AvInput id="avenant-signe" type="checkbox" className="form-check-input" name="signe" />
                  <Translate contentKey="emnaBackEndApp.avenant.signe">Signe</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="avenant-contrat">
                  <Translate contentKey="emnaBackEndApp.avenant.contrat">Contrat</Translate>
                </Label>
                <AvInput id="avenant-contrat" type="select" className="form-control" name="contratId">
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
              <Button tag={Link} id="cancel-save" to="/avenant" replace color="info">
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
  articles: storeState.article.entities,
  clauses: storeState.clause.entities,
  avenantEntity: storeState.avenant.entity,
  loading: storeState.avenant.loading,
  updating: storeState.avenant.updating,
  updateSuccess: storeState.avenant.updateSuccess,
});

const mapDispatchToProps = {
  getContrats,
  getArticles,
  getClauses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvenantUpdate);
