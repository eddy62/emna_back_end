import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './adresse.reducer';
import { IAdresse } from 'app/shared/model/adresse.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdresseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdresseUpdate = (props: IAdresseUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adresseEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/adresse');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adresseEntity,
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
          <h2 id="emnaBackEndApp.adresse.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.adresse.home.createOrEditLabel">Create or edit a Adresse</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adresseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adresse-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adresse-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="numeroRueLabel" for="adresse-numeroRue">
                  <Translate contentKey="emnaBackEndApp.adresse.numeroRue">Numero Rue</Translate>
                </Label>
                <AvField id="adresse-numeroRue" type="text" name="numeroRue" />
              </AvGroup>
              <AvGroup>
                <Label id="boitePostaleLabel" for="adresse-boitePostale">
                  <Translate contentKey="emnaBackEndApp.adresse.boitePostale">Boite Postale</Translate>
                </Label>
                <AvField id="adresse-boitePostale" type="text" name="boitePostale" />
              </AvGroup>
              <AvGroup>
                <Label id="nomRueLabel" for="adresse-nomRue">
                  <Translate contentKey="emnaBackEndApp.adresse.nomRue">Nom Rue</Translate>
                </Label>
                <AvField
                  id="adresse-nomRue"
                  type="text"
                  name="nomRue"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="codePostalLabel" for="adresse-codePostal">
                  <Translate contentKey="emnaBackEndApp.adresse.codePostal">Code Postal</Translate>
                </Label>
                <AvField
                  id="adresse-codePostal"
                  type="text"
                  name="codePostal"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="villeLabel" for="adresse-ville">
                  <Translate contentKey="emnaBackEndApp.adresse.ville">Ville</Translate>
                </Label>
                <AvField
                  id="adresse-ville"
                  type="text"
                  name="ville"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adresse" replace color="info">
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
  adresseEntity: storeState.adresse.entity,
  loading: storeState.adresse.loading,
  updating: storeState.adresse.updating,
  updateSuccess: storeState.adresse.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdresseUpdate);
