import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './contrat.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IContratDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContratDetail = (props: IContratDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { contratEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.contrat.detail.title">Contrat</Translate> [<b>{contratEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="titre">
              <Translate contentKey="emnaBackEndApp.contrat.titre">Titre</Translate>
            </span>
          </dt>
          <dd>{contratEntity.titre}</dd>
          <dt>
            <span id="dateCreation">
              <Translate contentKey="emnaBackEndApp.contrat.dateCreation">Date Creation</Translate>
            </span>
          </dt>
          <dd>
            {contratEntity.dateCreation ? (
              <TextFormat value={contratEntity.dateCreation} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="signe">
              <Translate contentKey="emnaBackEndApp.contrat.signe">Signe</Translate>
            </span>
          </dt>
          <dd>{contratEntity.signe ? 'true' : 'false'}</dd>
          <dt>
            <span id="archive">
              <Translate contentKey="emnaBackEndApp.contrat.archive">Archive</Translate>
            </span>
          </dt>
          <dd>{contratEntity.archive ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.contrat.typeContrat">Type Contrat</Translate>
          </dt>
          <dd>{contratEntity.typeContratId ? contratEntity.typeContratId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.contrat.employe">Employe</Translate>
          </dt>
          <dd>{contratEntity.employeId ? contratEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/contrat" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contrat/${contratEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ contrat }: IRootState) => ({
  contratEntity: contrat.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContratDetail);
