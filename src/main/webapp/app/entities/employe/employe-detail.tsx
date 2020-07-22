import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employe.reducer';
import { IEmploye } from 'app/shared/model/employe.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EmployeDetail = (props: IEmployeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { employeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.employe.detail.title">Employe</Translate> [<b>{employeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="matricule">
              <Translate contentKey="emnaBackEndApp.employe.matricule">Matricule</Translate>
            </span>
          </dt>
          <dd>{employeEntity.matricule}</dd>
          <dt>
            <span id="civilite">
              <Translate contentKey="emnaBackEndApp.employe.civilite">Civilite</Translate>
            </span>
          </dt>
          <dd>{employeEntity.civilite}</dd>
          <dt>
            <span id="nomNaissance">
              <Translate contentKey="emnaBackEndApp.employe.nomNaissance">Nom Naissance</Translate>
            </span>
          </dt>
          <dd>{employeEntity.nomNaissance}</dd>
          <dt>
            <span id="nomUsage">
              <Translate contentKey="emnaBackEndApp.employe.nomUsage">Nom Usage</Translate>
            </span>
          </dt>
          <dd>{employeEntity.nomUsage}</dd>
          <dt>
            <span id="prenom">
              <Translate contentKey="emnaBackEndApp.employe.prenom">Prenom</Translate>
            </span>
          </dt>
          <dd>{employeEntity.prenom}</dd>
          <dt>
            <span id="dateNaissance">
              <Translate contentKey="emnaBackEndApp.employe.dateNaissance">Date Naissance</Translate>
            </span>
          </dt>
          <dd>
            {employeEntity.dateNaissance ? (
              <TextFormat value={employeEntity.dateNaissance} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="villeNaissance">
              <Translate contentKey="emnaBackEndApp.employe.villeNaissance">Ville Naissance</Translate>
            </span>
          </dt>
          <dd>{employeEntity.villeNaissance}</dd>
          <dt>
            <span id="departementNaissance">
              <Translate contentKey="emnaBackEndApp.employe.departementNaissance">Departement Naissance</Translate>
            </span>
          </dt>
          <dd>{employeEntity.departementNaissance}</dd>
          <dt>
            <span id="paysNaisance">
              <Translate contentKey="emnaBackEndApp.employe.paysNaisance">Pays Naisance</Translate>
            </span>
          </dt>
          <dd>{employeEntity.paysNaisance}</dd>
          <dt>
            <span id="numeroSecuriteSociale">
              <Translate contentKey="emnaBackEndApp.employe.numeroSecuriteSociale">Numero Securite Sociale</Translate>
            </span>
          </dt>
          <dd>{employeEntity.numeroSecuriteSociale}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="emnaBackEndApp.employe.email">Email</Translate>
            </span>
          </dt>
          <dd>{employeEntity.email}</dd>
          <dt>
            <span id="telephoneFix">
              <Translate contentKey="emnaBackEndApp.employe.telephoneFix">Telephone Fix</Translate>
            </span>
          </dt>
          <dd>{employeEntity.telephoneFix}</dd>
          <dt>
            <span id="telephonePortable">
              <Translate contentKey="emnaBackEndApp.employe.telephonePortable">Telephone Portable</Translate>
            </span>
          </dt>
          <dd>{employeEntity.telephonePortable}</dd>
          <dt>
            <span id="fax">
              <Translate contentKey="emnaBackEndApp.employe.fax">Fax</Translate>
            </span>
          </dt>
          <dd>{employeEntity.fax}</dd>
          <dt>
            <span id="salaireHoraire">
              <Translate contentKey="emnaBackEndApp.employe.salaireHoraire">Salaire Horaire</Translate>
            </span>
          </dt>
          <dd>{employeEntity.salaireHoraire}</dd>
          <dt>
            <span id="salaireBrutMensuelle">
              <Translate contentKey="emnaBackEndApp.employe.salaireBrutMensuelle">Salaire Brut Mensuelle</Translate>
            </span>
          </dt>
          <dd>{employeEntity.salaireBrutMensuelle}</dd>
          <dt>
            <span id="heuresMensuelle">
              <Translate contentKey="emnaBackEndApp.employe.heuresMensuelle">Heures Mensuelle</Translate>
            </span>
          </dt>
          <dd>{employeEntity.heuresMensuelle}</dd>
          <dt>
            <span id="categorie">
              <Translate contentKey="emnaBackEndApp.employe.categorie">Categorie</Translate>
            </span>
          </dt>
          <dd>{employeEntity.categorie}</dd>
          <dt>
            <span id="statut">
              <Translate contentKey="emnaBackEndApp.employe.statut">Statut</Translate>
            </span>
          </dt>
          <dd>{employeEntity.statut}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.employe.adresse">Adresse</Translate>
          </dt>
          <dd>{employeEntity.adresseId ? employeEntity.adresseId : ''}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.employe.societe">Societe</Translate>
          </dt>
          <dd>{employeEntity.societeId ? employeEntity.societeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/employe" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/employe/${employeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ employe }: IRootState) => ({
  employeEntity: employe.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EmployeDetail);
