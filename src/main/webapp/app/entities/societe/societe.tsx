import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './societe.reducer';

export interface ISocieteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Societe = (props: ISocieteProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { societeList, match, loading } = props;
  return (
    <div>
      <h2 id="societe-heading">
        <Translate contentKey="emnaBackEndApp.societe.home.title">Societes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.societe.home.createLabel">Create new Societe</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {societeList && societeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.societe.civilite">Civilite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.societe.infoEntreprise">Info Entreprise</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.societe.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.societe.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.societe.comptable">Comptable</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {societeList.map((societe, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${societe.id}`} color="link" size="sm">
                      {societe.id}
                    </Button>
                  </td>
                  <td>{societe.civilite}</td>
                  <td>
                    {societe.infoEntrepriseId ? (
                      <Link to={`info-entreprise/${societe.infoEntrepriseId}`}>{societe.infoEntrepriseId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{societe.userId ? societe.userId : ''}</td>
                  <td>{societe.adresseId ? <Link to={`adresse/${societe.adresseId}`}>{societe.adresseId}</Link> : ''}</td>
                  <td>{societe.comptableId ? <Link to={`comptable/${societe.comptableId}`}>{societe.comptableId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${societe.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${societe.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${societe.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.societe.home.notFound">No Societes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ societe }: IRootState) => ({
  societeList: societe.entities,
  loading: societe.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Societe);
