import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './operation.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IOperationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OperationDetail = (props: IOperationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { operationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.operation.detail.title">Operation</Translate> [<b>{operationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.operation.date">Date</Translate>
            </span>
          </dt>
          <dd>{operationEntity.date ? <TextFormat value={operationEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.operation.description">Description</Translate>
            </span>
          </dt>
          <dd>{operationEntity.description}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="emnaBackEndApp.operation.type">Type</Translate>
            </span>
          </dt>
          <dd>{operationEntity.type}</dd>
          <dt>
            <span id="rapproche">
              <Translate contentKey="emnaBackEndApp.operation.rapproche">Rapproche</Translate>
            </span>
          </dt>
          <dd>{operationEntity.rapproche ? 'true' : 'false'}</dd>
          <dt>
            <span id="solde">
              <Translate contentKey="emnaBackEndApp.operation.solde">Solde</Translate>
            </span>
          </dt>
          <dd>{operationEntity.solde}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.operation.releve">Releve</Translate>
          </dt>
          <dd>{operationEntity.releveId ? operationEntity.releveId : ''}</dd>
        </dl>
        <Button tag={Link} to="/operation" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/operation/${operationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ operation }: IRootState) => ({
  operationEntity: operation.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OperationDetail);
