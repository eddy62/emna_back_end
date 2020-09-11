import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './depense.reducer';
import { IDepense } from 'app/shared/model/depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDepenseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Depense = (props: IDepenseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { depenseList, match, loading } = props;
  return (
    <div>
      <h2 id="depense-heading">
        <Translate contentKey="emnaBackEndApp.depense.home.title">Depenses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.depense.home.createLabel">Create new Depense</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {depenseList && depenseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.numero">Numero</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.prix">Prix</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.moyenDePaiement">Moyen De Paiement</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.raison">Raison</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.societe">Societe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.operation">Operation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.clientFournisseur">Client Fournisseur</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.depense.etatDepense">Etat Depense</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {depenseList.map((depense, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${depense.id}`} color="link" size="sm">
                      {depense.id}
                    </Button>
                  </td>
                  <td>{depense.numero}</td>
                  <td>{depense.date ? <TextFormat type="date" value={depense.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{depense.prix}</td>
                  <td>{depense.moyenDePaiement}</td>
                  <td>{depense.raison}</td>
                  <td>{depense.societeId ? <Link to={`societe/${depense.societeId}`}>{depense.societeId}</Link> : ''}</td>
                  <td>{depense.operationId ? <Link to={`operation/${depense.operationId}`}>{depense.operationId}</Link> : ''}</td>
                  <td>
                    {depense.clientFournisseurId ? (
                      <Link to={`client-fournisseur/${depense.clientFournisseurId}`}>{depense.clientFournisseurId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{depense.etatDepenseId ? <Link to={`etat-depense/${depense.etatDepenseId}`}>{depense.etatDepenseId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${depense.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${depense.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${depense.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.depense.home.notFound">No Depenses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ depense }: IRootState) => ({
  depenseList: depense.entities,
  loading: depense.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Depense);
