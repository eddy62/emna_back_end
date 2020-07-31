import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './statut-employe.reducer';
import { IStatutEmploye } from 'app/shared/model/statut-employe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStatutEmployeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const StatutEmploye = (props: IStatutEmployeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { statutEmployeList, match, loading } = props;
  return (
    <div>
      <h2 id="statut-employe-heading">
        <Translate contentKey="emnaBackEndApp.statutEmploye.home.title">Statut Employes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.statutEmploye.home.createLabel">Create new Statut Employe</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {statutEmployeList && statutEmployeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.statutEmploye.codeRef">Code Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.statutEmploye.libelle">Libelle</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {statutEmployeList.map((statutEmploye, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${statutEmploye.id}`} color="link" size="sm">
                      {statutEmploye.id}
                    </Button>
                  </td>
                  <td>{statutEmploye.codeRef}</td>
                  <td>{statutEmploye.libelle}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${statutEmploye.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${statutEmploye.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${statutEmploye.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.statutEmploye.home.notFound">No Statut Employes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ statutEmploye }: IRootState) => ({
  statutEmployeList: statutEmploye.entities,
  loading: statutEmploye.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StatutEmploye);
