import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Table} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities} from './document.reducer';

export interface IDocumentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Document = (props: IDocumentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { documentList, match, loading } = props;
  return (
    <div>
      <h2 id="document-heading">
        <Translate contentKey="emnaBackEndApp.document.home.title">Documents</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="emnaBackEndApp.document.home.createLabel">Create new Document</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {documentList && documentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.cheminFichier">Chemin Fichier</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.nom">Nom</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.typeDocument">Type Document</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.facture">Facture</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.releve">Releve</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.contrat">Contrat</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.employe">Employe</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.depense">Depense</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.absence">Absence</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.noteDeFrais">Note De Frais</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.autresVariable">Autres Variable</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.devis">Devis</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.dpae">Dpae</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.fichePaie">Fiche Paie</Translate>
                </th>
                <th>
                  <Translate contentKey="emnaBackEndApp.document.avenant">Avenant</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {documentList.map((document, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${document.id}`} color="link" size="sm">
                      {document.id}
                    </Button>
                  </td>
                  <td>{document.cheminFichier}</td>
                  <td>{document.type}</td>
                  <td>{document.nom}</td>
                  <td>
                    {document.typeDocumentId ? <Link to={`type-document/${document.typeDocumentId}`}>{document.typeDocumentId}</Link> : ''}
                  </td>
                  <td>{document.factureId ? <Link to={`facture/${document.factureId}`}>{document.factureId}</Link> : ''}</td>
                  <td>{document.releveId ? <Link to={`releve/${document.releveId}`}>{document.releveId}</Link> : ''}</td>
                  <td>{document.contratId ? <Link to={`contrat/${document.contratId}`}>{document.contratId}</Link> : ''}</td>
                  <td>{document.employeId ? <Link to={`employe/${document.employeId}`}>{document.employeId}</Link> : ''}</td>
                  <td>{document.depenseId ? <Link to={`depense/${document.depenseId}`}>{document.depenseId}</Link> : ''}</td>
                  <td>{document.absenceId ? <Link to={`absence/${document.absenceId}`}>{document.absenceId}</Link> : ''}</td>
                  <td>
                    {document.noteDeFraisId ? <Link to={`note-de-frais/${document.noteDeFraisId}`}>{document.noteDeFraisId}</Link> : ''}
                  </td>
                  <td>
                    {document.autresVariableId ? (
                      <Link to={`autres-variable/${document.autresVariableId}`}>{document.autresVariableId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{document.devisId ? <Link to={`devis/${document.devisId}`}>{document.devisId}</Link> : ''}</td>
                  <td>{document.dpaeId ? <Link to={`dpae/${document.dpaeId}`}>{document.dpaeId}</Link> : ''}</td>
                  <td>{document.fichePaieId ? <Link to={`fiche-paie/${document.fichePaieId}`}>{document.fichePaieId}</Link> : ''}</td>
                  <td>{document.avenantId ? <Link to={`avenant/${document.avenantId}`}>{document.avenantId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${document.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${document.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${document.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="emnaBackEndApp.document.home.notFound">No Documents found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ document }: IRootState) => ({
  documentList: document.entities,
  loading: document.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Document);
