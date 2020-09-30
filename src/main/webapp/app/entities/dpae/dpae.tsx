import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './dpae.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IDpaeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Dpae = (props: IDpaeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { dpaeList, match, loading } = props;
  return (
    <div>
      <h2 id="dpae-heading">
        <Translate contentKey="emnaBackEndApp.dpae.home.title">Dpaes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.dpae.home.createLabel">Create new Dpae</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {dpaeList && dpaeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.dpae.lieu">Lieu</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.dpae.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.dpae.employe">Employe</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dpaeList.map((dpae, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dpae.id}`} color="link" size="sm">
                      {dpae.id}
                    </Button>
                  </td>
                  <td>{dpae.lieu}</td>
                  <td>{dpae.date ? <TextFormat type="date" value={dpae.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{dpae.employeId ? <Link to={`employe/${dpae.employeId}`}>{dpae.employeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dpae.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dpae.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dpae.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.dpae.home.notFound">No Dpaes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ dpae }: IRootState) => ({
  dpaeList: dpae.entities,
  loading: dpae.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Dpae);
