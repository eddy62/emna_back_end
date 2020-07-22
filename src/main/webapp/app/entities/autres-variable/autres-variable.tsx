import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './autres-variable.reducer';
import { IAutresVariable } from 'app/shared/model/autres-variable.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAutresVariableProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AutresVariable = (props: IAutresVariableProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { autresVariableList, match, loading } = props;
  return (
    <div>
      <h2 id="autres-variable-heading">
        <Translate contentKey="emnaBackEndApp.autresVariable.home.title">Autres Variables</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.autresVariable.home.createLabel">Create new Autres Variable</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {autresVariableList && autresVariableList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.autresVariable.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.autresVariable.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.autresVariable.montant">Montant</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.autresVariable.justificatif">Justificatif</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.autresVariable.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {autresVariableList.map((autresVariable, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${autresVariable.id}`} color="link" size="sm">
                      {autresVariable.id}
                    </Button>
                  </td>
                  <td>{autresVariable.description}</td>
                  <td>
                    {autresVariable.date ? <TextFormat type="date" value={autresVariable.date} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{autresVariable.montant}</td>
                  <td>{autresVariable.justificatif}</td>
                  <td>
                    {autresVariable.employeId ? <Link to={`employe/${autresVariable.employeId}`}>{autresVariable.employeId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${autresVariable.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${autresVariable.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${autresVariable.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.autresVariable.home.notFound">No Autres Variables found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ autresVariable }: IRootState) => ({
  autresVariableList: autresVariable.entities,
  loading: autresVariable.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AutresVariable);
