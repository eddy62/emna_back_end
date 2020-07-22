import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etat-devis.reducer';
import { IEtatDevis } from 'app/shared/model/etat-devis.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatDevisProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EtatDevis = (props: IEtatDevisProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { etatDevisList, match, loading } = props;
  return (
    <div>
      <h2 id="etat-devis-heading">
        <Translate contentKey="emnaBackEndApp.etatDevis.home.title">Etat Devis</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.etatDevis.home.createLabel">Create new Etat Devis</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {etatDevisList && etatDevisList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatDevis.libelle">Libelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatDevis.codeRef">Code Ref</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etatDevisList.map((etatDevis, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${etatDevis.id}`} color="link" size="sm">
                      {etatDevis.id}
                    </Button>
                  </td>
                  <td>{etatDevis.libelle}</td>
                  <td>{etatDevis.codeRef}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${etatDevis.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatDevis.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatDevis.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.etatDevis.home.notFound">No Etat Devis found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ etatDevis }: IRootState) => ({
  etatDevisList: etatDevis.entities,
  loading: etatDevis.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatDevis);
