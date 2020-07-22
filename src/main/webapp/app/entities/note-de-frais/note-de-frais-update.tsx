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
import { getEntity, updateEntity, createEntity, reset } from './note-de-frais.reducer';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INoteDeFraisUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NoteDeFraisUpdate = (props: INoteDeFraisUpdateProps) => {
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { noteDeFraisEntity, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/note-de-frais');
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
        ...noteDeFraisEntity,
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
          <h2 id="emnaBackEndApp.noteDeFrais.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.noteDeFrais.home.createOrEditLabel">Create or edit a NoteDeFrais</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : noteDeFraisEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="note-de-frais-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="note-de-frais-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="designationLabel" for="note-de-frais-designation">
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.designation">Designation</Translate>
                </Label>
                <AvField
                  id="note-de-frais-designation"
                  type="text"
                  name="designation"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="note-de-frais-date">
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.date">Date</Translate>
                </Label>
                <AvField
                  id="note-de-frais-date"
                  type="date"
                  className="form-control"
                  name="date"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="montantLabel" for="note-de-frais-montant">
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.montant">Montant</Translate>
                </Label>
                <AvField
                  id="note-de-frais-montant"
                  type="string"
                  className="form-control"
                  name="montant"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="justificatifLabel" for="note-de-frais-justificatif">
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.justificatif">Justificatif</Translate>
                </Label>
                <AvField id="note-de-frais-justificatif" type="text" name="justificatif" />
              </AvGroup>
              <AvGroup>
                <Label for="note-de-frais-employe">
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.employe">Employe</Translate>
                </Label>
                <AvInput id="note-de-frais-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/note-de-frais" replace color="info">
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
  noteDeFraisEntity: storeState.noteDeFrais.entity,
  loading: storeState.noteDeFrais.loading,
  updating: storeState.noteDeFrais.updating,
  updateSuccess: storeState.noteDeFrais.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(NoteDeFraisUpdate);
