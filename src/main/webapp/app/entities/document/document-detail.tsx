import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './document.reducer';
import { IDocument } from 'app/shared/model/document.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDocumentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DocumentDetail = (props: IDocumentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { documentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.document.detail.title">Document</Translate> [<b>{documentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="cheminFichier">
              <Translate contentKey="emnaBackEndApp.document.cheminFichier">Chemin Fichier</Translate>
            </span>
          </dt>
          <dd>{documentEntity.cheminFichier}</dd>
          <dt>
            <span id="nom">
              <Translate contentKey="emnaBackEndApp.document.nom">Nom</Translate>
            </span>
          </dt>
          <dd>{documentEntity.nom}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.typeDocument">Type Document</Translate>
          </dt>
          <dd>{documentEntity.typeDocumentId ? documentEntity.typeDocumentId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.facture">Facture</Translate>
          </dt>
          <dd>{documentEntity.factureId ? documentEntity.factureId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.releve">Releve</Translate>
          </dt>
          <dd>{documentEntity.releveId ? documentEntity.releveId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.contrat">Contrat</Translate>
          </dt>
          <dd>{documentEntity.contratId ? documentEntity.contratId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.employe">Employe</Translate>
          </dt>
          <dd>{documentEntity.employeId ? documentEntity.employeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.depense">Depense</Translate>
          </dt>
          <dd>{documentEntity.depenseId ? documentEntity.depenseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.absence">Absence</Translate>
          </dt>
          <dd>{documentEntity.absenceId ? documentEntity.absenceId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.noteDeFrais">Note De Frais</Translate>
          </dt>
          <dd>{documentEntity.noteDeFraisId ? documentEntity.noteDeFraisId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.autresVariable">Autres Variable</Translate>
          </dt>
          <dd>{documentEntity.autresVariableId ? documentEntity.autresVariableId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.devis">Devis</Translate>
          </dt>
          <dd>{documentEntity.devisId ? documentEntity.devisId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.dpae">Dpae</Translate>
          </dt>
          <dd>{documentEntity.dpaeId ? documentEntity.dpaeId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.fichePaie">Fiche Paie</Translate>
          </dt>
          <dd>{documentEntity.fichePaieId ? documentEntity.fichePaieId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.document.avenant">Avenant</Translate>
          </dt>
          <dd>{documentEntity.avenantId ? documentEntity.avenantId : ''}</dd>
        </dl>
        <Button tag={Link} to="/document" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/document/${documentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ document }: IRootState) => ({
  documentEntity: document.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DocumentDetail);
