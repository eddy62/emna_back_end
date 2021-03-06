import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './produit.reducer';

export interface IProduitDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProduitDetail = (props: IProduitDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { produitEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.produit.detail.title">Produit</Translate> [<b>{produitEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nom">
              <Translate contentKey="emnaBackEndApp.produit.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{produitEntity.nom}</dd>
          <dt>
            <span id="reference">
              <Translate contentKey="emnaBackEndApp.produit.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{produitEntity.reference}</dd>
          <dt>
            <span id="tva">
              <Translate contentKey="emnaBackEndApp.produit.tva">Tva</Translate>
            </span>
          </dt>
          <dd>{produitEntity.tva}</dd>
          <dt>
            <span id="prix">
              <Translate contentKey="emnaBackEndApp.produit.prix">Prix</Translate>
            </span>
          </dt>
          <dd>{produitEntity.prix}</dd>
          <dt>
            <span id="unite">
              <Translate contentKey="emnaBackEndApp.produit.unite">Unite</Translate>
            </span>
          </dt>
          <dd>{produitEntity.unite}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.produit.description">Description</Translate>
            </span>
          </dt>
          <dd>{produitEntity.description}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.produit.societe">Societe</Translate>
          </dt>
          <dd>{produitEntity.societeId ? produitEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/produit" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/produit/${produitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ produit }: IRootState) => ({
  produitEntity: produit.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProduitDetail);
