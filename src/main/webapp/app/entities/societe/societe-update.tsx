import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInfoEntreprise } from 'app/shared/model/info-entreprise.model';
import { getEntities as getInfoEntreprises } from 'app/entities/info-entreprise/info-entreprise.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IAdresse } from 'app/shared/model/adresse.model';
import { getEntities as getAdresses } from 'app/entities/adresse/adresse.reducer';
import { IComptable } from 'app/shared/model/comptable.model';
import { getEntities as getComptables } from 'app/entities/comptable/comptable.reducer';
import { getEntity, updateEntity, createEntity, reset } from './societe.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISocieteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SocieteUpdate = (props: ISocieteUpdateProps) => {
  const [infoEntrepriseId, setInfoEntrepriseId] = useState('0');
  const [userId, setUserId] = useState('0');
  const [adresseId, setAdresseId] = useState('0');
  const [comptableId, setComptableId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { societeEntity, infoEntreprises, users, adresses, comptables, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/societe');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getInfoEntreprises();
    props.getUsers();
    props.getAdresses();
    props.getComptables();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...societeEntity,
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
          <h2 id="emnaBackEndApp.societe.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.societe.home.createOrEditLabel">Create or edit a Societe</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : societeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="societe-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="societe-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="civiliteLabel" for="societe-civilite">
                  <Translate contentKey="emnaBackEndApp.societe.civilite">Civilite</Translate>
                </Label>
                <AvField id="societe-civilite" type="text" name="civilite" />
              </AvGroup>
              <AvGroup>
                <Label for="societe-infoEntreprise">
                  <Translate contentKey="emnaBackEndApp.societe.infoEntreprise">Info Entreprise</Translate>
                </Label>
                <AvInput id="societe-infoEntreprise" type="select" className="form-control" name="infoEntrepriseId">
                  <option value="" key="0" />
                  {infoEntreprises
                    ? infoEntreprises.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="societe-user">
                  <Translate contentKey="emnaBackEndApp.societe.user">User</Translate>
                </Label>
                <AvInput id="societe-user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="societe-adresse">
                  <Translate contentKey="emnaBackEndApp.societe.adresse">Adresse</Translate>
                </Label>
                <AvInput id="societe-adresse" type="select" className="form-control" name="adresseId">
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
                <Label for="societe-comptable">
                  <Translate contentKey="emnaBackEndApp.societe.comptable">Comptable</Translate>
                </Label>
                <AvInput id="societe-comptable" type="select" className="form-control" name="comptableId">
                  <option value="" key="0" />
                  {comptables
                    ? comptables.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/societe" replace color="info">
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
  infoEntreprises: storeState.infoEntreprise.entities,
  users: storeState.userManagement.users,
  adresses: storeState.adresse.entities,
  comptables: storeState.comptable.entities,
  societeEntity: storeState.societe.entity,
  loading: storeState.societe.loading,
  updating: storeState.societe.updating,
  updateSuccess: storeState.societe.updateSuccess,
});

const mapDispatchToProps = {
  getInfoEntreprises,
  getUsers,
  getAdresses,
  getComptables,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SocieteUpdate);
