import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFacture } from 'app/shared/model/facture.model';
import { getEntities as getFactures } from 'app/entities/facture/facture.reducer';
import { IDevis } from 'app/shared/model/devis.model';
import { getEntities as getDevis } from 'app/entities/devis/devis.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ligne-produit.reducer';
import { ILigneProduit } from 'app/shared/model/ligne-produit.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILigneProduitUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneProduitUpdate = (props: ILigneProduitUpdateProps) => {
  const [factureId, setFactureId] = useState('0');
  const [devisId, setDevisId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ligneProduitEntity, factures, devis, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ligne-produit');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getFactures();
    props.getDevis();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...ligneProduitEntity,
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
          <h2 id="emnaBackEndApp.ligneProduit.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.ligneProduit.home.createOrEditLabel">Create or edit a LigneProduit</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : ligneProduitEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="ligne-produit-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="ligne-produit-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="quantiteLabel" for="ligne-produit-quantite">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.quantite">Quantite</Translate>
                </Label>
                <AvField
                  id="ligne-produit-quantite"
                  type="string"
                  className="form-control"
                  name="quantite"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nomLabel" for="ligne-produit-nom">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.nom">Nom</Translate>
                </Label>
                <AvField id="ligne-produit-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="ligne-produit-description">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.description">Description</Translate>
                </Label>
                <AvField id="ligne-produit-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="tvaLabel" for="ligne-produit-tva">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.tva">Tva</Translate>
                </Label>
                <AvField id="ligne-produit-tva" type="string" className="form-control" name="tva" />
              </AvGroup>
              <AvGroup>
                <Label id="prixLabel" for="ligne-produit-prix">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.prix">Prix</Translate>
                </Label>
                <AvField
                  id="ligne-produit-prix"
                  type="string"
                  className="form-control"
                  name="prix"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="ligne-produit-facture">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.facture">Facture</Translate>
                </Label>
                <AvInput id="ligne-produit-facture" type="select" className="form-control" name="factureId">
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
                <Label for="ligne-produit-devis">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.devis">Devis</Translate>
                </Label>
                <AvInput id="ligne-produit-devis" type="select" className="form-control" name="devisId">
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
              <Button tag={Link} id="cancel-save" to="/ligne-produit" replace color="info">
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
  factures: storeState.facture.entities,
  devis: storeState.devis.entities,
  ligneProduitEntity: storeState.ligneProduit.entity,
  loading: storeState.ligneProduit.loading,
  updating: storeState.ligneProduit.updating,
  updateSuccess: storeState.ligneProduit.updateSuccess,
});

const mapDispatchToProps = {
  getFactures,
  getDevis,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneProduitUpdate);
