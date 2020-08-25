import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './avance-rappel-salaire.reducer';
import { IAvanceRappelSalaire } from 'app/shared/model/avance-rappel-salaire.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAvanceRappelSalaireProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AvanceRappelSalaire = (props: IAvanceRappelSalaireProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { avanceRappelSalaireList, match, loading } = props;
  return (
    <div>
      <h2 id="avance-rappel-salaire-heading">
        <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.home.title">Avance Rappel Salaires</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.home.createLabel">Create new Avance Rappel Salaire</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {avanceRappelSalaireList && avanceRappelSalaireList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.debutPeriode">Debut Periode</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.finPeriode">Fin Periode</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.montant">Montant</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.etatVariablePaie">Etat Variable Paie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {avanceRappelSalaireList.map((avanceRappelSalaire, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${avanceRappelSalaire.id}`} color="link" size="sm">
                      {avanceRappelSalaire.id}
                    </Button>
                  </td>
                  <td>{avanceRappelSalaire.type}</td>
                  <td>
                    {avanceRappelSalaire.debutPeriode ? (
                      <TextFormat type="date" value={avanceRappelSalaire.debutPeriode} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {avanceRappelSalaire.finPeriode ? (
                      <TextFormat type="date" value={avanceRappelSalaire.finPeriode} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{avanceRappelSalaire.montant}</td>
                  <td>
                    {avanceRappelSalaire.etatVariablePaieId ? (
                      <Link to={`etat-variable-paie/${avanceRappelSalaire.etatVariablePaieId}`}>
                        {avanceRappelSalaire.etatVariablePaieId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {avanceRappelSalaire.employeId ? (
                      <Link to={`employe/${avanceRappelSalaire.employeId}`}>{avanceRappelSalaire.employeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${avanceRappelSalaire.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avanceRappelSalaire.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${avanceRappelSalaire.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.home.notFound">No Avance Rappel Salaires found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ avanceRappelSalaire }: IRootState) => ({
  avanceRappelSalaireList: avanceRappelSalaire.entities,
  loading: avanceRappelSalaire.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvanceRappelSalaire);
