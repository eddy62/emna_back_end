import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getEmployes} from 'app/entities/employe/employe.reducer';
import {createEntity, getEntity, reset, updateEntity} from './fiche-paie.reducer';

export interface IFichePaieUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FichePaieUpdate = (props: IFichePaieUpdateProps) => {
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { fichePaieEntity, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/fiche-paie');
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
        ...fichePaieEntity,
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
          <h2 id="emnaBackEndApp.fichePaie.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.fichePaie.home.createOrEditLabel">Create or edit a FichePaie</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fichePaieEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="fiche-paie-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="fiche-paie-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="debutPeriodeLabel" for="fiche-paie-debutPeriode">
                  <Translate contentKey="emnaBackEndApp.fichePaie.debutPeriode">Debut Periode</Translate>
                </Label>
                <AvField
                  id="fiche-paie-debutPeriode"
                  type="date"
                  className="form-control"
                  name="debutPeriode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="finPeriodeLabel" for="fiche-paie-finPeriode">
                  <Translate contentKey="emnaBackEndApp.fichePaie.finPeriode">Fin Periode</Translate>
                </Label>
                <AvField
                  id="fiche-paie-finPeriode"
                  type="date"
                  className="form-control"
                  name="finPeriode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="moisLabel" for="fiche-paie-mois">
                  <Translate contentKey="emnaBackEndApp.fichePaie.mois">Mois</Translate>
                </Label>
                <AvField
                  id="fiche-paie-mois"
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
                <Label id="anneeLabel" for="fiche-paie-annee">
                  <Translate contentKey="emnaBackEndApp.fichePaie.annee">Annee</Translate>
                </Label>
                <AvField
                  id="fiche-paie-annee"
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
                <Label for="fiche-paie-employe">
                  <Translate contentKey="emnaBackEndApp.fichePaie.employe">Employe</Translate>
                </Label>
                <AvInput id="fiche-paie-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/fiche-paie" replace color="info">
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
  fichePaieEntity: storeState.fichePaie.entity,
  loading: storeState.fichePaie.loading,
  updating: storeState.fichePaie.updating,
  updateSuccess: storeState.fichePaie.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(FichePaieUpdate);
