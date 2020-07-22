import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './type-absence.reducer';
import { ITypeAbsence } from 'app/shared/model/type-absence.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypeAbsenceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TypeAbsence = (props: ITypeAbsenceProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { typeAbsenceList, match, loading } = props;
  return (
    <div>
      <h2 id="type-absence-heading">
        <Translate contentKey="emnaBackEndApp.typeAbsence.home.title">Type Absences</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.typeAbsence.home.createLabel">Create new Type Absence</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {typeAbsenceList && typeAbsenceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.typeAbsence.intitule">Intitule</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {typeAbsenceList.map((typeAbsence, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${typeAbsence.id}`} color="link" size="sm">
                      {typeAbsence.id}
                    </Button>
                  </td>
                  <td>{typeAbsence.intitule}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${typeAbsence.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${typeAbsence.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${typeAbsence.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.typeAbsence.home.notFound">No Type Absences found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ typeAbsence }: IRootState) => ({
  typeAbsenceList: typeAbsence.entities,
  loading: typeAbsence.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypeAbsence);
