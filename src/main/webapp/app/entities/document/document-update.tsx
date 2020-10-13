import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITypeDocument } from 'app/shared/model/type-document.model';
import { getEntities as getTypeDocuments } from 'app/entities/type-document/type-document.reducer';
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
import { IAbsence } from 'app/shared/model/absence.model';
import { getEntities as getAbsences } from 'app/entities/absence/absence.reducer';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { getEntities as getNoteDeFrais } from 'app/entities/note-de-frais/note-de-frais.reducer';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';
import { getEntities as getAutresVariables } from 'app/entities/autres-variable/autres-variable.reducer';
import { IDevis } from 'app/shared/model/devis.model';
import { getEntities as getDevis } from 'app/entities/devis/devis.reducer';
import { IDpae } from 'app/shared/model/dpae.model';
import { getEntities as getDpaes } from 'app/entities/dpae/dpae.reducer';
import { IFichePaie } from 'app/shared/model/fiche-paie.model';
import { getEntities as getFichePaies } from 'app/entities/fiche-paie/fiche-paie.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { getEntities as getAvenants } from 'app/entities/avenant/avenant.reducer';
import { getEntity, updateEntity, createEntity, reset } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDocumentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DocumentUpdate = (props: IDocumentUpdateProps) => {
  const [typeDocumentId, setTypeDocumentId] = useState('0');
  const [factureId, setFactureId] = useState('0');
  const [releveId, setReleveId] = useState('0');
  const [contratId, setContratId] = useState('0');
  const [employeId, setEmployeId] = useState('0');
  const [depenseId, setDepenseId] = useState('0');
  const [absenceId, setAbsenceId] = useState('0');
  const [noteDeFraisId, setNoteDeFraisId] = useState('0');
  const [autresVariableId, setAutresVariableId] = useState('0');
  const [devisId, setDevisId] = useState('0');
  const [dpaeId, setDpaeId] = useState('0');
  const [fichePaieId, setFichePaieId] = useState('0');
  const [avenantId, setAvenantId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const {
    documentEntity,
    typeDocuments,
    factures,
    releves,
    contrats,
    employes,
    depenses,
    absences,
    noteDeFrais,
    autresVariables,
    devis,
    dpaes,
    fichePaies,
    avenants,
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

    props.getTypeDocuments();
    props.getFactures();
    props.getReleves();
    props.getContrats();
    props.getEmployes();
    props.getDepenses();
    props.getAbsences();
    props.getNoteDeFrais();
    props.getAutresVariables();
    props.getDevis();
    props.getDpaes();
    props.getFichePaies();
    props.getAvenants();
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
                <Label id="nomLabel" for="document-nom">
                  <Translate contentKey="emnaBackEndApp.document.nom">Nom</Translate>
                </Label>
                <AvField id="document-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label for="document-typeDocument">
                  <Translate contentKey="emnaBackEndApp.document.typeDocument">Type Document</Translate>
                </Label>
                <AvInput id="document-typeDocument" type="select" className="form-control" name="typeDocumentId" required>
                  {typeDocuments
                    ? typeDocuments.map(otherEntity => (
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
                <Label for="document-autresVariable">
                  <Translate contentKey="emnaBackEndApp.document.autresVariable">Autres Variable</Translate>
                </Label>
                <AvInput id="document-autresVariable" type="select" className="form-control" name="autresVariableId">
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
                <Label for="document-devis">
                  <Translate contentKey="emnaBackEndApp.document.devis">Devis</Translate>
                </Label>
                <AvInput id="document-devis" type="select" className="form-control" name="devisId">
                  <option value="" key="0" />
                  {devis
                    ? devis.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-dpae">
                  <Translate contentKey="emnaBackEndApp.document.dpae">Dpae</Translate>
                </Label>
                <AvInput id="document-dpae" type="select" className="form-control" name="dpaeId">
                  <option value="" key="0" />
                  {dpaes
                    ? dpaes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-fichePaie">
                  <Translate contentKey="emnaBackEndApp.document.fichePaie">Fiche Paie</Translate>
                </Label>
                <AvInput id="document-fichePaie" type="select" className="form-control" name="fichePaieId">
                  <option value="" key="0" />
                  {fichePaies
                    ? fichePaies.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="document-avenant">
                  <Translate contentKey="emnaBackEndApp.document.avenant">Avenant</Translate>
                </Label>
                <AvInput id="document-avenant" type="select" className="form-control" name="avenantId">
                  <option value="" key="0" />
                  {avenants
                    ? avenants.map(otherEntity => (
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
  typeDocuments: storeState.typeDocument.entities,
  factures: storeState.facture.entities,
  releves: storeState.releve.entities,
  contrats: storeState.contrat.entities,
  employes: storeState.employe.entities,
  depenses: storeState.depense.entities,
  absences: storeState.absence.entities,
  noteDeFrais: storeState.noteDeFrais.entities,
  autresVariables: storeState.autresVariable.entities,
  devis: storeState.devis.entities,
  dpaes: storeState.dpae.entities,
  fichePaies: storeState.fichePaie.entities,
  avenants: storeState.avenant.entities,
  documentEntity: storeState.document.entity,
  loading: storeState.document.loading,
  updating: storeState.document.updating,
  updateSuccess: storeState.document.updateSuccess,
});

const mapDispatchToProps = {
  getTypeDocuments,
  getFactures,
  getReleves,
  getContrats,
  getEmployes,
  getDepenses,
  getAbsences,
  getNoteDeFrais,
  getAutresVariables,
  getDevis,
  getDpaes,
  getFichePaies,
  getAvenants,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DocumentUpdate);
