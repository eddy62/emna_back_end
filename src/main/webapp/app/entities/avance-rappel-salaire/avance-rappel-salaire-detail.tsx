import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './avance-rappel-salaire.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IAvanceRappelSalaireDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvanceRappelSalaireDetail = (props: IAvanceRappelSalaireDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { avanceRappelSalaireEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.detail.title">AvanceRappelSalaire</Translate> [
          <b>{avanceRappelSalaireEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="type">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.type">Type</Translate>
            </span>
          </dt>
          <dd>{avanceRappelSalaireEntity.type}</dd>
          <dt>
            <span id="debutPeriode">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.debutPeriode">Debut Periode</Translate>
            </span>
          </dt>
          <dd>
            {avanceRappelSalaireEntity.debutPeriode ? (
              <TextFormat value={avanceRappelSalaireEntity.debutPeriode} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="finPeriode">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.finPeriode">Fin Periode</Translate>
            </span>
          </dt>
          <dd>
            {avanceRappelSalaireEntity.finPeriode ? (
              <TextFormat value={avanceRappelSalaireEntity.finPeriode} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="montant">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.montant">Montant</Translate>
            </span>
          </dt>
          <dd>{avanceRappelSalaireEntity.montant}</dd>
          <dt>
            <span id="mois">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.mois">Mois</Translate>
            </span>
          </dt>
          <dd>{avanceRappelSalaireEntity.mois}</dd>
          <dt>
            <span id="annee">
              <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.annee">Annee</Translate>
            </span>
          </dt>
          <dd>{avanceRappelSalaireEntity.annee}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.etatVariablePaie">Etat Variable Paie</Translate>
          </dt>
          <dd>{avanceRappelSalaireEntity.etatVariablePaieId ? avanceRappelSalaireEntity.etatVariablePaieId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.employe">Employe</Translate>
          </dt>
          <dd>{avanceRappelSalaireEntity.employeId ? avanceRappelSalaireEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/avance-rappel-salaire" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/avance-rappel-salaire/${avanceRappelSalaireEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ avanceRappelSalaire }: IRootState) => ({
  avanceRappelSalaireEntity: avanceRappelSalaire.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvanceRappelSalaireDetail);
