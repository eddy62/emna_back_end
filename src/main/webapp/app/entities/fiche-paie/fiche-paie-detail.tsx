import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './fiche-paie.reducer';
import { IFichePaie } from 'app/shared/model/fiche-paie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichePaieDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FichePaieDetail = (props: IFichePaieDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { fichePaieEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.fichePaie.detail.title">FichePaie</Translate> [<b>{fichePaieEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="debutPeriode">
              <Translate contentKey="emnaBackEndApp.fichePaie.debutPeriode">Debut Periode</Translate>
            </span>
          </dt>
          <dd>
            {fichePaieEntity.debutPeriode ? (
              <TextFormat value={fichePaieEntity.debutPeriode} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="finPeriode">
              <Translate contentKey="emnaBackEndApp.fichePaie.finPeriode">Fin Periode</Translate>
            </span>
          </dt>
          <dd>
            {fichePaieEntity.finPeriode ? (
              <TextFormat value={fichePaieEntity.finPeriode} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lienDocument">
              <Translate contentKey="emnaBackEndApp.fichePaie.lienDocument">Lien Document</Translate>
            </span>
          </dt>
          <dd>{fichePaieEntity.lienDocument}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.fichePaie.employe">Employe</Translate>
          </dt>
          <dd>{fichePaieEntity.employeId ? fichePaieEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/fiche-paie" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fiche-paie/${fichePaieEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ fichePaie }: IRootState) => ({
  fichePaieEntity: fichePaie.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FichePaieDetail);
