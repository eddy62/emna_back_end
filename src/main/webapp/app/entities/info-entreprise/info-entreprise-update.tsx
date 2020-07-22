import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './info-entreprise.reducer';
import { IInfoEntreprise } from 'app/shared/model/info-entreprise.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInfoEntrepriseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InfoEntrepriseUpdate = (props: IInfoEntrepriseUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { infoEntrepriseEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/info-entreprise');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...infoEntrepriseEntity,
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
          <h2 id="emnaBackEndApp.infoEntreprise.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.infoEntreprise.home.createOrEditLabel">Create or edit a InfoEntreprise</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : infoEntrepriseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="info-entreprise-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="info-entreprise-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="raisonSocialeLabel" for="info-entreprise-raisonSociale">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.raisonSociale">Raison Sociale</Translate>
                </Label>
                <AvField id="info-entreprise-raisonSociale" type="text" name="raisonSociale" />
              </AvGroup>
              <AvGroup>
                <Label id="telephoneLabel" for="info-entreprise-telephone">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.telephone">Telephone</Translate>
                </Label>
                <AvField id="info-entreprise-telephone" type="text" name="telephone" />
              </AvGroup>
              <AvGroup>
                <Label id="faxLabel" for="info-entreprise-fax">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.fax">Fax</Translate>
                </Label>
                <AvField id="info-entreprise-fax" type="text" name="fax" />
              </AvGroup>
              <AvGroup>
                <Label id="formeJuridiqueLabel" for="info-entreprise-formeJuridique">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.formeJuridique">Forme Juridique</Translate>
                </Label>
                <AvField id="info-entreprise-formeJuridique" type="text" name="formeJuridique" />
              </AvGroup>
              <AvGroup>
                <Label id="dateDeCreationLabel" for="info-entreprise-dateDeCreation">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.dateDeCreation">Date De Creation</Translate>
                </Label>
                <AvField id="info-entreprise-dateDeCreation" type="date" className="form-control" name="dateDeCreation" />
              </AvGroup>
              <AvGroup>
                <Label id="sirenLabel" for="info-entreprise-siren">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.siren">Siren</Translate>
                </Label>
                <AvField id="info-entreprise-siren" type="text" name="siren" />
              </AvGroup>
              <AvGroup>
                <Label id="siretLabel" for="info-entreprise-siret">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.siret">Siret</Translate>
                </Label>
                <AvField id="info-entreprise-siret" type="text" name="siret" />
              </AvGroup>
              <AvGroup>
                <Label id="domaineDactiviteLabel" for="info-entreprise-domaineDactivite">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.domaineDactivite">Domaine Dactivite</Translate>
                </Label>
                <AvField id="info-entreprise-domaineDactivite" type="text" name="domaineDactivite" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="info-entreprise-description">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.description">Description</Translate>
                </Label>
                <AvField id="info-entreprise-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="info-entreprise-email">
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.email">Email</Translate>
                </Label>
                <AvField id="info-entreprise-email" type="text" name="email" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/info-entreprise" replace color="info">
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
  infoEntrepriseEntity: storeState.infoEntreprise.entity,
  loading: storeState.infoEntreprise.loading,
  updating: storeState.infoEntreprise.updating,
  updateSuccess: storeState.infoEntreprise.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InfoEntrepriseUpdate);
