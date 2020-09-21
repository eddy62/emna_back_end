import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './clause.reducer';
import { IClause } from 'app/shared/model/clause.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClauseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClauseDetail = (props: IClauseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { clauseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.clause.detail.title">Clause</Translate> [<b>{clauseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="reference">
              <Translate contentKey="emnaBackEndApp.clause.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{clauseEntity.reference}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.clause.description">Description</Translate>
            </span>
          </dt>
          <dd>{clauseEntity.description}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.clause.listeContrats">Liste Contrats</Translate>
          </dt>
          <dd>
            {clauseEntity.listeContrats
              ? clauseEntity.listeContrats.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {clauseEntity.listeContrats && i === clauseEntity.listeContrats.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.clause.listeAvenants">Liste Avenants</Translate>
          </dt>
          <dd>
            {clauseEntity.listeAvenants
              ? clauseEntity.listeAvenants.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {clauseEntity.listeAvenants && i === clauseEntity.listeAvenants.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.clause.societe">Societe</Translate>
          </dt>
          <dd>{clauseEntity.societeId ? clauseEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/clause" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/clause/${clauseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ clause }: IRootState) => ({
  clauseEntity: clause.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClauseDetail);
