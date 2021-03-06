import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etat-variable-paie.reducer';
import { IEtatVariablePaie } from 'app/shared/model/etat-variable-paie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatVariablePaieDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatVariablePaieDetail = (props: IEtatVariablePaieDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { etatVariablePaieEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.etatVariablePaie.detail.title">EtatVariablePaie</Translate> [
          <b>{etatVariablePaieEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.etatVariablePaie.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{etatVariablePaieEntity.codeRef}</dd>
          <dt>
            <span id="intitule">
              <Translate contentKey="emnaBackEndApp.etatVariablePaie.intitule">Intitule</Translate>
            </span>
          </dt>
          <dd>{etatVariablePaieEntity.intitule}</dd>
        </dl>
        <Button tag={Link} to="/etat-variable-paie" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etat-variable-paie/${etatVariablePaieEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ etatVariablePaie }: IRootState) => ({
  etatVariablePaieEntity: etatVariablePaie.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatVariablePaieDetail);
