import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './type-document.reducer';

export interface ITypeDocumentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypeDocumentDetail = (props: ITypeDocumentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { typeDocumentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="emnaBackEndApp.typeDocument.detail.title">TypeDocument</Translate> [<b>{typeDocumentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="codeRef">
              <Translate contentKey="emnaBackEndApp.typeDocument.codeRef">Code Ref</Translate>
            </span>
          </dt>
          <dd>{typeDocumentEntity.codeRef}</dd>
          <dt>
            <span id="intitule">
              <Translate contentKey="emnaBackEndApp.typeDocument.intitule">Intitule</Translate>
            </span>
          </dt>
          <dd>{typeDocumentEntity.intitule}</dd>
        </dl>
        <Button tag={Link} to="/type-document" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-document/${typeDocumentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ typeDocument }: IRootState) => ({
  typeDocumentEntity: typeDocument.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypeDocumentDetail);
