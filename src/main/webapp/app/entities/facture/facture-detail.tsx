import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './facture.reducer';
import { IFacture } from 'app/shared/model/facture.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFactureDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FactureDetail = (props: IFactureDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { factureEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.facture.detail.title">Facture</Translate> [<b>{factureEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="numfact">
              <Translate contentKey="emnaBackEndApp.facture.numfact">Numfact</Translate>
            </span>
          </dt>
          <dd>{factureEntity.numfact}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="emnaBackEndApp.facture.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{factureEntity.nom}</dd>
          <dt>
            <span id="message">
              <Translate contentKey="emnaBackEndApp.facture.message">Message</Translate>
            </span>
          </dt>
          <dd>{factureEntity.message}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="emnaBackEndApp.facture.date">Date</Translate>
            </span>
          </dt>
          <dd>{factureEntity.date ? <TextFormat value={factureEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="dateEcheance">
              <Translate contentKey="emnaBackEndApp.facture.dateEcheance">Date Echeance</Translate>
            </span>
          </dt>
          <dd>
            {factureEntity.dateEcheance ? (
              <TextFormat value={factureEntity.dateEcheance} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="prixHT">
              <Translate contentKey="emnaBackEndApp.facture.prixHT">Prix HT</Translate>
            </span>
          </dt>
          <dd>{factureEntity.prixHT}</dd>
          <dt>
            <span id="prixTTC">
              <Translate contentKey="emnaBackEndApp.facture.prixTTC">Prix TTC</Translate>
            </span>
          </dt>
          <dd>{factureEntity.prixTTC}</dd>
          <dt>
            <span id="tva">
              <Translate contentKey="emnaBackEndApp.facture.tva">Tva</Translate>
            </span>
          </dt>
          <dd>{factureEntity.tva}</dd>
          <dt>
            <span id="fichier">
              <Translate contentKey="emnaBackEndApp.facture.fichier">Fichier</Translate>
            </span>
          </dt>
          <dd>{factureEntity.fichier}</dd>
          <dt>
            <span id="cheminFichier">
              <Translate contentKey="emnaBackEndApp.facture.cheminFichier">Chemin Fichier</Translate>
            </span>
          </dt>
          <dd>{factureEntity.cheminFichier}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="emnaBackEndApp.facture.type">Type</Translate>
            </span>
          </dt>
          <dd>{factureEntity.type}</dd>
          <dt>
            <span id="moyenDePaiement">
              <Translate contentKey="emnaBackEndApp.facture.moyenDePaiement">Moyen De Paiement</Translate>
            </span>
          </dt>
          <dd>{factureEntity.moyenDePaiement}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.facture.adresse">Adresse</Translate>
          </dt>
          <dd>{factureEntity.adresseId ? factureEntity.adresseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.facture.etatFacture">Etat Facture</Translate>
          </dt>
          <dd>{factureEntity.etatFactureId ? factureEntity.etatFactureId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.facture.societe">Societe</Translate>
          </dt>
          <dd>{factureEntity.societeId ? factureEntity.societeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.facture.operation">Operation</Translate>
          </dt>
          <dd>{factureEntity.operationId ? factureEntity.operationId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.facture.clientFournisseur">Client Fournisseur</Translate>
          </dt>
          <dd>{factureEntity.clientFournisseurId ? factureEntity.clientFournisseurId : ''}</dd>
        </dl>
        <Button tag={Link} to="/facture" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/facture/${factureEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ facture }: IRootState) => ({
  factureEntity: facture.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FactureDetail);
