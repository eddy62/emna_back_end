import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './avenant.reducer';
import { IAvenant } from 'app/shared/model/avenant.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvenantProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Avenant = (props: IAvenantProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { avenantList, match, loading } = props;
  return (
    <div>
      <h2 id="avenant-heading">
        <Translate contentKey="emnaBackEndApp.avenant.home.title">Avenants</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.avenant.home.createLabel">Create new Avenant</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {avenantList && avenantList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avenant.reference">Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avenant.signe">Signe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avenant.dateDeCreation">Date De Creation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avenant.dateDeSignature">Date De Signature</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {avenantList.map((avenant, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${avenant.id}`} color="link" size="sm">
                      {avenant.id}
                    </Button>
                  </td>
                  <td>{avenant.reference}</td>
                  <td>{avenant.signe ? 'true' : 'false'}</td>
                  <td>
                    {avenant.dateDeCreation ? (
                      <TextFormat type="date" value={avenant.dateDeCreation} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {avenant.dateDeSignature ? (
                      <TextFormat type="date" value={avenant.dateDeSignature} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${avenant.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avenant.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avenant.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.avenant.home.notFound">No Avenants found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ avenant }: IRootState) => ({
  avenantList: avenant.entities,
  loading: avenant.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Avenant);
