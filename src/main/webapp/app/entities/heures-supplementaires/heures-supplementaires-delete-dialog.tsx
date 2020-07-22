import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IHeuresSupplementaires } from 'app/shared/model/heures-supplementaires.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './heures-supplementaires.reducer';

export interface IHeuresSupplementairesDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HeuresSupplementairesDeleteDialog = (props: IHeuresSupplementairesDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/heures-supplementaires');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.heuresSupplementairesEntity.id);
  };

  const { heuresSupplementairesEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="emnaBackEndApp.heuresSupplementaires.delete.question">
        <Translate contentKey="emnaBackEndApp.heuresSupplementaires.delete.question" interpolate={{ id: heuresSupplementairesEntity.id }}>
          Are you sure you want to delete this HeuresSupplementaires?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-heuresSupplementaires" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ heuresSupplementaires }: IRootState) => ({
  heuresSupplementairesEntity: heuresSupplementaires.entity,
  updateSuccess: heuresSupplementaires.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HeuresSupplementairesDeleteDialog);
