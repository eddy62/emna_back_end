import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etat-facture.reducer';
import { IEtatFacture } from 'app/shared/model/etat-facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatFactureProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EtatFacture = (props: IEtatFactureProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { etatFactureList, match, loading } = props;
  return (
    <div>
      <h2 id="etat-facture-heading">
        <Translate contentKey="emnaBackEndApp.etatFacture.home.title">Etat Factures</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.etatFacture.home.createLabel">Create new Etat Facture</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {etatFactureList && etatFactureList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatFacture.libelle">Libelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatFacture.codeRef">Code Ref</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etatFactureList.map((etatFacture, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${etatFacture.id}`} color="link" size="sm">
                      {etatFacture.id}
                    </Button>
                  </td>
                  <td>{etatFacture.libelle}</td>
                  <td>{etatFacture.codeRef}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${etatFacture.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatFacture.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatFacture.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.etatFacture.home.notFound">No Etat Factures found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ etatFacture }: IRootState) => ({
  etatFactureList: etatFacture.entities,
  loading: etatFacture.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatFacture);
