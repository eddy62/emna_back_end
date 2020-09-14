import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Employe = (props: IEmployeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { employeList, match, loading } = props;
  return (
    <div>
      <h2 id="employe-heading">
        <Translate contentKey="emnaBackEndApp.employe.home.title">Employes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.employe.home.createLabel">Create new Employe</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {employeList && employeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.matricule">Matricule</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.civilite">Civilite</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.nomNaissance">Nom Naissance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.nomUsage">Nom Usage</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.prenom">Prenom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.dateNaissance">Date Naissance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.villeNaissance">Ville Naissance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.departementNaissance">Departement Naissance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.paysNaisance">Pays Naisance</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.numeroSecuriteSociale">Numero Securite Sociale</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.telephoneFix">Telephone Fix</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.telephonePortable">Telephone Portable</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.fax">Fax</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.salaireHoraire">Salaire Horaire</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.salaireBrutMensuelle">Salaire Brut Mensuelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.heuresMensuelle">Heures Mensuelle</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.categorie">Categorie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.poste">Poste</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.dateEmbauche">Date Embauche</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.dateSortie">Date Sortie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.periodeEssai">Periode Essai</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.situationFamiliale">Situation Familiale</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.enfantsACharge">Enfants A Charge</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.statutEmploye">Statut Employe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.adresse">Adresse</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.typeContrat">Type Contrat</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.employe.societe">Societe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {employeList.map((employe, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${employe.id}`} color="link" size="sm">
                      {employe.id}
                    </Button>
                  </td>
                  <td>{employe.matricule}</td>
                  <td>{employe.civilite}</td>
                  <td>{employe.nomNaissance}</td>
                  <td>{employe.nomUsage}</td>
                  <td>{employe.prenom}</td>
                  <td>
                    {employe.dateNaissance ? <TextFormat type="date" value={employe.dateNaissance} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{employe.villeNaissance}</td>
                  <td>{employe.departementNaissance}</td>
                  <td>{employe.paysNaisance}</td>
                  <td>{employe.numeroSecuriteSociale}</td>
                  <td>{employe.email}</td>
                  <td>{employe.telephoneFix}</td>
                  <td>{employe.telephonePortable}</td>
                  <td>{employe.fax}</td>
                  <td>{employe.salaireHoraire}</td>
                  <td>{employe.salaireBrutMensuelle}</td>
                  <td>{employe.heuresMensuelle}</td>
                  <td>{employe.categorie}</td>
                  <td>{employe.poste}</td>
                  <td>
                    {employe.dateEmbauche ? <TextFormat type="date" value={employe.dateEmbauche} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {employe.dateSortie ? <TextFormat type="date" value={employe.dateSortie} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{employe.periodeEssai}</td>
                  <td>{employe.situationFamiliale}</td>
                  <td>{employe.enfantsACharge}</td>
                  <td>
                    {employe.statutEmployeId ? <Link to={`statut-employe/${employe.statutEmployeId}`}>{employe.statutEmployeId}</Link> : ''}
                  </td>
                  <td>{employe.adresseId ? <Link to={`adresse/${employe.adresseId}`}>{employe.adresseId}</Link> : ''}</td>
                  <td>{employe.typeContratId ? <Link to={`type-contrat/${employe.typeContratId}`}>{employe.typeContratId}</Link> : ''}</td>
                  <td>{employe.societeId ? <Link to={`societe/${employe.societeId}`}>{employe.societeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${employe.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${employe.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${employe.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.employe.home.notFound">No Employes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ employe }: IRootState) => ({
  employeList: employe.entities,
  loading: employe.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Employe);
