import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etat-depense.reducer';
import { IEtatDepense } from 'app/shared/model/etat-depense.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatDepenseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EtatDepense = (props: IEtatDepenseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { etatDepenseList, match, loading } = props;
  return (
    <div>
      <h2 id="etat-depense-heading">
        <Translate contentKey="emnaBackEndApp.etatDepense.home.title">Etat Depenses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.etatDepense.home.createLabel">Create new Etat Depense</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {etatDepenseList && etatDepenseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatDepense.libelle">Libelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatDepense.codeRef">Code Ref</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etatDepenseList.map((etatDepense, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${etatDepense.id}`} color="link" size="sm">
                      {etatDepense.id}
                    </Button>
                  </td>
                  <td>{etatDepense.libelle}</td>
                  <td>{etatDepense.codeRef}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${etatDepense.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatDepense.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatDepense.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.etatDepense.home.notFound">No Etat Depenses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ etatDepense }: IRootState) => ({
  etatDepenseList: etatDepense.entities,
  loading: etatDepense.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatDepense);
