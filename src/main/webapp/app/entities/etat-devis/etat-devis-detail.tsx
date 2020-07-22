import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etat-devis.reducer';
import { IEtatDevis } from 'app/shared/model/etat-devis.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatDevisDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatDevisDetail = (props: IEtatDevisDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { etatDevisEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.etatDevis.detail.title">EtatDevis</Translate> [<b>{etatDevisEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="libelle">
              <Translate contentKey="emnaBackEndApp.etatDevis.libelle">Libelle</Translate>
            </span>
          </dt>
          <dd>{etatDevisEntity.libelle}</dd>
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.etatDevis.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{etatDevisEntity.codeRef}</dd>
        </dl>
        <Button tag={Link} to="/etat-devis" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etat-devis/${etatDevisEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ etatDevis }: IRootState) => ({
  etatDevisEntity: etatDevis.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatDevisDetail);
