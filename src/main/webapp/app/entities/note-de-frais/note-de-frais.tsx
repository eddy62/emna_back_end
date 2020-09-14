import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './note-de-frais.reducer';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INoteDeFraisProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NoteDeFrais = (props: INoteDeFraisProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { noteDeFraisList, match, loading } = props;
  return (
    <div>
      <h2 id="note-de-frais-heading">
        <Translate contentKey="emnaBackEndApp.noteDeFrais.home.title">Note De Frais</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.noteDeFrais.home.createLabel">Create new Note De Frais</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {noteDeFraisList && noteDeFraisList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.designation">Designation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.montant">Montant</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.justificatif">Justificatif</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.mois">Mois</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.annee">Annee</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.etatVariablePaie">Etat Variable Paie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.noteDeFrais.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {noteDeFraisList.map((noteDeFrais, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${noteDeFrais.id}`} color="link" size="sm">
                      {noteDeFrais.id}
                    </Button>
                  </td>
                  <td>{noteDeFrais.designation}</td>
                  <td>{noteDeFrais.date ? <TextFormat type="date" value={noteDeFrais.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{noteDeFrais.montant}</td>
                  <td>{noteDeFrais.justificatif}</td>
                  <td>{noteDeFrais.mois}</td>
                  <td>{noteDeFrais.annee}</td>
                  <td>
                    {noteDeFrais.etatVariablePaieId ? (
                      <Link to={`etat-variable-paie/${noteDeFrais.etatVariablePaieId}`}>{noteDeFrais.etatVariablePaieId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{noteDeFrais.employeId ? <Link to={`employe/${noteDeFrais.employeId}`}>{noteDeFrais.employeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${noteDeFrais.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${noteDeFrais.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${noteDeFrais.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.noteDeFrais.home.notFound">No Note De Frais found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ noteDeFrais }: IRootState) => ({
  noteDeFraisList: noteDeFrais.entities,
  loading: noteDeFrais.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NoteDeFrais);
