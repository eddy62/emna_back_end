import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { IOperation } from 'app/shared/model/operation.model';
import { getEntities as getOperations } from 'app/entities/operation/operation.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { getEntities as getClientFournisseurs } from 'app/entities/client-fournisseur/client-fournisseur.reducer';
import { IEtatDepense } from 'app/shared/model/etat-depense.model';
import { getEntities as getEtatDepenses } from 'app/entities/etat-depense/etat-depense.reducer';
import { getEntity, updateEntity, createEntity, reset } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDepenseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DepenseUpdate = (props: IDepenseUpdateProps) => {
  const [societeId, setSocieteId] = useState('0');
  const [operationId, setOperationId] = useState('0');
  const [clientFournisseurId, setClientFournisseurId] = useState('0');
  const [etatDepenseId, setEtatDepenseId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { depenseEntity, societes, operations, clientFournisseurs, etatDepenses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/depense');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSocietes();
    props.getOperations();
    props.getClientFournisseurs();
    props.getEtatDepenses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...depenseEntity,
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
          <h2 id="emnaBackEndApp.depense.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.depense.home.createOrEditLabel">Create or edit a Depense</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : depenseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="depense-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="depense-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="numeroLabel" for="depense-numero">
                  <Translate contentKey="emnaBackEndApp.depense.numero">Numero</Translate>
                </Label>
                <AvField
                  id="depense-numero"
                  type="string"
                  className="form-control"
                  name="numero"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="depense-date">
                  <Translate contentKey="emnaBackEndApp.depense.date">Date</Translate>
                </Label>
                <AvField id="depense-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="prixLabel" for="depense-prix">
                  <Translate contentKey="emnaBackEndApp.depense.prix">Prix</Translate>
                </Label>
                <AvField id="depense-prix" type="string" className="form-control" name="prix" />
              </AvGroup>
              <AvGroup>
                <Label id="moyenDePaiementLabel" for="depense-moyenDePaiement">
                  <Translate contentKey="emnaBackEndApp.depense.moyenDePaiement">Moyen De Paiement</Translate>
                </Label>
                <AvField id="depense-moyenDePaiement" type="text" name="moyenDePaiement" />
              </AvGroup>
              <AvGroup>
                <Label id="raisonLabel" for="depense-raison">
                  <Translate contentKey="emnaBackEndApp.depense.raison">Raison</Translate>
                </Label>
                <AvField id="depense-raison" type="text" name="raison" />
              </AvGroup>
              <AvGroup>
                <Label for="depense-societe">
                  <Translate contentKey="emnaBackEndApp.depense.societe">Societe</Translate>
                </Label>
                <AvInput id="depense-societe" type="select" className="form-control" name="societeId">
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
              <AvGroup>
                <Label for="depense-operation">
                  <Translate contentKey="emnaBackEndApp.depense.operation">Operation</Translate>
                </Label>
                <AvInput id="depense-operation" type="select" className="form-control" name="operationId">
                  <option value="" key="0" />
                  {operations
                    ? operations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="depense-clientFournisseur">
                  <Translate contentKey="emnaBackEndApp.depense.clientFournisseur">Client Fournisseur</Translate>
                </Label>
                <AvInput id="depense-clientFournisseur" type="select" className="form-control" name="clientFournisseurId">
                  <option value="" key="0" />
                  {clientFournisseurs
                    ? clientFournisseurs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="depense-etatDepense">
                  <Translate contentKey="emnaBackEndApp.depense.etatDepense">Etat Depense</Translate>
                </Label>
                <AvInput id="depense-etatDepense" type="select" className="form-control" name="etatDepenseId">
                  <option value="" key="0" />
                  {etatDepenses
                    ? etatDepenses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/depense" replace color="info">
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
  societes: storeState.societe.entities,
  operations: storeState.operation.entities,
  clientFournisseurs: storeState.clientFournisseur.entities,
  etatDepenses: storeState.etatDepense.entities,
  depenseEntity: storeState.depense.entity,
  loading: storeState.depense.loading,
  updating: storeState.depense.updating,
  updateSuccess: storeState.depense.updateSuccess,
});

const mapDispatchToProps = {
  getSocietes,
  getOperations,
  getClientFournisseurs,
  getEtatDepenses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DepenseUpdate);
