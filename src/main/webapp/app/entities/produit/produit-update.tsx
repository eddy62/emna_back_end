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
import { getEntity, updateEntity, createEntity, reset } from './produit.reducer';
import { IProduit } from 'app/shared/model/produit.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProduitUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProduitUpdate = (props: IProduitUpdateProps) => {
  const [societeId, setSocieteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { produitEntity, societes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/produit');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...produitEntity,
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
          <h2 id="emnaBackEndApp.produit.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.produit.home.createOrEditLabel">Create or edit a Produit</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : produitEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="produit-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="produit-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nomLabel" for="produit-nom">
                  <Translate contentKey="emnaBackEndApp.produit.nom">Nom</Translate>
                </Label>
                <AvField id="produit-nom" type="text" name="nom" />
              </AvGroup>
              <AvGroup>
                <Label id="referenceLabel" for="produit-reference">
                  <Translate contentKey="emnaBackEndApp.produit.reference">Reference</Translate>
                </Label>
                <AvField id="produit-reference" type="string" className="form-control" name="reference" />
              </AvGroup>
              <AvGroup>
                <Label id="tvaLabel" for="produit-tva">
                  <Translate contentKey="emnaBackEndApp.produit.tva">Tva</Translate>
                </Label>
                <AvField id="produit-tva" type="string" className="form-control" name="tva" />
              </AvGroup>
              <AvGroup>
                <Label id="prixLabel" for="produit-prix">
                  <Translate contentKey="emnaBackEndApp.produit.prix">Prix</Translate>
                </Label>
                <AvField id="produit-prix" type="string" className="form-control" name="prix" />
              </AvGroup>
              <AvGroup>
                <Label id="uniteLabel" for="produit-unite">
                  <Translate contentKey="emnaBackEndApp.produit.unite">Unite</Translate>
                </Label>
                <AvField id="produit-unite" type="text" name="unite" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="produit-description">
                  <Translate contentKey="emnaBackEndApp.produit.description">Description</Translate>
                </Label>
                <AvField id="produit-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="produit-societe">
                  <Translate contentKey="emnaBackEndApp.produit.societe">Societe</Translate>
                </Label>
                <AvInput id="produit-societe" type="select" className="form-control" name="societeId">
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
              <Button tag={Link} id="cancel-save" to="/produit" replace color="info">
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
  produitEntity: storeState.produit.entity,
  loading: storeState.produit.loading,
  updating: storeState.produit.updating,
  updateSuccess: storeState.produit.updateSuccess,
});

const mapDispatchToProps = {
  getSocietes,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProduitUpdate);
