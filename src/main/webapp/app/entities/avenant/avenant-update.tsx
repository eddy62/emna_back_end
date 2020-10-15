import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './avenant.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAvenantUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvenantUpdate = (props: IAvenantUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { avenantEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/avenant');
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
        ...avenantEntity,
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
          <h2 id="emnaBackEndApp.avenant.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.avenant.home.createOrEditLabel">Create or edit a Avenant</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : avenantEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="avenant-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="avenant-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="referenceLabel" for="avenant-reference">
                  <Translate contentKey="emnaBackEndApp.avenant.reference">Reference</Translate>
                </Label>
                <AvField
                  id="avenant-reference"
                  type="text"
                  name="reference"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="signeLabel">
                  <AvInput id="avenant-signe" type="checkbox" className="form-check-input" name="signe" />
                  <Translate contentKey="emnaBackEndApp.avenant.signe">Signe</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="dateDeCreationLabel" for="avenant-dateDeCreation">
                  <Translate contentKey="emnaBackEndApp.avenant.dateDeCreation">Date De Creation</Translate>
                </Label>
                <AvField
                  id="avenant-dateDeCreation"
                  type="date"
                  className="form-control"
                  name="dateDeCreation"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateDeSignatureLabel" for="avenant-dateDeSignature">
                  <Translate contentKey="emnaBackEndApp.avenant.dateDeSignature">Date De Signature</Translate>
                </Label>
                <AvField id="avenant-dateDeSignature" type="date" className="form-control" name="dateDeSignature" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/avenant" replace color="info">
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
  avenantEntity: storeState.avenant.entity,
  loading: storeState.avenant.loading,
  updating: storeState.avenant.updating,
  updateSuccess: storeState.avenant.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvenantUpdate);
