import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etat-variable-paie.reducer';
import { IEtatVariablePaie } from 'app/shared/model/etat-variable-paie.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtatVariablePaieUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatVariablePaieUpdate = (props: IEtatVariablePaieUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { etatVariablePaieEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/etat-variable-paie');
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
        ...etatVariablePaieEntity,
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
          <h2 id="emnaBackEndApp.etatVariablePaie.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.etatVariablePaie.home.createOrEditLabel">Create or edit a EtatVariablePaie</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : etatVariablePaieEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="etat-variable-paie-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="etat-variable-paie-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="codeRefLabel" for="etat-variable-paie-codeRef">
                  <Translate contentKey="emnaBackEndApp.etatVariablePaie.codeRef">Code Ref</Translate>
                </Label>
                <AvField
                  id="etat-variable-paie-codeRef"
                  type="text"
                  name="codeRef"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="intituleLabel" for="etat-variable-paie-intitule">
                  <Translate contentKey="emnaBackEndApp.etatVariablePaie.intitule">Intitule</Translate>
                </Label>
                <AvField
                  id="etat-variable-paie-intitule"
                  type="text"
                  name="intitule"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/etat-variable-paie" replace color="info">
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
  etatVariablePaieEntity: storeState.etatVariablePaie.entity,
  loading: storeState.etatVariablePaie.loading,
  updating: storeState.etatVariablePaie.updating,
  updateSuccess: storeState.etatVariablePaie.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatVariablePaieUpdate);
