import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etat-releve.reducer';
import { IEtatReleve } from 'app/shared/model/etat-releve.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatReleveProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EtatReleve = (props: IEtatReleveProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { etatReleveList, match, loading } = props;
  return (
    <div>
      <h2 id="etat-releve-heading">
        <Translate contentKey="emnaBackEndApp.etatReleve.home.title">Etat Releves</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.etatReleve.home.createLabel">Create new Etat Releve</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {etatReleveList && etatReleveList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatReleve.libelle">Libelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatReleve.codeRef">Code Ref</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etatReleveList.map((etatReleve, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${etatReleve.id}`} color="link" size="sm">
                      {etatReleve.id}
                    </Button>
                  </td>
                  <td>{etatReleve.libelle}</td>
                  <td>{etatReleve.codeRef}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${etatReleve.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatReleve.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatReleve.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.etatReleve.home.notFound">No Etat Releves found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ etatReleve }: IRootState) => ({
  etatReleveList: etatReleve.entities,
  loading: etatReleve.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatReleve);
