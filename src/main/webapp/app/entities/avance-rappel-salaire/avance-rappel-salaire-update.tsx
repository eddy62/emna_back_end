import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtatVariablePaie } from 'app/shared/model/etat-variable-paie.model';
import { getEntities as getEtatVariablePaies } from 'app/entities/etat-variable-paie/etat-variable-paie.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { getEntities as getEmployes } from 'app/entities/employe/employe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './avance-rappel-salaire.reducer';
import { IAvanceRappelSalaire } from 'app/shared/model/avance-rappel-salaire.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvanceRappelSalaireUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvanceRappelSalaireUpdate = (props: IAvanceRappelSalaireUpdateProps) => {
  const [etatVariablePaieId, setEtatVariablePaieId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { avanceRappelSalaireEntity, etatVariablePaies, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/avance-rappel-salaire');
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
        ...avanceRappelSalaireEntity,
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
          <h2 id="emnaBackEndApp.avanceRappelSalaire.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.home.createOrEditLabel">
              Create or edit a AvanceRappelSalaire
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : avanceRappelSalaireEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="avance-rappel-salaire-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="avance-rappel-salaire-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="typeLabel" for="avance-rappel-salaire-type">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.type">Type</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-type"
                  type="text"
                  name="type"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="debutPeriodeLabel" for="avance-rappel-salaire-debutPeriode">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.debutPeriode">Debut Periode</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-debutPeriode"
                  type="date"
                  className="form-control"
                  name="debutPeriode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="finPeriodeLabel" for="avance-rappel-salaire-finPeriode">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.finPeriode">Fin Periode</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-finPeriode"
                  type="date"
                  className="form-control"
                  name="finPeriode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="montantLabel" for="avance-rappel-salaire-montant">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.montant">Montant</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-montant"
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
                <Label id="moisLabel" for="avance-rappel-salaire-mois">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.mois">Mois</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-mois"
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
                <Label id="anneeLabel" for="avance-rappel-salaire-annee">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.annee">Annee</Translate>
                </Label>
                <AvField
                  id="avance-rappel-salaire-annee"
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
                <Label for="avance-rappel-salaire-etatVariablePaie">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.etatVariablePaie">Etat Variable Paie</Translate>
                </Label>
                <AvInput id="avance-rappel-salaire-etatVariablePaie" type="select" className="form-control" name="etatVariablePaieId">
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
                <Label for="avance-rappel-salaire-employe">
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.employe">Employe</Translate>
                </Label>
                <AvInput id="avance-rappel-salaire-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/avance-rappel-salaire" replace color="info">
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
  avanceRappelSalaireEntity: storeState.avanceRappelSalaire.entity,
  loading: storeState.avanceRappelSalaire.loading,
  updating: storeState.avanceRappelSalaire.updating,
  updateSuccess: storeState.avanceRappelSalaire.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(AvanceRappelSalaireUpdate);
