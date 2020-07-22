import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IReleve } from 'app/shared/model/releve.model';
import { getEntities as getReleves } from 'app/entities/releve/releve.reducer';
import { getEntity, updateEntity, createEntity, reset } from './operation.reducer';
import { IOperation } from 'app/shared/model/operation.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOperationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OperationUpdate = (props: IOperationUpdateProps) => {
  const [releveId, setReleveId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { operationEntity, releves, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/operation');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getReleves();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...operationEntity,
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
          <h2 id="emnaBackEndApp.operation.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.operation.home.createOrEditLabel">Create or edit a Operation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : operationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="operation-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="operation-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="operation-date">
                  <Translate contentKey="emnaBackEndApp.operation.date">Date</Translate>
                </Label>
                <AvField id="operation-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="operation-description">
                  <Translate contentKey="emnaBackEndApp.operation.description">Description</Translate>
                </Label>
                <AvField id="operation-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="operation-type">
                  <Translate contentKey="emnaBackEndApp.operation.type">Type</Translate>
                </Label>
                <AvField id="operation-type" type="text" name="type" />
              </AvGroup>
              <AvGroup check>
                <Label id="rapprocheLabel">
                  <AvInput id="operation-rapproche" type="checkbox" className="form-check-input" name="rapproche" />
                  <Translate contentKey="emnaBackEndApp.operation.rapproche">Rapproche</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="soldeLabel" for="operation-solde">
                  <Translate contentKey="emnaBackEndApp.operation.solde">Solde</Translate>
                </Label>
                <AvField id="operation-solde" type="string" className="form-control" name="solde" />
              </AvGroup>
              <AvGroup>
                <Label for="operation-releve">
                  <Translate contentKey="emnaBackEndApp.operation.releve">Releve</Translate>
                </Label>
                <AvInput id="operation-releve" type="select" className="form-control" name="releveId">
                  <option value="" key="0" />
                  {releves
                    ? releves.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/operation" replace color="info">
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
  releves: storeState.releve.entities,
  operationEntity: storeState.operation.entity,
  loading: storeState.operation.loading,
  updating: storeState.operation.updating,
  updateSuccess: storeState.operation.updateSuccess,
});

const mapDispatchToProps = {
  getReleves,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OperationUpdate);
