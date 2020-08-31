import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdresse } from 'app/shared/model/adresse.model';
import { getEntities as getAdresses } from 'app/entities/adresse/adresse.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './client-fournisseur.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClientFournisseurUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientFournisseurUpdate = (props: IClientFournisseurUpdateProps) => {
  const [adresseId, setAdresseId] = useState('0');
  const [societeId, setSocieteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { clientFournisseurEntity, adresses, societes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/client-fournisseur');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdresses();
    props.getSocietes();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...clientFournisseurEntity,
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
          <h2 id="emnaBackEndApp.clientFournisseur.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.clientFournisseur.home.createOrEditLabel">Create or edit a ClientFournisseur</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : clientFournisseurEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="client-fournisseur-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="client-fournisseur-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="client-fournisseur-nom">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.nom">Nom</Translate>
                </Label>
                <AvField id="client-fournisseur-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="sirenLabel" for="client-fournisseur-siren">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.siren">Siren</Translate>
                </Label>
                <AvField id="client-fournisseur-siren" type="string" className="form-control" name="siren" />
              </AvGroup>
              <AvGroup>
                <Label id="telephoneLabel" for="client-fournisseur-telephone">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.telephone">Telephone</Translate>
                </Label>
                <AvField id="client-fournisseur-telephone" type="text" name="telephone" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="client-fournisseur-email">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.email">Email</Translate>
                </Label>
                <AvField id="client-fournisseur-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label for="client-fournisseur-adresse">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.adresse">Adresse</Translate>
                </Label>
                <AvInput id="client-fournisseur-adresse" type="select" className="form-control" name="adresseId">
                  <option value="" key="0" />
                  {adresses
                    ? adresses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="client-fournisseur-societe">
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.societe">Societe</Translate>
                </Label>
                <AvInput id="client-fournisseur-societe" type="select" className="form-control" name="societeId">
                  <option value="" key="0" />
                  {societes
                    ? societes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/client-fournisseur" replace color="info">
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
  adresses: storeState.adresse.entities,
  societes: storeState.societe.entities,
  clientFournisseurEntity: storeState.clientFournisseur.entity,
  loading: storeState.clientFournisseur.loading,
  updating: storeState.clientFournisseur.updating,
  updateSuccess: storeState.clientFournisseur.updateSuccess,
});

const mapDispatchToProps = {
  getAdresses,
  getSocietes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientFournisseurUpdate);
