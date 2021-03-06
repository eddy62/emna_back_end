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
import {createEntity, getEntity, reset, updateEntity} from './heures-supplementaires.reducer';

export interface IHeuresSupplementairesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HeuresSupplementairesUpdate = (props: IHeuresSupplementairesUpdateProps) => {
  const [etatVariablePaieId, setEtatVariablePaieId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { heuresSupplementairesEntity, etatVariablePaies, employes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/heures-supplementaires');
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
        ...heuresSupplementairesEntity,
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
          <h2 id="emnaBackEndApp.heuresSupplementaires.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.heuresSupplementaires.home.createOrEditLabel">
              Create or edit a HeuresSupplementaires
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : heuresSupplementairesEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="heures-supplementaires-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="heures-supplementaires-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="heures-supplementaires-date">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.date">Date</Translate>
                </Label>
                <AvField
                  id="heures-supplementaires-date"
                  type="date"
                  className="form-control"
                  name="date"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nombreHeureLabel" for="heures-supplementaires-nombreHeure">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.nombreHeure">Nombre Heure</Translate>
                </Label>
                <AvField
                  id="heures-supplementaires-nombreHeure"
                  type="string"
                  className="form-control"
                  name="nombreHeure"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="moisLabel" for="heures-supplementaires-mois">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.mois">Mois</Translate>
                </Label>
                <AvField
                  id="heures-supplementaires-mois"
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
                <Label id="anneeLabel" for="heures-supplementaires-annee">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.annee">Annee</Translate>
                </Label>
                <AvField
                  id="heures-supplementaires-annee"
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
                <Label for="heures-supplementaires-etatVariablePaie">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.etatVariablePaie">Etat Variable Paie</Translate>
                </Label>
                <AvInput
                  id="heures-supplementaires-etatVariablePaie"
                  type="select"
                  className="form-control"
                  name="etatVariablePaieId"
                  required
                >
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
                <Label for="heures-supplementaires-employe">
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.employe">Employe</Translate>
                </Label>
                <AvInput id="heures-supplementaires-employe" type="select" className="form-control" name="employeId">
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
              <Button tag={Link} id="cancel-save" to="/heures-supplementaires" replace color="info">
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
  heuresSupplementairesEntity: storeState.heuresSupplementaires.entity,
  loading: storeState.heuresSupplementaires.loading,
  updating: storeState.heuresSupplementaires.updating,
  updateSuccess: storeState.heuresSupplementaires.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(HeuresSupplementairesUpdate);
