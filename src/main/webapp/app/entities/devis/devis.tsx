import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './devis.reducer';
import { IDevis } from 'app/shared/model/devis.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDevisProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Devis = (props: IDevisProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { devisList, match, loading } = props;
  return (
    <div>
      <h2 id="devis-heading">
        <Translate contentKey="emnaBackEndApp.devis.home.title">Devis</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.devis.home.createLabel">Create new Devis</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {devisList && devisList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.numDevis">Num Devis</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.message">Message</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.dateCreation">Date Creation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.dateLimite">Date Limite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.prixHT">Prix HT</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.prixTTC">Prix TTC</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.tva">Tva</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.cheminFichier">Chemin Fichier</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.etatDevis">Etat Devis</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.societe">Societe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.devis.clientFournisseur">Client Fournisseur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {devisList.map((devis, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${devis.id}`} color="link" size="sm">
                      {devis.id}
                    </Button>
                  </td>
                  <td>{devis.numDevis}</td>
                  <td>{devis.nom}</td>
                  <td>{devis.message}</td>
                  <td>
                    <TextFormat type="date" value={devis.dateCreation} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={devis.dateLimite} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{devis.prixHT}</td>
                  <td>{devis.prixTTC}</td>
                  <td>{devis.tva}</td>
                  <td>{devis.cheminFichier}</td>
                  <td>{devis.etatDevisId ? <Link to={`etat-devis/${devis.etatDevisId}`}>{devis.etatDevisId}</Link> : ''}</td>
                  <td>{devis.societeId ? <Link to={`societe/${devis.societeId}`}>{devis.societeId}</Link> : ''}</td>
                  <td>
                    {devis.clientFournisseurId ? (
                      <Link to={`client-fournisseur/${devis.clientFournisseurId}`}>{devis.clientFournisseurId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${devis.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${devis.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${devis.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.devis.home.notFound">No Devis found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ devis }: IRootState) => ({
  devisList: devis.entities,
  loading: devis.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Devis);
