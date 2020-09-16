import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etat-depense.reducer';
import { IEtatDepense } from 'app/shared/model/etat-depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatDepenseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EtatDepenseDetail = (props: IEtatDepenseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { etatDepenseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.etatDepense.detail.title">EtatDepense</Translate> [<b>{etatDepenseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="libelle">
              <Translate contentKey="emnaBackEndApp.etatDepense.libelle">Libelle</Translate>
            </span>
          </dt>
          <dd>{etatDepenseEntity.libelle}</dd>
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.etatDepense.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{etatDepenseEntity.codeRef}</dd>
        </dl>
        <Button tag={Link} to="/etat-depense" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/etat-depense/${etatDepenseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ etatDepense }: IRootState) => ({
  etatDepenseEntity: etatDepense.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatDepenseDetail);
