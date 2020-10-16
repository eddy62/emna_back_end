import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './dpae.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

export interface IDpaeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DpaeDetail = (props: IDpaeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { dpaeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.dpae.detail.title">Dpae</Translate> [<b>{dpaeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="lieu">
              <Translate contentKey="emnaBackEndApp.dpae.lieu">Lieu</Translate>
            </span>
          </dt>
          <dd>{dpaeEntity.lieu}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.dpae.date">Date</Translate>
            </span>
          </dt>
          <dd>{dpaeEntity.date ? <TextFormat value={dpaeEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="heureEmbauche">
              <Translate contentKey="emnaBackEndApp.dpae.heureEmbauche">Heure Embauche</Translate>
            </span>
          </dt>
          <dd>{dpaeEntity.heureEmbauche}</dd>
          <dt>
            <span id="commentaire">
              <Translate contentKey="emnaBackEndApp.dpae.commentaire">Commentaire</Translate>
            </span>
          </dt>
          <dd>{dpaeEntity.commentaire}</dd>
          <dt>
            <span id="retourApiUrssaf">
              <Translate contentKey="emnaBackEndApp.dpae.retourApiUrssaf">Retour Api Urssaf</Translate>
            </span>
          </dt>
          <dd>{dpaeEntity.retourApiUrssaf}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.dpae.contrat">Contrat</Translate>
          </dt>
          <dd>{dpaeEntity.contratId ? dpaeEntity.contratId : ''}</dd>
        </dl>
        <Button tag={Link} to="/dpae" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/dpae/${dpaeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ dpae }: IRootState) => ({
  dpaeEntity: dpae.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DpaeDetail);
