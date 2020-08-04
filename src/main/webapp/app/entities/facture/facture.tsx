import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFactureProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Facture = (props: IFactureProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { factureList, match, loading } = props;
  return (
    <div>
      <h2 id="facture-heading">
        <Translate contentKey="emnaBackEndApp.facture.home.title">Factures</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.facture.home.createLabel">Create new Facture</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {factureList && factureList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.numfact">Numfact</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.message">Message</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.dateEcheance">Date Echeance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.prixHT">Prix HT</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.prixTTC">Prix TTC</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.tva">Tva</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.fichier">Fichier</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.cheminFichier">Chemin Fichier</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.moyenDePaiement">Moyen De Paiement</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.etatFacture">Etat Facture</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.societe">Societe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.operation">Operation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.facture.clientFournisseur">Client Fournisseur</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {factureList.map((facture, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${facture.id}`} color="link" size="sm">
                      {facture.id}
                    </Button>
                  </td>
                  <td>{facture.numfact}</td>
                  <td>{facture.nom}</td>
                  <td>{facture.message}</td>
                  <td>
                    <TextFormat type="date" value={facture.date} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={facture.dateEcheance} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{facture.prixHT}</td>
                  <td>{facture.prixTTC}</td>
                  <td>{facture.tva}</td>
                  <td>{facture.fichier}</td>
                  <td>{facture.cheminFichier}</td>
                  <td>{facture.type}</td>
                  <td>{facture.moyenDePaiement}</td>
                  <td>{facture.adresseId ? <Link to={`adresse/${facture.adresseId}`}>{facture.adresseId}</Link> : ''}</td>
                  <td>{facture.etatFactureId ? <Link to={`etat-facture/${facture.etatFactureId}`}>{facture.etatFactureId}</Link> : ''}</td>
                  <td>{facture.societeId ? <Link to={`societe/${facture.societeId}`}>{facture.societeId}</Link> : ''}</td>
                  <td>{facture.operationId ? <Link to={`operation/${facture.operationId}`}>{facture.operationId}</Link> : ''}</td>
                  <td>
                    {facture.clientFournisseurId ? (
                      <Link to={`client-fournisseur/${facture.clientFournisseurId}`}>{facture.clientFournisseurId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${facture.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facture.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${facture.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.facture.home.notFound">No Factures found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ facture }: IRootState) => ({
  factureList: facture.entities,
  loading: facture.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Facture);
