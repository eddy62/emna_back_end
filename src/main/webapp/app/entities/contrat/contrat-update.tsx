import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvFeedback, AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getTypeContrats} from 'app/entities/type-contrat/type-contrat.reducer';
import {getEntities as getEmployes} from 'app/entities/employe/employe.reducer';
import {createEntity, getEntity, reset, updateEntity} from './contrat.reducer';

export interface IContratUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContratUpdate = (props: IContratUpdateProps) => {
  const [typeContratId, setTypeContratId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { contratEntity, typeContrats, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/contrat');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTypeContrats();
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
        ...contratEntity,
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
          <h2 id="emnaBackEndApp.contrat.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.contrat.home.createOrEditLabel">Create or edit a Contrat</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : contratEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="contrat-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="contrat-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="titreLabel" for="contrat-titre">
                  <Translate contentKey="emnaBackEndApp.contrat.titre">Titre</Translate>
                </Label>
                <AvField
                  id="contrat-titre"
                  type="text"
                  name="titre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreationLabel" for="contrat-dateCreation">
                  <Translate contentKey="emnaBackEndApp.contrat.dateCreation">Date Creation</Translate>
                </Label>
                <AvField
                  id="contrat-dateCreation"
                  type="date"
                  className="form-control"
                  name="dateCreation"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="signeLabel">
                  <AvInput id="contrat-signe" type="checkbox" className="form-check-input" name="signe" />
                  <Translate contentKey="emnaBackEndApp.contrat.signe">Signe</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="archiveLabel">
                  <AvInput id="contrat-archive" type="checkbox" className="form-check-input" name="archive" />
                  <Translate contentKey="emnaBackEndApp.contrat.archive">Archive</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="contrat-typeContrat">
                  <Translate contentKey="emnaBackEndApp.contrat.typeContrat">Type Contrat</Translate>
                </Label>
                <AvInput id="contrat-typeContrat" type="select" className="form-control" name="typeContratId" required>
                  {typeContrats
                    ? typeContrats.map(otherEntity => (
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
                <Label for="contrat-employe">
                  <Translate contentKey="emnaBackEndApp.contrat.employe">Employe</Translate>
                </Label>
                <AvInput id="contrat-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/contrat" replace color="info">
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
  typeContrats: storeState.typeContrat.entities,
  employes: storeState.employe.entities,
  contratEntity: storeState.contrat.entity,
  loading: storeState.contrat.loading,
  updating: storeState.contrat.updating,
  updateSuccess: storeState.contrat.updateSuccess,
});

const mapDispatchToProps = {
  getTypeContrats,
  getEmployes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContratUpdate);
