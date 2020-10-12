import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './avenant.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvenantDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvenantDetail = (props: IAvenantDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { avenantEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.avenant.detail.title">Avenant</Translate> [<b>{avenantEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="reference">
              <Translate contentKey="emnaBackEndApp.avenant.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{avenantEntity.reference}</dd>
          <dt>
            <span id="signe">
              <Translate contentKey="emnaBackEndApp.avenant.signe">Signe</Translate>
            </span>
          </dt>
          <dd>{avenantEntity.signe ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.avenant.saisieArticle">Saisie Article</Translate>
          </dt>
          <dd>{avenantEntity.saisieArticleId ? avenantEntity.saisieArticleId : ''}</dd>
        </dl>
        <Button tag={Link} to="/avenant" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/avenant/${avenantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ avenant }: IRootState) => ({
  avenantEntity: avenant.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvenantDetail);
