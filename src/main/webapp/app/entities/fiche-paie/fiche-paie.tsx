import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fiche-paie.reducer';
import { IFichePaie } from 'app/shared/model/fiche-paie.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichePaieProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const FichePaie = (props: IFichePaieProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { fichePaieList, match, loading } = props;
  return (
    <div>
      <h2 id="fiche-paie-heading">
        <Translate contentKey="emnaBackEndApp.fichePaie.home.title">Fiche Paies</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.fichePaie.home.createLabel">Create new Fiche Paie</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {fichePaieList && fichePaieList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.fichePaie.debutPeriode">Debut Periode</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.fichePaie.finPeriode">Fin Periode</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.fichePaie.lienDocument">Lien Document</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.fichePaie.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fichePaieList.map((fichePaie, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fichePaie.id}`} color="link" size="sm">
                      {fichePaie.id}
                    </Button>
                  </td>
                  <td>
                    {fichePaie.debutPeriode ? (
                      <TextFormat type="date" value={fichePaie.debutPeriode} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {fichePaie.finPeriode ? <TextFormat type="date" value={fichePaie.finPeriode} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fichePaie.lienDocument}</td>
                  <td>{fichePaie.employeId ? <Link to={`employe/${fichePaie.employeId}`}>{fichePaie.employeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fichePaie.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fichePaie.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fichePaie.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.fichePaie.home.notFound">No Fiche Paies found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ fichePaie }: IRootState) => ({
  fichePaieList: fichePaie.entities,
  loading: fichePaie.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FichePaie);
