import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAbsence } from 'app/shared/model/absence.model';
import { getEntities as getAbsences } from 'app/entities/absence/absence.reducer';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { getEntities as getNoteDeFrais } from 'app/entities/note-de-frais/note-de-frais.reducer';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';
import { getEntities as getAutresVariables } from 'app/entities/autres-variable/autres-variable.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { getEntities as getFactures } from 'app/entities/facture/facture.reducer';
import { IReleve } from 'app/shared/model/releve.model';
import { getEntities as getReleves } from 'app/entities/releve/releve.reducer';
import { IContrat } from 'app/shared/model/contrat.model';
import { getEntities as getContrats } from 'app/entities/contrat/contrat.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { getEntities as getEmployes } from 'app/entities/employe/employe.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { getEntities as getDepenses } from 'app/entities/depense/depense.reducer';
import { getEntity, updateEntity, createEntity, reset } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDocumentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DocumentUpdate = (props: IDocumentUpdateProps) => {
  const [absenceId, setAbsenceId] = useState('0');
  const [noteDeFraisId, setNoteDeFraisId] = useState('0');
  const [autresVariablesId, setAutresVariablesId] = useState('0');
  const [factureId, setFactureId] = useState('0');
  const [releveId, setReleveId] = useState('0');
  const [contratId, setContratId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [depenseId, setDepenseId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const {
    documentEntity,
    absences,
    noteDeFrais,
    autresVariables,
    factures,
    releves,
    contrats,
    employes,
    depenses,
    loading,
    updating,
  } = props;

  const handleClose = () => {
    props.history.push('/document');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAbsences();
    props.getNoteDeFrais();
    props.getAutresVariables();
    props.getFactures();
    props.getReleves();
    props.getContrats();
    props.getEmployes();
    props.getDepenses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...documentEntity,
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
          <h2 id="emnaBackEndApp.document.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.document.home.createOrEditLabel">Create or edit a Document</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : documentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="document-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="document-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="cheminFichierLabel" for="document-cheminFichier">
                  <Translate contentKey="emnaBackEndApp.document.cheminFichier">Chemin Fichier</Translate>
                </Label>
                <AvField id="document-cheminFichier" type="text" name="cheminFichier" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="document-type">
                  <Translate contentKey="emnaBackEndApp.document.type">Type</Translate>
                </Label>
                <AvField id="document-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="document-nom">
                  <Translate contentKey="emnaBackEndApp.document.nom">Nom</Translate>
                </Label>
                <AvField id="document-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label for="document-absence">
                  <Translate contentKey="emnaBackEndApp.document.absence">Absence</Translate>
                </Label>
                <AvInput id="document-absence" type="select" className="form-control" name="absenceId">
                  <option value="" key="0" />
                  {absences
                    ? absences.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-noteDeFrais">
                  <Translate contentKey="emnaBackEndApp.document.noteDeFrais">Note De Frais</Translate>
                </Label>
                <AvInput id="document-noteDeFrais" type="select" className="form-control" name="noteDeFraisId">
                  <option value="" key="0" />
                  {noteDeFrais
                    ? noteDeFrais.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-autresVariables">
                  <Translate contentKey="emnaBackEndApp.document.autresVariables">Autres Variables</Translate>
                </Label>
                <AvInput id="document-autresVariables" type="select" className="form-control" name="autresVariablesId">
                  <option value="" key="0" />
                  {autresVariables
                    ? autresVariables.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-facture">
                  <Translate contentKey="emnaBackEndApp.document.facture">Facture</Translate>
                </Label>
                <AvInput id="document-facture" type="select" className="form-control" name="factureId">
                  <option value="" key="0" />
                  {factures
                    ? factures.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-releve">
                  <Translate contentKey="emnaBackEndApp.document.releve">Releve</Translate>
                </Label>
                <AvInput id="document-releve" type="select" className="form-control" name="releveId">
                  <option value="" key="0" />
                  {releves
                    ? releves.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-contrat">
                  <Translate contentKey="emnaBackEndApp.document.contrat">Contrat</Translate>
                </Label>
                <AvInput id="document-contrat" type="select" className="form-control" name="contratId">
                  <option value="" key="0" />
                  {contrats
                    ? contrats.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-employe">
                  <Translate contentKey="emnaBackEndApp.document.employe">Employe</Translate>
                </Label>
                <AvInput id="document-employe" type="select" className="form-control" name="employeId">
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
              <AvGroup>
                <Label for="document-depense">
                  <Translate contentKey="emnaBackEndApp.document.depense">Depense</Translate>
                </Label>
                <AvInput id="document-depense" type="select" className="form-control" name="depenseId">
                  <option value="" key="0" />
                  {depenses
                    ? depenses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/document" replace color="info">
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
  absences: storeState.absence.entities,
  noteDeFrais: storeState.noteDeFrais.entities,
  autresVariables: storeState.autresVariable.entities,
  factures: storeState.facture.entities,
  releves: storeState.releve.entities,
  contrats: storeState.contrat.entities,
  employes: storeState.employe.entities,
  depenses: storeState.depense.entities,
  documentEntity: storeState.document.entity,
  loading: storeState.document.loading,
  updating: storeState.document.updating,
  updateSuccess: storeState.document.updateSuccess,
});

const mapDispatchToProps = {
  getAbsences,
  getNoteDeFrais,
  getAutresVariables,
  getFactures,
  getReleves,
  getContrats,
  getEmployes,
  getDepenses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DocumentUpdate);
