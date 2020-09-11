import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepenseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DepenseDetail = (props: IDepenseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { depenseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.depense.detail.title">Depense</Translate> [<b>{depenseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="numero">
              <Translate contentKey="emnaBackEndApp.depense.numero">Numero</Translate>
            </span>
          </dt>
          <dd>{depenseEntity.numero}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.depense.date">Date</Translate>
            </span>
          </dt>
          <dd>{depenseEntity.date ? <TextFormat value={depenseEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="prix">
              <Translate contentKey="emnaBackEndApp.depense.prix">Prix</Translate>
            </span>
          </dt>
          <dd>{depenseEntity.prix}</dd>
          <dt>
            <span id="moyenDePaiement">
              <Translate contentKey="emnaBackEndApp.depense.moyenDePaiement">Moyen De Paiement</Translate>
            </span>
          </dt>
          <dd>{depenseEntity.moyenDePaiement}</dd>
          <dt>
            <span id="raison">
              <Translate contentKey="emnaBackEndApp.depense.raison">Raison</Translate>
            </span>
          </dt>
          <dd>{depenseEntity.raison}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.depense.societe">Societe</Translate>
          </dt>
          <dd>{depenseEntity.societeId ? depenseEntity.societeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.depense.operation">Operation</Translate>
          </dt>
          <dd>{depenseEntity.operationId ? depenseEntity.operationId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.depense.clientFournisseur">Client Fournisseur</Translate>
          </dt>
          <dd>{depenseEntity.clientFournisseurId ? depenseEntity.clientFournisseurId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.depense.etatDepense">Etat Depense</Translate>
          </dt>
          <dd>{depenseEntity.etatDepenseId ? depenseEntity.etatDepenseId : ''}</dd>
        </dl>
        <Button tag={Link} to="/depense" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/depense/${depenseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ depense }: IRootState) => ({
  depenseEntity: depense.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DepenseDetail);
