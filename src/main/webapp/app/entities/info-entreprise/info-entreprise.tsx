import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './info-entreprise.reducer';
import { IInfoEntreprise } from 'app/shared/model/info-entreprise.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInfoEntrepriseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const InfoEntreprise = (props: IInfoEntrepriseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { infoEntrepriseList, match, loading } = props;
  return (
    <div>
      <h2 id="info-entreprise-heading">
        <Translate contentKey="emnaBackEndApp.infoEntreprise.home.title">Info Entreprises</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.infoEntreprise.home.createLabel">Create new Info Entreprise</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {infoEntrepriseList && infoEntrepriseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.raisonSociale">Raison Sociale</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.telephone">Telephone</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.fax">Fax</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.formeJuridique">Forme Juridique</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.dateDeCreation">Date De Creation</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.siren">Siren</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.siret">Siret</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.domaineDactivite">Domaine Dactivite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.codeUrssaf">Code Urssaf</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.infoEntreprise.serviceSanteTravail">Service Sante Travail</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {infoEntrepriseList.map((infoEntreprise, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${infoEntreprise.id}`} color="link" size="sm">
                      {infoEntreprise.id}
                    </Button>
                  </td>
                  <td>{infoEntreprise.raisonSociale}</td>
                  <td>{infoEntreprise.telephone}</td>
                  <td>{infoEntreprise.fax}</td>
                  <td>{infoEntreprise.formeJuridique}</td>
                  <td>
                    {infoEntreprise.dateDeCreation ? (
                      <TextFormat type="date" value={infoEntreprise.dateDeCreation} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{infoEntreprise.siren}</td>
                  <td>{infoEntreprise.siret}</td>
                  <td>{infoEntreprise.domaineDactivite}</td>
                  <td>{infoEntreprise.description}</td>
                  <td>{infoEntreprise.email}</td>
                  <td>{infoEntreprise.codeUrssaf}</td>
                  <td>{infoEntreprise.serviceSanteTravail}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${infoEntreprise.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${infoEntreprise.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${infoEntreprise.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.infoEntreprise.home.notFound">No Info Entreprises found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ infoEntreprise }: IRootState) => ({
  infoEntrepriseList: infoEntreprise.entities,
  loading: infoEntreprise.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InfoEntreprise);
