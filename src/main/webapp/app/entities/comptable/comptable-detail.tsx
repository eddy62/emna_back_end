import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './comptable.reducer';
import { IComptable } from 'app/shared/model/comptable.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IComptableDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ComptableDetail = (props: IComptableDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { comptableEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.comptable.detail.title">Comptable</Translate> [<b>{comptableEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="civilite">
              <Translate contentKey="emnaBackEndApp.comptable.civilite">Civilite</Translate>
            </span>
          </dt>
          <dd>{comptableEntity.civilite}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.comptable.adresse">Adresse</Translate>
          </dt>
          <dd>{comptableEntity.adresseId ? comptableEntity.adresseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.comptable.infoEntreprise">Info Entreprise</Translate>
          </dt>
          <dd>{comptableEntity.infoEntrepriseId ? comptableEntity.infoEntrepriseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.comptable.user">User</Translate>
          </dt>
          <dd>{comptableEntity.userId ? comptableEntity.userId : ''}</dd>
        </dl>
        <Button tag={Link} to="/comptable" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/comptable/${comptableEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ comptable }: IRootState) => ({
  comptableEntity: comptable.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ComptableDetail);
