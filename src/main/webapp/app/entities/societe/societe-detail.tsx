import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './societe.reducer';
import { ISociete } from 'app/shared/model/societe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISocieteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SocieteDetail = (props: ISocieteDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { societeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.societe.detail.title">Societe</Translate> [<b>{societeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="civilite">
              <Translate contentKey="emnaBackEndApp.societe.civilite">Civilite</Translate>
            </span>
          </dt>
          <dd>{societeEntity.civilite}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.societe.adresse">Adresse</Translate>
          </dt>
          <dd>{societeEntity.adresseId ? societeEntity.adresseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.societe.infoEntreprise">Info Entreprise</Translate>
          </dt>
          <dd>{societeEntity.infoEntrepriseId ? societeEntity.infoEntrepriseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.societe.user">User</Translate>
          </dt>
          <dd>{societeEntity.userId ? societeEntity.userId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.societe.comptable">Comptable</Translate>
          </dt>
          <dd>{societeEntity.comptableId ? societeEntity.comptableId : ''}</dd>
        </dl>
        <Button tag={Link} to="/societe" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/societe/${societeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ societe }: IRootState) => ({
  societeEntity: societe.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SocieteDetail);
