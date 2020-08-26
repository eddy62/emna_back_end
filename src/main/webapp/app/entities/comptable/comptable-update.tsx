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
import { getEntity, updateEntity, createEntity, reset } from './comptable.reducer';
import { IComptable } from 'app/shared/model/comptable.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IComptableUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ComptableUpdate = (props: IComptableUpdateProps) => {
  const [infoEntrepriseId, setInfoEntrepriseId] = useState('0');
  const [userId, setUserId] = useState('0');
  const [adresseId, setAdresseId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { comptableEntity, infoEntreprises, users, adresses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/comptable');
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
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...comptableEntity,
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
          <h2 id="emnaBackEndApp.comptable.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.comptable.home.createOrEditLabel">Create or edit a Comptable</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : comptableEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="comptable-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="comptable-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="civiliteLabel" for="comptable-civilite">
                  <Translate contentKey="emnaBackEndApp.comptable.civilite">Civilite</Translate>
                </Label>
                <AvField id="comptable-civilite" type="text" name="civilite" />
              </AvGroup>
              <AvGroup>
                <Label for="comptable-infoEntreprise">
                  <Translate contentKey="emnaBackEndApp.comptable.infoEntreprise">Info Entreprise</Translate>
                </Label>
                <AvInput id="comptable-infoEntreprise" type="select" className="form-control" name="infoEntrepriseId">
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
                <Label for="comptable-user">
                  <Translate contentKey="emnaBackEndApp.comptable.user">User</Translate>
                </Label>
                <AvInput id="comptable-user" type="select" className="form-control" name="userId">
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
                <Label for="comptable-adresse">
                  <Translate contentKey="emnaBackEndApp.comptable.adresse">Adresse</Translate>
                </Label>
                <AvInput id="comptable-adresse" type="select" className="form-control" name="adresseId" required>
                  {adresses
                    ? adresses.map(otherEntity => (
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
              <Button tag={Link} id="cancel-save" to="/comptable" replace color="info">
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
  comptableEntity: storeState.comptable.entity,
  loading: storeState.comptable.loading,
  updating: storeState.comptable.updating,
  updateSuccess: storeState.comptable.updateSuccess,
});

const mapDispatchToProps = {
  getInfoEntreprises,
  getUsers,
  getAdresses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ComptableUpdate);
