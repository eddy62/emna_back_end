import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './type-prime.reducer';
import { ITypePrime } from 'app/shared/model/type-prime.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypePrimeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypePrimeDetail = (props: ITypePrimeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { typePrimeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.typePrime.detail.title">TypePrime</Translate> [<b>{typePrimeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.typePrime.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{typePrimeEntity.codeRef}</dd>
          <dt>
            <span id="intitule">
              <Translate contentKey="emnaBackEndApp.typePrime.intitule">Intitule</Translate>
            </span>
          </dt>
          <dd>{typePrimeEntity.intitule}</dd>
        </dl>
        <Button tag={Link} to="/type-prime" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-prime/${typePrimeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ typePrime }: IRootState) => ({
  typePrimeEntity: typePrime.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypePrimeDetail);
