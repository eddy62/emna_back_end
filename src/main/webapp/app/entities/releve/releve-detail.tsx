import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './releve.reducer';
import { IReleve } from 'app/shared/model/releve.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReleveDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ReleveDetail = (props: IReleveDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { releveEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.releve.detail.title">Releve</Translate> [<b>{releveEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dateDebut">
              <Translate contentKey="emnaBackEndApp.releve.dateDebut">Date Debut</Translate>
            </span>
          </dt>
          <dd>
            {releveEntity.dateDebut ? <TextFormat value={releveEntity.dateDebut} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dateFin">
              <Translate contentKey="emnaBackEndApp.releve.dateFin">Date Fin</Translate>
            </span>
          </dt>
          <dd>{releveEntity.dateFin ? <TextFormat value={releveEntity.dateFin} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="banque">
              <Translate contentKey="emnaBackEndApp.releve.banque">Banque</Translate>
            </span>
          </dt>
          <dd>{releveEntity.banque}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.releve.etatReleve">Etat Releve</Translate>
          </dt>
          <dd>{releveEntity.etatReleveId ? releveEntity.etatReleveId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.releve.societe">Societe</Translate>
          </dt>
          <dd>{releveEntity.societeId ? releveEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/releve" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/releve/${releveEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ releve }: IRootState) => ({
  releveEntity: releve.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReleveDetail);
