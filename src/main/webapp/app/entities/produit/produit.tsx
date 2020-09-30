import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './produit.reducer';

export interface IProduitProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Produit = (props: IProduitProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { produitList, match, loading } = props;
  return (
    <div>
      <h2 id="produit-heading">
        <Translate contentKey="emnaBackEndApp.produit.home.title">Produits</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.produit.home.createLabel">Create new Produit</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {produitList && produitList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.reference">Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.tva">Tva</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.prix">Prix</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.unite">Unite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.produit.societe">Societe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {produitList.map((produit, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${produit.id}`} color="link" size="sm">
                      {produit.id}
                    </Button>
                  </td>
                  <td>{produit.nom}</td>
                  <td>{produit.reference}</td>
                  <td>{produit.tva}</td>
                  <td>{produit.prix}</td>
                  <td>{produit.unite}</td>
                  <td>{produit.description}</td>
                  <td>{produit.societeId ? <Link to={`societe/${produit.societeId}`}>{produit.societeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${produit.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${produit.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${produit.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="emnaBackEndApp.produit.home.notFound">No Produits found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ produit }: IRootState) => ({
  produitList: produit.entities,
  loading: produit.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Produit);
