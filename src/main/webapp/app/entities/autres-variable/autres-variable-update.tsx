import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmploye } from 'app/shared/model/employe.model';
import { getEntities as getEmployes } from 'app/entities/employe/employe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './autres-variable.reducer';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAutresVariableUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AutresVariableUpdate = (props: IAutresVariableUpdateProps) => {
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { autresVariableEntity, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/autres-variable');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEmployes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...autresVariableEntity,
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
          <h2 id="emnaBackEndApp.autresVariable.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.autresVariable.home.createOrEditLabel">Create or edit a AutresVariable</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : autresVariableEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="autres-variable-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="autres-variable-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="descriptionLabel" for="autres-variable-description">
                  <Translate contentKey="emnaBackEndApp.autresVariable.description">Description</Translate>
                </Label>
                <AvField id="autres-variable-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="autres-variable-date">
                  <Translate contentKey="emnaBackEndApp.autresVariable.date">Date</Translate>
                </Label>
                <AvField id="autres-variable-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="montantLabel" for="autres-variable-montant">
                  <Translate contentKey="emnaBackEndApp.autresVariable.montant">Montant</Translate>
                </Label>
                <AvField id="autres-variable-montant" type="string" className="form-control" name="montant" />
              </AvGroup>
              <AvGroup>
                <Label id="justificatifLabel" for="autres-variable-justificatif">
                  <Translate contentKey="emnaBackEndApp.autresVariable.justificatif">Justificatif</Translate>
                </Label>
                <AvField id="autres-variable-justificatif" type="text" name="justificatif" />
              </AvGroup>
              <AvGroup>
                <Label for="autres-variable-employe">
                  <Translate contentKey="emnaBackEndApp.autresVariable.employe">Employe</Translate>
                </Label>
                <AvInput id="autres-variable-employe" type="select" className="form-control" name="employeId">
                  <option value="" key="0" />
                  {employes
                    ? employes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/autres-variable" replace color="info">
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
  employes: storeState.employe.entities,
  autresVariableEntity: storeState.autresVariable.entity,
  loading: storeState.autresVariable.loading,
  updating: storeState.autresVariable.updating,
  updateSuccess: storeState.autresVariable.updateSuccess,
});

const mapDispatchToProps = {
  getEmployes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AutresVariableUpdate);
