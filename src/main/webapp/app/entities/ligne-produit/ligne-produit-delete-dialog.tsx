import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILigneProduit } from 'app/shared/model/ligne-produit.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './ligne-produit.reducer';

export interface ILigneProduitDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LigneProduitDeleteDialog = (props: ILigneProduitDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/ligne-produit');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.ligneProduitEntity.id);
  };

  const { ligneProduitEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="emnaBackEndApp.ligneProduit.delete.question">
        <Translate contentKey="emnaBackEndApp.ligneProduit.delete.question" interpolate={{ id: ligneProduitEntity.id }}>
          Are you sure you want to delete this LigneProduit?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-ligneProduit" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ ligneProduit }: IRootState) => ({
  ligneProduitEntity: ligneProduit.entity,
  updateSuccess: ligneProduit.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LigneProduitDeleteDialog);
