import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etat-depense.reducer';
import { IEtatDepense } from 'app/shared/model/etat-depense.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtatDepenseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatDepenseUpdate = (props: IEtatDepenseUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { etatDepenseEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/etat-depense');
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
        ...etatDepenseEntity,
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
          <h2 id="emnaBackEndApp.etatDepense.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.etatDepense.home.createOrEditLabel">Create or edit a EtatDepense</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : etatDepenseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="etat-depense-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="etat-depense-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="libelleLabel" for="etat-depense-libelle">
                  <Translate contentKey="emnaBackEndApp.etatDepense.libelle">Libelle</Translate>
                </Label>
                <AvField id="etat-depense-libelle" type="text" name="libelle" />
              </AvGroup>
              <AvGroup>
                <Label id="codeRefLabel" for="etat-depense-codeRef">
                  <Translate contentKey="emnaBackEndApp.etatDepense.codeRef">Code Ref</Translate>
                </Label>
                <AvField id="etat-depense-codeRef" type="text" name="codeRef" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/etat-depense" replace color="info">
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
  etatDepenseEntity: storeState.etatDepense.entity,
  loading: storeState.etatDepense.loading,
  updating: storeState.etatDepense.updating,
  updateSuccess: storeState.etatDepense.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatDepenseUpdate);
