import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './employe.reducer';
import {APP_LOCAL_DATE_FORMAT} from 'app/config/constants';

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
            <span id="paysNaissance">
              <Translate contentKey="emnaBackEndApp.employe.paysNaissance">Pays Naissance</Translate>
            </span>
          </dt>
          <dd>{employeEntity.paysNaissance}</dd>
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
            <span id="telephoneFixe">
              <Translate contentKey="emnaBackEndApp.employe.telephoneFixe">Telephone Fixe</Translate>
            </span>
          </dt>
          <dd>{employeEntity.telephoneFixe}</dd>
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
            <span id="salaireBrutMensuel">
              <Translate contentKey="emnaBackEndApp.employe.salaireBrutMensuel">Salaire Brut Mensuel</Translate>
            </span>
          </dt>
          <dd>{employeEntity.salaireBrutMensuel}</dd>
          <dt>
            <span id="nbHeureMensuelle">
              <Translate contentKey="emnaBackEndApp.employe.nbHeureMensuelle">Nb Heure Mensuelle</Translate>
            </span>
          </dt>
          <dd>{employeEntity.nbHeureMensuelle}</dd>
          <dt>
            <span id="categorie">
              <Translate contentKey="emnaBackEndApp.employe.categorie">Categorie</Translate>
            </span>
          </dt>
          <dd>{employeEntity.categorie}</dd>
          <dt>
            <span id="poste">
              <Translate contentKey="emnaBackEndApp.employe.poste">Poste</Translate>
            </span>
          </dt>
          <dd>{employeEntity.poste}</dd>
          <dt>
            <span id="dateEmbauche">
              <Translate contentKey="emnaBackEndApp.employe.dateEmbauche">Date Embauche</Translate>
            </span>
          </dt>
          <dd>
            {employeEntity.dateEmbauche ? (
              <TextFormat value={employeEntity.dateEmbauche} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateSortie">
              <Translate contentKey="emnaBackEndApp.employe.dateSortie">Date Sortie</Translate>
            </span>
          </dt>
          <dd>
            {employeEntity.dateSortie ? <TextFormat value={employeEntity.dateSortie} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="periodeEssai">
              <Translate contentKey="emnaBackEndApp.employe.periodeEssai">Periode Essai</Translate>
            </span>
          </dt>
          <dd>{employeEntity.periodeEssai}</dd>
          <dt>
            <span id="situationFamiliale">
              <Translate contentKey="emnaBackEndApp.employe.situationFamiliale">Situation Familiale</Translate>
            </span>
          </dt>
          <dd>{employeEntity.situationFamiliale}</dd>
          <dt>
            <span id="nbEnfantACharge">
              <Translate contentKey="emnaBackEndApp.employe.nbEnfantACharge">Nb Enfant A Charge</Translate>
            </span>
          </dt>
          <dd>{employeEntity.nbEnfantACharge}</dd>
          <dt>
            <Translate contentKey="emnaBackEndApp.employe.statutEmploye">Statut Employe</Translate>
          </dt>
          <dd>{employeEntity.statutEmployeId ? employeEntity.statutEmployeId : ''}</dd>
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
