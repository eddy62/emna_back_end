import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ligne-produit.reducer';
import { ILigneProduit } from 'app/shared/model/ligne-produit.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILigneProduitDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneProduitDetail = (props: ILigneProduitDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { ligneProduitEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.ligneProduit.detail.title">LigneProduit</Translate> [<b>{ligneProduitEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="quantite">
              <Translate contentKey="emnaBackEndApp.ligneProduit.quantite">Quantite</Translate>
            </span>
          </dt>
          <dd>{ligneProduitEntity.quantite}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.ligneProduit.produit">Produit</Translate>
          </dt>
          <dd>{ligneProduitEntity.produitId ? ligneProduitEntity.produitId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.ligneProduit.facture">Facture</Translate>
          </dt>
          <dd>{ligneProduitEntity.factureId ? ligneProduitEntity.factureId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.ligneProduit.devis">Devis</Translate>
          </dt>
          <dd>{ligneProduitEntity.devisId ? ligneProduitEntity.devisId : ''}</dd>
        </dl>
        <Button tag={Link} to="/ligne-produit" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ligne-produit/${ligneProduitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ ligneProduit }: IRootState) => ({
  ligneProduitEntity: ligneProduit.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneProduitDetail);
