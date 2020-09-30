import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './absence.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IAbsenceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Absence = (props: IAbsenceProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { absenceList, match, loading } = props;
  return (
    <div>
      <h2 id="absence-heading">
        <Translate contentKey="emnaBackEndApp.absence.home.title">Absences</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.absence.home.createLabel">Create new Absence</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {absenceList && absenceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.debutAbsence">Debut Absence</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.finAbsence">Fin Absence</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.mois">Mois</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.annee">Annee</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.typeAbsence">Type Absence</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.etatVariablePaie">Etat Variable Paie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.absence.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {absenceList.map((absence, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${absence.id}`} color="link" size="sm">
                      {absence.id}
                    </Button>
                  </td>
                  <td>
                    {absence.debutAbsence ? <TextFormat type="date" value={absence.debutAbsence} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {absence.finAbsence ? <TextFormat type="date" value={absence.finAbsence} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{absence.mois}</td>
                  <td>{absence.annee}</td>
                  <td>{absence.typeAbsenceId ? <Link to={`type-absence/${absence.typeAbsenceId}`}>{absence.typeAbsenceId}</Link> : ''}</td>
                  <td>
                    {absence.etatVariablePaieId ? (
                      <Link to={`etat-variable-paie/${absence.etatVariablePaieId}`}>{absence.etatVariablePaieId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{absence.employeId ? <Link to={`employe/${absence.employeId}`}>{absence.employeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${absence.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${absence.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${absence.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.absence.home.notFound">No Absences found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ absence }: IRootState) => ({
  absenceList: absence.entities,
  loading: absence.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Absence);
