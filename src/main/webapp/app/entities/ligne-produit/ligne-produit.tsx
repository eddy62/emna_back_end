import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ligne-produit.reducer';
import { ILigneProduit } from 'app/shared/model/ligne-produit.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILigneProduitProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LigneProduit = (props: ILigneProduitProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { ligneProduitList, match, loading } = props;
  return (
    <div>
      <h2 id="ligne-produit-heading">
        <Translate contentKey="emnaBackEndApp.ligneProduit.home.title">Ligne Produits</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.ligneProduit.home.createLabel">Create new Ligne Produit</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {ligneProduitList && ligneProduitList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.ligneProduit.quantite">Quantite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.ligneProduit.produit">Produit</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.ligneProduit.facture">Facture</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.ligneProduit.devis">Devis</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ligneProduitList.map((ligneProduit, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ligneProduit.id}`} color="link" size="sm">
                      {ligneProduit.id}
                    </Button>
                  </td>
                  <td>{ligneProduit.quantite}</td>
                  <td>{ligneProduit.produitId ? <Link to={`produit/${ligneProduit.produitId}`}>{ligneProduit.produitId}</Link> : ''}</td>
                  <td>{ligneProduit.factureId ? <Link to={`facture/${ligneProduit.factureId}`}>{ligneProduit.factureId}</Link> : ''}</td>
                  <td>{ligneProduit.devisId ? <Link to={`devis/${ligneProduit.devisId}`}>{ligneProduit.devisId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ligneProduit.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ligneProduit.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ligneProduit.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.ligneProduit.home.notFound">No Ligne Produits found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ ligneProduit }: IRootState) => ({
  ligneProduitList: ligneProduit.entities,
  loading: ligneProduit.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneProduit);
