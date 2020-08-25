import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './client-fournisseur.reducer';
import { IClientFournisseur } from 'app/shared/model/client-fournisseur.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientFournisseurProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ClientFournisseur = (props: IClientFournisseurProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { clientFournisseurList, match, loading } = props;
  return (
    <div>
      <h2 id="client-fournisseur-heading">
        <Translate contentKey="emnaBackEndApp.clientFournisseur.home.title">Client Fournisseurs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.clientFournisseur.home.createLabel">Create new Client Fournisseur</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {clientFournisseurList && clientFournisseurList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.siren">Siren</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.telephone">Telephone</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clientFournisseur.societe">Societe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clientFournisseurList.map((clientFournisseur, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${clientFournisseur.id}`} color="link" size="sm">
                      {clientFournisseur.id}
                    </Button>
                  </td>
                  <td>{clientFournisseur.nom}</td>
                  <td>{clientFournisseur.siren}</td>
                  <td>{clientFournisseur.telephone}</td>
                  <td>{clientFournisseur.email}</td>
                  <td>
                    {clientFournisseur.adresseId ? (
                      <Link to={`adresse/${clientFournisseur.adresseId}`}>{clientFournisseur.adresseId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {clientFournisseur.societeId ? (
                      <Link to={`societe/${clientFournisseur.societeId}`}>{clientFournisseur.societeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${clientFournisseur.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${clientFournisseur.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${clientFournisseur.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.clientFournisseur.home.notFound">No Client Fournisseurs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ clientFournisseur }: IRootState) => ({
  clientFournisseurList: clientFournisseur.entities,
  loading: clientFournisseur.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientFournisseur);
