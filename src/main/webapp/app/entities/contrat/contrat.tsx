import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './contrat.reducer';
import { IContrat } from 'app/shared/model/contrat.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContratProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Contrat = (props: IContratProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { contratList, match, loading } = props;
  return (
    <div>
      <h2 id="contrat-heading">
        <Translate contentKey="emnaBackEndApp.contrat.home.title">Contrats</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.contrat.home.createLabel">Create new Contrat</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {contratList && contratList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.titre">Titre</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.dateCreation">Date Creation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.signe">Signe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.archive">Archive</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.employe">Employe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.contrat.societe">Societe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contratList.map((contrat, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${contrat.id}`} color="link" size="sm">
                      {contrat.id}
                    </Button>
                  </td>
                  <td>{contrat.titre}</td>
                  <td>
                    <TextFormat type="date" value={contrat.dateCreation} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{contrat.signe ? 'true' : 'false'}</td>
                  <td>{contrat.archive ? 'true' : 'false'}</td>
                  <td>{contrat.employeId ? <Link to={`employe/${contrat.employeId}`}>{contrat.employeId}</Link> : ''}</td>
                  <td>{contrat.societeId ? <Link to={`societe/${contrat.societeId}`}>{contrat.societeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contrat.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contrat.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contrat.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.contrat.home.notFound">No Contrats found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ contrat }: IRootState) => ({
  contratList: contrat.entities,
  loading: contrat.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Contrat);
