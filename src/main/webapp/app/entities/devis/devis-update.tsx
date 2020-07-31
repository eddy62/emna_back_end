import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEtatDevis } from 'app/shared/model/etat-devis.model';
import { getEntities as getEtatDevis } from 'app/entities/etat-devis/etat-devis.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { getEntities as getSocietes } from 'app/entities/societe/societe.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { getEntities as getClientFournisseurs } from 'app/entities/client-fournisseur/client-fournisseur.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { getEntity, updateEntity, createEntity, reset } from './devis.reducer';
import { IDevis } from 'app/shared/model/devis.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDevisUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DevisUpdate = (props: IDevisUpdateProps) => {
  const [etatDevisId, setEtatDevisId] = useState('0');
  const [societeId, setSocieteId] = useState('0');
  const [clientFournisseurId, setClientFournisseurId] = useState('0');
  const [listeProduitsId, setListeProduitsId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { devisEntity, etatDevis, societes, clientFournisseurs, produits, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/devis');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEtatDevis();
    props.getSocietes();
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
        ...devisEntity,
        ...values
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
          <h2 id="emnaBackEndApp.devis.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.devis.home.createOrEditLabel">Create or edit a Devis</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : devisEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="devis-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="devis-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="numDevisLabel" for="devis-numDevis">
                  <Translate contentKey="emnaBackEndApp.devis.numDevis">Num Devis</Translate>
                </Label>
                <AvField id="devis-numDevis" type="string" className="form-control" name="numDevis" />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="devis-nom">
                  <Translate contentKey="emnaBackEndApp.devis.nom">Nom</Translate>
                </Label>
                <AvField id="devis-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="messageLabel" for="devis-message">
                  <Translate contentKey="emnaBackEndApp.devis.message">Message</Translate>
                </Label>
                <AvField id="devis-message" type="text" name="message" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreationLabel" for="devis-dateCreation">
                  <Translate contentKey="emnaBackEndApp.devis.dateCreation">Date Creation</Translate>
                </Label>
                <AvField id="devis-dateCreation" type="date" className="form-control" name="dateCreation" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLimiteLabel" for="devis-dateLimite">
                  <Translate contentKey="emnaBackEndApp.devis.dateLimite">Date Limite</Translate>
                </Label>
                <AvField id="devis-dateLimite" type="date" className="form-control" name="dateLimite" />
              </AvGroup>
              <AvGroup>
                <Label id="prixHTLabel" for="devis-prixHT">
                  <Translate contentKey="emnaBackEndApp.devis.prixHT">Prix HT</Translate>
                </Label>
                <AvField id="devis-prixHT" type="string" className="form-control" name="prixHT" />
              </AvGroup>
              <AvGroup>
                <Label id="prixTTCLabel" for="devis-prixTTC">
                  <Translate contentKey="emnaBackEndApp.devis.prixTTC">Prix TTC</Translate>
                </Label>
                <AvField id="devis-prixTTC" type="string" className="form-control" name="prixTTC" />
              </AvGroup>
              <AvGroup>
                <Label id="tvaLabel" for="devis-tva">
                  <Translate contentKey="emnaBackEndApp.devis.tva">Tva</Translate>
                </Label>
                <AvField id="devis-tva" type="string" className="form-control" name="tva" />
              </AvGroup>
              <AvGroup>
                <Label id="cheminFichierLabel" for="devis-cheminFichier">
                  <Translate contentKey="emnaBackEndApp.devis.cheminFichier">Chemin Fichier</Translate>
                </Label>
                <AvField id="devis-cheminFichier" type="text" name="cheminFichier" />
              </AvGroup>
              <AvGroup>
                <Label for="devis-etatDevis">
                  <Translate contentKey="emnaBackEndApp.devis.etatDevis">Etat Devis</Translate>
                </Label>
                <AvInput id="devis-etatDevis" type="select" className="form-control" name="etatDevisId">
                  <option value="" key="0" />
                  {etatDevis
                    ? etatDevis.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="devis-societe">
                  <Translate contentKey="emnaBackEndApp.devis.societe">Societe</Translate>
                </Label>
                <AvInput id="devis-societe" type="select" className="form-control" name="societeId">
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
                <Label for="devis-clientFournisseur">
                  <Translate contentKey="emnaBackEndApp.devis.clientFournisseur">Client Fournisseur</Translate>
                </Label>
                <AvInput id="devis-clientFournisseur" type="select" className="form-control" name="clientFournisseurId">
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
              <Button tag={Link} id="cancel-save" to="/devis" replace color="info">
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
  etatDevis: storeState.etatDevis.entities,
  societes: storeState.societe.entities,
  clientFournisseurs: storeState.clientFournisseur.entities,
  produits: storeState.produit.entities,
  devisEntity: storeState.devis.entity,
  loading: storeState.devis.loading,
  updating: storeState.devis.updating,
  updateSuccess: storeState.devis.updateSuccess
});

const mapDispatchToProps = {
  getEtatDevis,
  getSocietes,
  getClientFournisseurs,
  getProduits,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DevisUpdate);
