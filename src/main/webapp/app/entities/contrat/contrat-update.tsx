import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmploye } from 'app/shared/model/employe.model';
import { getEntities as getEmployes } from 'app/entities/employe/employe.reducer';
import { IArticle } from 'app/shared/model/article.model';
import { getEntities as getArticles } from 'app/entities/article/article.reducer';
import { IClause } from 'app/shared/model/clause.model';
import { getEntities as getClauses } from 'app/entities/clause/clause.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contrat.reducer';
import { IContrat } from 'app/shared/model/contrat.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IContratUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContratUpdate = (props: IContratUpdateProps) => {
  const [employeId, setEmployeId] = useState('0');
  const [listeArticlesId, setListeArticlesId] = useState('0');
  const [listeClausesId, setListeClausesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { contratEntity, employes, articles, clauses, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/contrat');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getEmployes();
    props.getArticles();
    props.getClauses();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...contratEntity,
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
          <h2 id="emnaBackEndApp.contrat.home.createOrEditLabel">
            <Translate contentKey="emnaBackEndApp.contrat.home.createOrEditLabel">Create or edit a Contrat</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : contratEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="contrat-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="contrat-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="titreLabel" for="contrat-titre">
                  <Translate contentKey="emnaBackEndApp.contrat.titre">Titre</Translate>
                </Label>
                <AvField
                  id="contrat-titre"
                  type="text"
                  name="titre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreationLabel" for="contrat-dateCreation">
                  <Translate contentKey="emnaBackEndApp.contrat.dateCreation">Date Creation</Translate>
                </Label>
                <AvField
                  id="contrat-dateCreation"
                  type="date"
                  className="form-control"
                  name="dateCreation"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="signeLabel">
                  <AvInput id="contrat-signe" type="checkbox" className="form-check-input" name="signe" />
                  <Translate contentKey="emnaBackEndApp.contrat.signe">Signe</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="archiveLabel">
                  <AvInput id="contrat-archive" type="checkbox" className="form-check-input" name="archive" />
                  <Translate contentKey="emnaBackEndApp.contrat.archive">Archive</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="contrat-employe">
                  <Translate contentKey="emnaBackEndApp.contrat.employe">Employe</Translate>
                </Label>
                <AvInput id="contrat-employe" type="select" className="form-control" name="employeId">
                  <option value="" key="0" />
                  {employes
                    ? employes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/contrat" replace color="info">
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
  employes: storeState.employe.entities,
  articles: storeState.article.entities,
  clauses: storeState.clause.entities,
  contratEntity: storeState.contrat.entity,
  loading: storeState.contrat.loading,
  updating: storeState.contrat.updating,
  updateSuccess: storeState.contrat.updateSuccess,
});

const mapDispatchToProps = {
  getEmployes,
  getArticles,
  getClauses,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContratUpdate);
