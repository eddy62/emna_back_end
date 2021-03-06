import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvFeedback, AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getTypePrimes} from 'app/entities/type-prime/type-prime.reducer';
import {getEntities as getEtatVariablePaies} from 'app/entities/etat-variable-paie/etat-variable-paie.reducer';
import {getEntities as getEmployes} from 'app/entities/employe/employe.reducer';
import {createEntity, getEntity, reset, updateEntity} from './prime.reducer';

export interface IPrimeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PrimeUpdate = (props: IPrimeUpdateProps) => {
  const [typePrimeId, setTypePrimeId] = useState('0');
  const [etatVariablePaieId, setEtatVariablePaieId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { primeEntity, typePrimes, etatVariablePaies, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/prime');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTypePrimes();
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
        ...primeEntity,
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
          <h2 id="emnaBackEndApp.prime.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.prime.home.createOrEditLabel">Create or edit a Prime</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : primeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="prime-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="prime-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="montantLabel" for="prime-montant">
                  <Translate contentKey="emnaBackEndApp.prime.montant">Montant</Translate>
                </Label>
                <AvField
                  id="prime-montant"
                  type="text"
                  name="montant"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="moisLabel" for="prime-mois">
                  <Translate contentKey="emnaBackEndApp.prime.mois">Mois</Translate>
                </Label>
                <AvField
                  id="prime-mois"
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
                <Label id="anneeLabel" for="prime-annee">
                  <Translate contentKey="emnaBackEndApp.prime.annee">Annee</Translate>
                </Label>
                <AvField
                  id="prime-annee"
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
                <Label for="prime-typePrime">
                  <Translate contentKey="emnaBackEndApp.prime.typePrime">Type Prime</Translate>
                </Label>
                <AvInput id="prime-typePrime" type="select" className="form-control" name="typePrimeId" required>
                  {typePrimes
                    ? typePrimes.map(otherEntity => (
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
                <Label for="prime-etatVariablePaie">
                  <Translate contentKey="emnaBackEndApp.prime.etatVariablePaie">Etat Variable Paie</Translate>
                </Label>
                <AvInput id="prime-etatVariablePaie" type="select" className="form-control" name="etatVariablePaieId" required>
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
                <Label for="prime-employe">
                  <Translate contentKey="emnaBackEndApp.prime.employe">Employe</Translate>
                </Label>
                <AvInput id="prime-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/prime" replace color="info">
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
  typePrimes: storeState.typePrime.entities,
  etatVariablePaies: storeState.etatVariablePaie.entities,
  employes: storeState.employe.entities,
  primeEntity: storeState.prime.entity,
  loading: storeState.prime.loading,
  updating: storeState.prime.updating,
  updateSuccess: storeState.prime.updateSuccess,
});

const mapDispatchToProps = {
  getTypePrimes,
  getEtatVariablePaies,
  getEmployes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PrimeUpdate);
