import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtatReleve } from 'app/shared/model/etat-releve.model';
import { getEntities as getEtatReleves } from 'app/entities/etat-releve/etat-releve.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { getEntity, updateEntity, createEntity, reset } from './releve.reducer';
import { IReleve } from 'app/shared/model/releve.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IReleveUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReleveUpdate = (props: IReleveUpdateProps) => {
  const [etatReleveId, setEtatReleveId] = useState('0');
  const [societeId, setSocieteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { releveEntity, etatReleves, societes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/releve');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEtatReleves();
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
        ...releveEntity,
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
          <h2 id="emnaBackEndApp.releve.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.releve.home.createOrEditLabel">Create or edit a Releve</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : releveEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="releve-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="releve-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateDebutLabel" for="releve-dateDebut">
                  <Translate contentKey="emnaBackEndApp.releve.dateDebut">Date Debut</Translate>
                </Label>
                <AvField id="releve-dateDebut" type="date" className="form-control" name="dateDebut" />
              </AvGroup>
              <AvGroup>
                <Label id="dateFinLabel" for="releve-dateFin">
                  <Translate contentKey="emnaBackEndApp.releve.dateFin">Date Fin</Translate>
                </Label>
                <AvField id="releve-dateFin" type="date" className="form-control" name="dateFin" />
              </AvGroup>
              <AvGroup>
                <Label id="soldeLabel" for="releve-solde">
                  <Translate contentKey="emnaBackEndApp.releve.solde">Solde</Translate>
                </Label>
                <AvField id="releve-solde" type="string" className="form-control" name="solde" />
              </AvGroup>
              <AvGroup>
                <Label id="banqueLabel" for="releve-banque">
                  <Translate contentKey="emnaBackEndApp.releve.banque">Banque</Translate>
                </Label>
                <AvField id="releve-banque" type="text" name="banque" />
              </AvGroup>
              <AvGroup>
                <Label id="cheminFichierLabel" for="releve-cheminFichier">
                  <Translate contentKey="emnaBackEndApp.releve.cheminFichier">Chemin Fichier</Translate>
                </Label>
                <AvField id="releve-cheminFichier" type="text" name="cheminFichier" />
              </AvGroup>
              <AvGroup>
                <Label for="releve-etatReleve">
                  <Translate contentKey="emnaBackEndApp.releve.etatReleve">Etat Releve</Translate>
                </Label>
                <AvInput id="releve-etatReleve" type="select" className="form-control" name="etatReleveId">
                  <option value="" key="0" />
                  {etatReleves
                    ? etatReleves.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="releve-societe">
                  <Translate contentKey="emnaBackEndApp.releve.societe">Societe</Translate>
                </Label>
                <AvInput id="releve-societe" type="select" className="form-control" name="societeId">
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
              <Button tag={Link} id="cancel-save" to="/releve" replace color="info">
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
  etatReleves: storeState.etatReleve.entities,
  societes: storeState.societe.entities,
  releveEntity: storeState.releve.entity,
  loading: storeState.releve.loading,
  updating: storeState.releve.updating,
  updateSuccess: storeState.releve.updateSuccess,
});

const mapDispatchToProps = {
  getEtatReleves,
  getSocietes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReleveUpdate);
