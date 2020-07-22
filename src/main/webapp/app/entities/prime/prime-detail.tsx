import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './prime.reducer';
import { IPrime } from 'app/shared/model/prime.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPrimeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PrimeDetail = (props: IPrimeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { primeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.prime.detail.title">Prime</Translate> [<b>{primeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="emnaBackEndApp.prime.type">Type</Translate>
            </span>
          </dt>
          <dd>{primeEntity.type}</dd>
          <dt>
            <span id="montant">
              <Translate contentKey="emnaBackEndApp.prime.montant">Montant</Translate>
            </span>
          </dt>
          <dd>{primeEntity.montant}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.prime.typePrime">Type Prime</Translate>
          </dt>
          <dd>{primeEntity.typePrimeId ? primeEntity.typePrimeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.prime.employe">Employe</Translate>
          </dt>
          <dd>{primeEntity.employeId ? primeEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/prime" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/prime/${primeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ prime }: IRootState) => ({
  primeEntity: prime.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PrimeDetail);
