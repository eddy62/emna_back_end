import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './releve.reducer';
import { IReleve } from 'app/shared/model/releve.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReleveProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Releve = (props: IReleveProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { releveList, match, loading } = props;
  return (
    <div>
      <h2 id="releve-heading">
        <Translate contentKey="emnaBackEndApp.releve.home.title">Releves</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.releve.home.createLabel">Create new Releve</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {releveList && releveList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.dateDebut">Date Debut</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.dateFin">Date Fin</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.solde">Solde</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.banque">Banque</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.cheminFichier">Chemin Fichier</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.etatReleve">Etat Releve</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.releve.societe">Societe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {releveList.map((releve, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${releve.id}`} color="link" size="sm">
                      {releve.id}
                    </Button>
                  </td>
                  <td>{releve.dateDebut ? <TextFormat type="date" value={releve.dateDebut} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{releve.dateFin ? <TextFormat type="date" value={releve.dateFin} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{releve.solde}</td>
                  <td>{releve.banque}</td>
                  <td>{releve.cheminFichier}</td>
                  <td>{releve.etatReleveId ? <Link to={`etat-releve/${releve.etatReleveId}`}>{releve.etatReleveId}</Link> : ''}</td>
                  <td>{releve.societeId ? <Link to={`societe/${releve.societeId}`}>{releve.societeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${releve.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${releve.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${releve.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.releve.home.notFound">No Releves found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ releve }: IRootState) => ({
  releveList: releve.entities,
  loading: releve.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Releve);
