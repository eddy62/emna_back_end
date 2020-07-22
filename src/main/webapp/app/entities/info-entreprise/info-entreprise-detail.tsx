import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './info-entreprise.reducer';
import { IInfoEntreprise } from 'app/shared/model/info-entreprise.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInfoEntrepriseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const InfoEntrepriseDetail = (props: IInfoEntrepriseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { infoEntrepriseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.infoEntreprise.detail.title">InfoEntreprise</Translate> [<b>{infoEntrepriseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="raisonSociale">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.raisonSociale">Raison Sociale</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.raisonSociale}</dd>
          <dt>
            <span id="telephone">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.telephone">Telephone</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.telephone}</dd>
          <dt>
            <span id="fax">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.fax">Fax</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.fax}</dd>
          <dt>
            <span id="formeJuridique">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.formeJuridique">Forme Juridique</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.formeJuridique}</dd>
          <dt>
            <span id="dateDeCreation">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.dateDeCreation">Date De Creation</Translate>
            </span>
          </dt>
          <dd>
            {infoEntrepriseEntity.dateDeCreation ? (
              <TextFormat value={infoEntrepriseEntity.dateDeCreation} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="siren">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.siren">Siren</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.siren}</dd>
          <dt>
            <span id="siret">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.siret">Siret</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.siret}</dd>
          <dt>
            <span id="domaineDactivite">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.domaineDactivite">Domaine Dactivite</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.domaineDactivite}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.description">Description</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.description}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="emnaBackEndApp.infoEntreprise.email">Email</Translate>
            </span>
          </dt>
          <dd>{infoEntrepriseEntity.email}</dd>
        </dl>
        <Button tag={Link} to="/info-entreprise" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/info-entreprise/${infoEntrepriseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ infoEntreprise }: IRootState) => ({
  infoEntrepriseEntity: infoEntreprise.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(InfoEntrepriseDetail);
