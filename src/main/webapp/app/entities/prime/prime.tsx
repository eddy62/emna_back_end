import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './prime.reducer';
import { IPrime } from 'app/shared/model/prime.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPrimeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Prime = (props: IPrimeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { primeList, match, loading } = props;
  return (
    <div>
      <h2 id="prime-heading">
        <Translate contentKey="emnaBackEndApp.prime.home.title">Primes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.prime.home.createLabel">Create new Prime</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {primeList && primeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.prime.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.prime.montant">Montant</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.prime.typePrime">Type Prime</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.prime.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {primeList.map((prime, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${prime.id}`} color="link" size="sm">
                      {prime.id}
                    </Button>
                  </td>
                  <td>{prime.type}</td>
                  <td>{prime.montant}</td>
                  <td>{prime.typePrimeId ? <Link to={`type-prime/${prime.typePrimeId}`}>{prime.typePrimeId}</Link> : ''}</td>
                  <td>{prime.employeId ? <Link to={`employe/${prime.employeId}`}>{prime.employeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${prime.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${prime.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${prime.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.prime.home.notFound">No Primes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ prime }: IRootState) => ({
  primeList: prime.entities,
  loading: prime.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Prime);
