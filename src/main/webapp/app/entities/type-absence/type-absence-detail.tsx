import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './type-absence.reducer';
import { ITypeAbsence } from 'app/shared/model/type-absence.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypeAbsenceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypeAbsenceDetail = (props: ITypeAbsenceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { typeAbsenceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.typeAbsence.detail.title">TypeAbsence</Translate> [<b>{typeAbsenceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.typeAbsence.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{typeAbsenceEntity.codeRef}</dd>
          <dt>
            <span id="intitule">
              <Translate contentKey="emnaBackEndApp.typeAbsence.intitule">Intitule</Translate>
            </span>
          </dt>
          <dd>{typeAbsenceEntity.intitule}</dd>
        </dl>
        <Button tag={Link} to="/type-absence" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-absence/${typeAbsenceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ typeAbsence }: IRootState) => ({
  typeAbsenceEntity: typeAbsence.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypeAbsenceDetail);
