import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './heures-supplementaires.reducer';
import { IHeuresSupplementaires } from 'app/shared/model/heures-supplementaires.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHeuresSupplementairesProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const HeuresSupplementaires = (props: IHeuresSupplementairesProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { heuresSupplementairesList, match, loading } = props;
  return (
    <div>
      <h2 id="heures-supplementaires-heading">
        <Translate contentKey="emnaBackEndApp.heuresSupplementaires.home.title">Heures Supplementaires</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.heuresSupplementaires.home.createLabel">Create new Heures Supplementaires</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {heuresSupplementairesList && heuresSupplementairesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.nombreHeure">Nombre Heure</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.justificatif">Justificatif</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.heuresSupplementaires.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {heuresSupplementairesList.map((heuresSupplementaires, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${heuresSupplementaires.id}`} color="link" size="sm">
                      {heuresSupplementaires.id}
                    </Button>
                  </td>
                  <td>
                    {heuresSupplementaires.date ? (
                      <TextFormat type="date" value={heuresSupplementaires.date} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{heuresSupplementaires.nombreHeure}</td>
                  <td>{heuresSupplementaires.justificatif}</td>
                  <td>
                    {heuresSupplementaires.employeId ? (
                      <Link to={`employe/${heuresSupplementaires.employeId}`}>{heuresSupplementaires.employeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${heuresSupplementaires.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${heuresSupplementaires.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${heuresSupplementaires.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.heuresSupplementaires.home.notFound">No Heures Supplementaires found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ heuresSupplementaires }: IRootState) => ({
  heuresSupplementairesList: heuresSupplementaires.entities,
  loading: heuresSupplementaires.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HeuresSupplementaires);
