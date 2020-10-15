import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getContrats} from 'app/entities/contrat/contrat.reducer';
import {createEntity, getEntity, reset, updateEntity} from './dpae.reducer';

export interface IDpaeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpaeUpdate = (props: IDpaeUpdateProps) => {
  const [contratId, setContratId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { dpaeEntity, contrats, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/dpae');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getContrats();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...dpaeEntity,
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
          <h2 id="emnaBackEndApp.dpae.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.dpae.home.createOrEditLabel">Create or edit a Dpae</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : dpaeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="dpae-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="dpae-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="lieuLabel" for="dpae-lieu">
                  <Translate contentKey="emnaBackEndApp.dpae.lieu">Lieu</Translate>
                </Label>
                <AvField
                  id="dpae-lieu"
                  type="text"
                  name="lieu"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="dpae-date">
                  <Translate contentKey="emnaBackEndApp.dpae.date">Date</Translate>
                </Label>
                <AvField
                  id="dpae-date"
                  type="date"
                  className="form-control"
                  name="date"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="heureEmbaucheLabel" for="dpae-heureEmbauche">
                  <Translate contentKey="emnaBackEndApp.dpae.heureEmbauche">Heure Embauche</Translate>
                </Label>
                <AvField
                  id="dpae-heureEmbauche"
                  type="text"
                  name="heureEmbauche"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentaireLabel" for="dpae-commentaire">
                  <Translate contentKey="emnaBackEndApp.dpae.commentaire">Commentaire</Translate>
                </Label>
                <AvField id="dpae-commentaire" type="text" name="commentaire" />
              </AvGroup>
              <AvGroup>
                <Label id="retourApiUrssafLabel" for="dpae-retourApiUrssaf">
                  <Translate contentKey="emnaBackEndApp.dpae.retourApiUrssaf">Retour Api Urssaf</Translate>
                </Label>
                <AvField id="dpae-retourApiUrssaf" type="text" name="retourApiUrssaf" />
              </AvGroup>
              <AvGroup>
                <Label for="dpae-contrat">
                  <Translate contentKey="emnaBackEndApp.dpae.contrat">Contrat</Translate>
                </Label>
                <AvInput id="dpae-contrat" type="select" className="form-control" name="contratId">
                  <option value="" key="0" />
                  {contrats
                    ? contrats.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/dpae" replace color="info">
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
  contrats: storeState.contrat.entities,
  dpaeEntity: storeState.dpae.entity,
  loading: storeState.dpae.loading,
  updating: storeState.dpae.updating,
  updateSuccess: storeState.dpae.updateSuccess,
});

const mapDispatchToProps = {
  getContrats,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpaeUpdate);
