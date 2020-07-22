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
import { IEtatFacture } from 'app/shared/model/etat-facture.model';
import { getEntities as getEtatFactures } from 'app/entities/etat-facture/etat-facture.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { IOperation } from 'app/shared/model/operation.model';
import { getEntities as getOperations } from 'app/entities/operation/operation.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { getEntities as getClientFournisseurs } from 'app/entities/client-fournisseur/client-fournisseur.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { getEntity, updateEntity, createEntity, reset } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFactureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FactureUpdate = (props: IFactureUpdateProps) => {
  const [adresseId, setAdresseId] = useState('0');
  const [etatFactureId, setEtatFactureId] = useState('0');
  const [societeId, setSocieteId] = useState('0');
  const [operationId, setOperationId] = useState('0');
  const [clientFournisseurId, setClientFournisseurId] = useState('0');
  const [listeProduitsId, setListeProduitsId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { factureEntity, adresses, etatFactures, societes, operations, clientFournisseurs, produits, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/facture');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdresses();
    props.getEtatFactures();
    props.getSocietes();
    props.getOperations();
    props.getClientFournisseurs();
    props.getProduits();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...factureEntity,
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
          <h2 id="emnaBackEndApp.facture.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.facture.home.createOrEditLabel">Create or edit a Facture</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : factureEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="facture-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="facture-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="numfactLabel" for="facture-numfact">
                  <Translate contentKey="emnaBackEndApp.facture.numfact">Numfact</Translate>
                </Label>
                <AvField id="facture-numfact" type="string" className="form-control" name="numfact" />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="facture-nom">
                  <Translate contentKey="emnaBackEndApp.facture.nom">Nom</Translate>
                </Label>
                <AvField id="facture-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="messageLabel" for="facture-message">
                  <Translate contentKey="emnaBackEndApp.facture.message">Message</Translate>
                </Label>
                <AvField id="facture-message" type="text" name="message" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="facture-date">
                  <Translate contentKey="emnaBackEndApp.facture.date">Date</Translate>
                </Label>
                <AvField id="facture-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="dateEcheanceLabel" for="facture-dateEcheance">
                  <Translate contentKey="emnaBackEndApp.facture.dateEcheance">Date Echeance</Translate>
                </Label>
                <AvField id="facture-dateEcheance" type="date" className="form-control" name="dateEcheance" />
              </AvGroup>
              <AvGroup>
                <Label id="prixHTLabel" for="facture-prixHT">
                  <Translate contentKey="emnaBackEndApp.facture.prixHT">Prix HT</Translate>
                </Label>
                <AvField id="facture-prixHT" type="string" className="form-control" name="prixHT" />
              </AvGroup>
              <AvGroup>
                <Label id="prixTTCLabel" for="facture-prixTTC">
                  <Translate contentKey="emnaBackEndApp.facture.prixTTC">Prix TTC</Translate>
                </Label>
                <AvField id="facture-prixTTC" type="string" className="form-control" name="prixTTC" />
              </AvGroup>
              <AvGroup>
                <Label id="tvaLabel" for="facture-tva">
                  <Translate contentKey="emnaBackEndApp.facture.tva">Tva</Translate>
                </Label>
                <AvField id="facture-tva" type="string" className="form-control" name="tva" />
              </AvGroup>
              <AvGroup>
                <Label id="fichierLabel" for="facture-fichier">
                  <Translate contentKey="emnaBackEndApp.facture.fichier">Fichier</Translate>
                </Label>
                <AvField id="facture-fichier" type="text" name="fichier" />
              </AvGroup>
              <AvGroup>
                <Label id="cheminFichierLabel" for="facture-cheminFichier">
                  <Translate contentKey="emnaBackEndApp.facture.cheminFichier">Chemin Fichier</Translate>
                </Label>
                <AvField id="facture-cheminFichier" type="text" name="cheminFichier" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="facture-type">
                  <Translate contentKey="emnaBackEndApp.facture.type">Type</Translate>
                </Label>
                <AvField id="facture-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label id="moyenDePaiementLabel" for="facture-moyenDePaiement">
                  <Translate contentKey="emnaBackEndApp.facture.moyenDePaiement">Moyen De Paiement</Translate>
                </Label>
                <AvField id="facture-moyenDePaiement" type="text" name="moyenDePaiement" />
              </AvGroup>
              <AvGroup>
                <Label for="facture-adresse">
                  <Translate contentKey="emnaBackEndApp.facture.adresse">Adresse</Translate>
                </Label>
                <AvInput id="facture-adresse" type="select" className="form-control" name="adresseId">
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
                <Label for="facture-etatFacture">
                  <Translate contentKey="emnaBackEndApp.facture.etatFacture">Etat Facture</Translate>
                </Label>
                <AvInput id="facture-etatFacture" type="select" className="form-control" name="etatFactureId">
                  <option value="" key="0" />
                  {etatFactures
                    ? etatFactures.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="facture-societe">
                  <Translate contentKey="emnaBackEndApp.facture.societe">Societe</Translate>
                </Label>
                <AvInput id="facture-societe" type="select" className="form-control" name="societeId">
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
                <Label for="facture-operation">
                  <Translate contentKey="emnaBackEndApp.facture.operation">Operation</Translate>
                </Label>
                <AvInput id="facture-operation" type="select" className="form-control" name="operationId">
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
                <Label for="facture-clientFournisseur">
                  <Translate contentKey="emnaBackEndApp.facture.clientFournisseur">Client Fournisseur</Translate>
                </Label>
                <AvInput id="facture-clientFournisseur" type="select" className="form-control" name="clientFournisseurId">
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
              <Button tag={Link} id="cancel-save" to="/facture" replace color="info">
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
  etatFactures: storeState.etatFacture.entities,
  societes: storeState.societe.entities,
  operations: storeState.operation.entities,
  clientFournisseurs: storeState.clientFournisseur.entities,
  produits: storeState.produit.entities,
  factureEntity: storeState.facture.entity,
  loading: storeState.facture.loading,
  updating: storeState.facture.updating,
  updateSuccess: storeState.facture.updateSuccess,
});

const mapDispatchToProps = {
  getAdresses,
  getEtatFactures,
  getSocietes,
  getOperations,
  getClientFournisseurs,
  getProduits,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureUpdate);
