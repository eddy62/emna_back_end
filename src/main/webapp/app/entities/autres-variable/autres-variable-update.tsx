import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvFeedback, AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getEtatVariablePaies} from 'app/entities/etat-variable-paie/etat-variable-paie.reducer';
import {getEntities as getEmployes} from 'app/entities/employe/employe.reducer';
import {createEntity, getEntity, reset, updateEntity} from './autres-variable.reducer';

export interface IAutresVariableUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AutresVariableUpdate = (props: IAutresVariableUpdateProps) => {
  const [etatVariablePaieId, setEtatVariablePaieId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { autresVariableEntity, etatVariablePaies, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/autres-variable');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
                <AvField id="autres-variable-montant" type="text" name="montant" />
              </AvGroup>
              <AvGroup>
                <Label id="moisLabel" for="autres-variable-mois">
                  <Translate contentKey="emnaBackEndApp.autresVariable.mois">Mois</Translate>
                </Label>
                <AvField
                  id="autres-variable-mois"
                  type="string"
                  className="form-control"
                  name="mois"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="anneeLabel" for="autres-variable-annee">
                  <Translate contentKey="emnaBackEndApp.autresVariable.annee">Annee</Translate>
                </Label>
                <AvField
                  id="autres-variable-annee"
                  type="string"
                  className="form-control"
                  name="annee"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="autres-variable-etatVariablePaie">
                  <Translate contentKey="emnaBackEndApp.autresVariable.etatVariablePaie">Etat Variable Paie</Translate>
                </Label>
                <AvInput id="autres-variable-etatVariablePaie" type="select" className="form-control" name="etatVariablePaieId" required>
                  {etatVariablePaies
                    ? etatVariablePaies.map(otherEntity => (
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
  etatVariablePaies: storeState.etatVariablePaie.entities,
  employes: storeState.employe.entities,
  autresVariableEntity: storeState.autresVariable.entity,
  loading: storeState.autresVariable.loading,
  updating: storeState.autresVariable.updating,
  updateSuccess: storeState.autresVariable.updateSuccess,
});

const mapDispatchToProps = {
  getEtatVariablePaies,
  getEmployes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AutresVariableUpdate);
