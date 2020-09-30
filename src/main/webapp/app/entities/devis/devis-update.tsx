import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getEtatDevis} from 'app/entities/etat-devis/etat-devis.reducer';
import {getEntities as getClientFournisseurs} from 'app/entities/client-fournisseur/client-fournisseur.reducer';
import {createEntity, getEntity, reset, updateEntity} from './devis.reducer';

export interface IDevisUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DevisUpdate = (props: IDevisUpdateProps) => {
  const [etatDevisId, setEtatDevisId] = useState('0');
  const [clientFournisseurId, setClientFournisseurId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { devisEntity, etatDevis, clientFournisseurs, loading, updating } = props;

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
    props.getClientFournisseurs();
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
  clientFournisseurs: storeState.clientFournisseur.entities,
  devisEntity: storeState.devis.entity,
  loading: storeState.devis.loading,
  updating: storeState.devis.updating,
  updateSuccess: storeState.devis.updateSuccess,
});

const mapDispatchToProps = {
  getEtatDevis,
  getClientFournisseurs,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DevisUpdate);
