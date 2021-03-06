import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './comptable.reducer';
import { IComptable } from 'app/shared/model/comptable.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IComptableProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Comptable = (props: IComptableProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { comptableList, match, loading } = props;
  return (
    <div>
      <h2 id="comptable-heading">
        <Translate contentKey="emnaBackEndApp.comptable.home.title">Comptables</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.comptable.home.createLabel">Create new Comptable</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {comptableList && comptableList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.comptable.civilite">Civilite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.comptable.infoEntreprise">Info Entreprise</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.comptable.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.comptable.adresse">Adresse</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {comptableList.map((comptable, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${comptable.id}`} color="link" size="sm">
                      {comptable.id}
                    </Button>
                  </td>
                  <td>{comptable.civilite}</td>
                  <td>
                    {comptable.infoEntrepriseId ? (
                      <Link to={`info-entreprise/${comptable.infoEntrepriseId}`}>{comptable.infoEntrepriseId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{comptable.userId ? comptable.userId : ''}</td>
                  <td>{comptable.adresseId ? <Link to={`adresse/${comptable.adresseId}`}>{comptable.adresseId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${comptable.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${comptable.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${comptable.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.comptable.home.notFound">No Comptables found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ comptable }: IRootState) => ({
  comptableList: comptable.entities,
  loading: comptable.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Comptable);
