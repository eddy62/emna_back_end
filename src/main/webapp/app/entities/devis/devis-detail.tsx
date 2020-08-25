import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './devis.reducer';
import { IDevis } from 'app/shared/model/devis.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDevisDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DevisDetail = (props: IDevisDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { devisEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.devis.detail.title">Devis</Translate> [<b>{devisEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="numDevis">
              <Translate contentKey="emnaBackEndApp.devis.numDevis">Num Devis</Translate>
            </span>
          </dt>
          <dd>{devisEntity.numDevis}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="emnaBackEndApp.devis.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{devisEntity.nom}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="emnaBackEndApp.devis.message">Message</Translate>
            </span>
          </dt>
          <dd>{devisEntity.message}</dd>
          <dt>
            <span id="dateCreation">
              <Translate contentKey="emnaBackEndApp.devis.dateCreation">Date Creation</Translate>
            </span>
          </dt>
          <dd>
            {devisEntity.dateCreation ? <TextFormat value={devisEntity.dateCreation} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dateLimite">
              <Translate contentKey="emnaBackEndApp.devis.dateLimite">Date Limite</Translate>
            </span>
          </dt>
          <dd>
            {devisEntity.dateLimite ? <TextFormat value={devisEntity.dateLimite} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="prixHT">
              <Translate contentKey="emnaBackEndApp.devis.prixHT">Prix HT</Translate>
            </span>
          </dt>
          <dd>{devisEntity.prixHT}</dd>
          <dt>
            <span id="prixTTC">
              <Translate contentKey="emnaBackEndApp.devis.prixTTC">Prix TTC</Translate>
            </span>
          </dt>
          <dd>{devisEntity.prixTTC}</dd>
          <dt>
            <span id="tva">
              <Translate contentKey="emnaBackEndApp.devis.tva">Tva</Translate>
            </span>
          </dt>
          <dd>{devisEntity.tva}</dd>
          <dt>
            <span id="cheminFichier">
              <Translate contentKey="emnaBackEndApp.devis.cheminFichier">Chemin Fichier</Translate>
            </span>
          </dt>
          <dd>{devisEntity.cheminFichier}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.devis.etatDevis">Etat Devis</Translate>
          </dt>
          <dd>{devisEntity.etatDevisId ? devisEntity.etatDevisId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.devis.societe">Societe</Translate>
          </dt>
          <dd>{devisEntity.societeId ? devisEntity.societeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.devis.clientFournisseur">Client Fournisseur</Translate>
          </dt>
          <dd>{devisEntity.clientFournisseurId ? devisEntity.clientFournisseurId : ''}</dd>
        </dl>
        <Button tag={Link} to="/devis" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/devis/${devisEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ devis }: IRootState) => ({
  devisEntity: devis.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DevisDetail);
