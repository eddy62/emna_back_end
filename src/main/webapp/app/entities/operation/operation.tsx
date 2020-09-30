import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './operation.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IOperationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Operation = (props: IOperationProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { operationList, match, loading } = props;
  return (
    <div>
      <h2 id="operation-heading">
        <Translate contentKey="emnaBackEndApp.operation.home.title">Operations</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.operation.home.createLabel">Create new Operation</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {operationList && operationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.rapproche">Rapproche</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.solde">Solde</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.operation.releve">Releve</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {operationList.map((operation, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${operation.id}`} color="link" size="sm">
                      {operation.id}
                    </Button>
                  </td>
                  <td>{operation.date ? <TextFormat type="date" value={operation.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{operation.description}</td>
                  <td>{operation.type}</td>
                  <td>{operation.rapproche ? 'true' : 'false'}</td>
                  <td>{operation.solde}</td>
                  <td>{operation.releveId ? <Link to={`releve/${operation.releveId}`}>{operation.releveId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${operation.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${operation.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${operation.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="emnaBackEndApp.operation.home.notFound">No Operations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ operation }: IRootState) => ({
  operationList: operation.entities,
  loading: operation.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Operation);
