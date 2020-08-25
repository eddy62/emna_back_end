import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './autres-variable.reducer';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAutresVariableDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AutresVariableDetail = (props: IAutresVariableDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { autresVariableEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.autresVariable.detail.title">AutresVariable</Translate> [<b>{autresVariableEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.autresVariable.description">Description</Translate>
            </span>
          </dt>
          <dd>{autresVariableEntity.description}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.autresVariable.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {autresVariableEntity.date ? <TextFormat value={autresVariableEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="montant">
              <Translate contentKey="emnaBackEndApp.autresVariable.montant">Montant</Translate>
            </span>
          </dt>
          <dd>{autresVariableEntity.montant}</dd>
          <dt>
            <span id="justificatif">
              <Translate contentKey="emnaBackEndApp.autresVariable.justificatif">Justificatif</Translate>
            </span>
          </dt>
          <dd>{autresVariableEntity.justificatif}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.autresVariable.etatVariablePaie">Etat Variable Paie</Translate>
          </dt>
          <dd>{autresVariableEntity.etatVariablePaieId ? autresVariableEntity.etatVariablePaieId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.autresVariable.employe">Employe</Translate>
          </dt>
          <dd>{autresVariableEntity.employeId ? autresVariableEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/autres-variable" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/autres-variable/${autresVariableEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ autresVariable }: IRootState) => ({
  autresVariableEntity: autresVariable.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AutresVariableDetail);
