import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITypeAbsence } from 'app/shared/model/type-absence.model';
import { getEntities as getTypeAbsences } from 'app/entities/type-absence/type-absence.reducer';
import { IEtatVariablePaie } from 'app/shared/model/etat-variable-paie.model';
import { getEntities as getEtatVariablePaies } from 'app/entities/etat-variable-paie/etat-variable-paie.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { getEntities as getEmployes } from 'app/entities/employe/employe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './absence.reducer';
import { IAbsence } from 'app/shared/model/absence.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAbsenceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AbsenceUpdate = (props: IAbsenceUpdateProps) => {
  const [typeAbsenceId, setTypeAbsenceId] = useState('0');
  const [etatVariablePaieId, setEtatVariablePaieId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { absenceEntity, typeAbsences, etatVariablePaies, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/absence');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTypeAbsences();
    props.getEtatVariablePaies();
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
        ...absenceEntity,
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
          <h2 id="emnaBackEndApp.absence.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.absence.home.createOrEditLabel">Create or edit a Absence</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : absenceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="absence-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="absence-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="debutAbsenceLabel" for="absence-debutAbsence">
                  <Translate contentKey="emnaBackEndApp.absence.debutAbsence">Debut Absence</Translate>
                </Label>
                <AvField
                  id="absence-debutAbsence"
                  type="date"
                  className="form-control"
                  name="debutAbsence"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="finAbsenceLabel" for="absence-finAbsence">
                  <Translate contentKey="emnaBackEndApp.absence.finAbsence">Fin Absence</Translate>
                </Label>
                <AvField
                  id="absence-finAbsence"
                  type="date"
                  className="form-control"
                  name="finAbsence"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="justificatifLabel" for="absence-justificatif">
                  <Translate contentKey="emnaBackEndApp.absence.justificatif">Justificatif</Translate>
                </Label>
                <AvField id="absence-justificatif" type="text" name="justificatif" />
              </AvGroup>
              <AvGroup>
                <Label for="absence-typeAbsence">
                  <Translate contentKey="emnaBackEndApp.absence.typeAbsence">Type Absence</Translate>
                </Label>
                <AvInput id="absence-typeAbsence" type="select" className="form-control" name="typeAbsenceId" required>
                  {typeAbsences
                    ? typeAbsences.map(otherEntity => (
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
                <Label for="absence-etatVariablePaie">
                  <Translate contentKey="emnaBackEndApp.absence.etatVariablePaie">Etat Variable Paie</Translate>
                </Label>
                <AvInput id="absence-etatVariablePaie" type="select" className="form-control" name="etatVariablePaieId">
                  <option value="" key="0" />
                  {etatVariablePaies
                    ? etatVariablePaies.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="absence-employe">
                  <Translate contentKey="emnaBackEndApp.absence.employe">Employe</Translate>
                </Label>
                <AvInput id="absence-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/absence" replace color="info">
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
  typeAbsences: storeState.typeAbsence.entities,
  etatVariablePaies: storeState.etatVariablePaie.entities,
  employes: storeState.employe.entities,
  absenceEntity: storeState.absence.entity,
  loading: storeState.absence.loading,
  updating: storeState.absence.updating,
  updateSuccess: storeState.absence.updateSuccess,
});

const mapDispatchToProps = {
  getTypeAbsences,
  getEtatVariablePaies,
  getEmployes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AbsenceUpdate);
