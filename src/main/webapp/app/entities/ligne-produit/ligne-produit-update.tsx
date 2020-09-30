import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvFeedback, AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getProduits} from 'app/entities/produit/produit.reducer';
import {getEntities as getFactures} from 'app/entities/facture/facture.reducer';
import {getEntities as getDevis} from 'app/entities/devis/devis.reducer';
import {createEntity, getEntity, reset, updateEntity} from './ligne-produit.reducer';

export interface ILigneProduitUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneProduitUpdate = (props: ILigneProduitUpdateProps) => {
  const [produitId, setProduitId] = useState('0');
  const [factureId, setFactureId] = useState('0');
  const [devisId, setDevisId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { ligneProduitEntity, produits, factures, devis, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/ligne-produit');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProduits();
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
                <Label id="commentaireLabel" for="ligne-produit-commentaire">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.commentaire">Commentaire</Translate>
                </Label>
                <AvField id="ligne-produit-commentaire" type="text" name="commentaire" />
              </AvGroup>
              <AvGroup>
                <Label id="remiseLabel" for="ligne-produit-remise">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.remise">Remise</Translate>
                </Label>
                <AvField id="ligne-produit-remise" type="text" name="remise" />
              </AvGroup>
              <AvGroup>
                <Label for="ligne-produit-produit">
                  <Translate contentKey="emnaBackEndApp.ligneProduit.produit">Produit</Translate>
                </Label>
                <AvInput id="ligne-produit-produit" type="select" className="form-control" name="produitId" required>
                  {produits
                    ? produits.map(otherEntity => (
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
  produits: storeState.produit.entities,
  factures: storeState.facture.entities,
  devis: storeState.devis.entities,
  ligneProduitEntity: storeState.ligneProduit.entity,
  loading: storeState.ligneProduit.loading,
  updating: storeState.ligneProduit.updating,
  updateSuccess: storeState.ligneProduit.updateSuccess,
});

const mapDispatchToProps = {
  getProduits,
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
