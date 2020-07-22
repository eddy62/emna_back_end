import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './clause.reducer';
import { IClause } from 'app/shared/model/clause.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClauseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Clause = (props: IClauseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { clauseList, match, loading } = props;
  return (
    <div>
      <h2 id="clause-heading">
        <Translate contentKey="emnaBackEndApp.clause.home.title">Clauses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.clause.home.createLabel">Create new Clause</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {clauseList && clauseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.reference">Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.listeContrats">Liste Contrats</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.listeAvenants">Liste Avenants</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.societe">Societe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.clause.article">Article</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clauseList.map((clause, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${clause.id}`} color="link" size="sm">
                      {clause.id}
                    </Button>
                  </td>
                  <td>{clause.reference}</td>
                  <td>{clause.description}</td>
                  <td>
                    {clause.listeContrats
                      ? clause.listeContrats.map((val, j) => (
                          <span key={j}>
                            <Link to={`contrat/${val.id}`}>{val.id}</Link>
                            {j === clause.listeContrats.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {clause.listeAvenants
                      ? clause.listeAvenants.map((val, j) => (
                          <span key={j}>
                            <Link to={`avenant/${val.id}`}>{val.id}</Link>
                            {j === clause.listeAvenants.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>{clause.societeId ? <Link to={`societe/${clause.societeId}`}>{clause.societeId}</Link> : ''}</td>
                  <td>{clause.articleId ? <Link to={`article/${clause.articleId}`}>{clause.articleId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${clause.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${clause.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${clause.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.clause.home.notFound">No Clauses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ clause }: IRootState) => ({
  clauseList: clause.entities,
  loading: clause.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Clause);
