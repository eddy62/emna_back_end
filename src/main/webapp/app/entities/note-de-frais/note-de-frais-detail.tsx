import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './note-de-frais.reducer';
import { INoteDeFrais } from 'app/shared/model/note-de-frais.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INoteDeFraisDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NoteDeFraisDetail = (props: INoteDeFraisDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { noteDeFraisEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.noteDeFrais.detail.title">NoteDeFrais</Translate> [<b>{noteDeFraisEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="designation">
              <Translate contentKey="emnaBackEndApp.noteDeFrais.designation">Designation</Translate>
            </span>
          </dt>
          <dd>{noteDeFraisEntity.designation}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.noteDeFrais.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {noteDeFraisEntity.date ? <TextFormat value={noteDeFraisEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="montant">
              <Translate contentKey="emnaBackEndApp.noteDeFrais.montant">Montant</Translate>
            </span>
          </dt>
          <dd>{noteDeFraisEntity.montant}</dd>
          <dt>
            <span id="justificatif">
              <Translate contentKey="emnaBackEndApp.noteDeFrais.justificatif">Justificatif</Translate>
            </span>
          </dt>
          <dd>{noteDeFraisEntity.justificatif}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.noteDeFrais.etatVariablePaie">Etat Variable Paie</Translate>
          </dt>
          <dd>{noteDeFraisEntity.etatVariablePaieId ? noteDeFraisEntity.etatVariablePaieId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.noteDeFrais.employe">Employe</Translate>
          </dt>
          <dd>{noteDeFraisEntity.employeId ? noteDeFraisEntity.employeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/note-de-frais" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/note-de-frais/${noteDeFraisEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ noteDeFrais }: IRootState) => ({
  noteDeFraisEntity: noteDeFrais.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NoteDeFraisDetail);
