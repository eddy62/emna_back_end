import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etat-facture.reducer';
import { IEtatFacture } from 'app/shared/model/etat-facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatFactureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatFactureDetail = (props: IEtatFactureDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { etatFactureEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.etatFacture.detail.title">EtatFacture</Translate> [<b>{etatFactureEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="libelle">
              <Translate contentKey="emnaBackEndApp.etatFacture.libelle">Libelle</Translate>
            </span>
          </dt>
          <dd>{etatFactureEntity.libelle}</dd>
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.etatFacture.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{etatFactureEntity.codeRef}</dd>
        </dl>
        <Button tag={Link} to="/etat-facture" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etat-facture/${etatFactureEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ etatFacture }: IRootState) => ({
  etatFactureEntity: etatFacture.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatFactureDetail);
