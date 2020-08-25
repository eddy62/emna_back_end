import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './heures-supplementaires.reducer';
import { IHeuresSupplementaires } from 'app/shared/model/heures-supplementaires.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHeuresSupplementairesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HeuresSupplementairesDetail = (props: IHeuresSupplementairesDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { heuresSupplementairesEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.heuresSupplementaires.detail.title">HeuresSupplementaires</Translate> [
          <b>{heuresSupplementairesEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.heuresSupplementaires.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {heuresSupplementairesEntity.date ? (
              <TextFormat value={heuresSupplementairesEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="nombreHeure">
              <Translate contentKey="emnaBackEndApp.heuresSupplementaires.nombreHeure">Nombre Heure</Translate>
            </span>
          </dt>
          <dd>{heuresSupplementairesEntity.nombreHeure}</dd>
          <dt>
            <span id="justificatif">
              <Translate contentKey="emnaBackEndApp.heuresSupplementaires.justificatif">Justificatif</Translate>
            </span>
          </dt>
          <dd>{heuresSupplementairesEntity.justificatif}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.heuresSupplementaires.employe">Employe</Translate>
          </dt>
          <dd>{heuresSupplementairesEntity.employeId ? heuresSupplementairesEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/heures-supplementaires" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/heures-supplementaires/${heuresSupplementairesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ heuresSupplementaires }: IRootState) => ({
  heuresSupplementairesEntity: heuresSupplementaires.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HeuresSupplementairesDetail);
