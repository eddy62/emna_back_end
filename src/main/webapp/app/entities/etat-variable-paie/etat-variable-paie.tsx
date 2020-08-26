import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etat-variable-paie.reducer';
import { IEtatVariablePaie } from 'app/shared/model/etat-variable-paie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtatVariablePaieProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const EtatVariablePaie = (props: IEtatVariablePaieProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { etatVariablePaieList, match, loading } = props;
  return (
    <div>
      <h2 id="etat-variable-paie-heading">
        <Translate contentKey="emnaBackEndApp.etatVariablePaie.home.title">Etat Variable Paies</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.etatVariablePaie.home.createLabel">Create new Etat Variable Paie</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {etatVariablePaieList && etatVariablePaieList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatVariablePaie.codeRef">Code Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.etatVariablePaie.intitule">Intitule</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {etatVariablePaieList.map((etatVariablePaie, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${etatVariablePaie.id}`} color="link" size="sm">
                      {etatVariablePaie.id}
                    </Button>
                  </td>
                  <td>{etatVariablePaie.codeRef}</td>
                  <td>{etatVariablePaie.intitule}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${etatVariablePaie.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatVariablePaie.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${etatVariablePaie.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.etatVariablePaie.home.notFound">No Etat Variable Paies found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ etatVariablePaie }: IRootState) => ({
  etatVariablePaieList: etatVariablePaie.entities,
  loading: etatVariablePaie.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EtatVariablePaie);
