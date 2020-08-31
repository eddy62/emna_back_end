import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IAvanceRappelSalaire } from 'app/shared/model/avance-rappel-salaire.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './avance-rappel-salaire.reducer';

export interface IAvanceRappelSalaireDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AvanceRappelSalaireDeleteDialog = (props: IAvanceRappelSalaireDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/avance-rappel-salaire');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.avanceRappelSalaireEntity.id);
  };

  const { avanceRappelSalaireEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="emnaBackEndApp.avanceRappelSalaire.delete.question">
        <Translate contentKey="emnaBackEndApp.avanceRappelSalaire.delete.question" interpolate={{ id: avanceRappelSalaireEntity.id }}>
          Are you sure you want to delete this AvanceRappelSalaire?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-avanceRappelSalaire" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ avanceRappelSalaire }: IRootState) => ({
  avanceRappelSalaireEntity: avanceRappelSalaire.entity,
  updateSuccess: avanceRappelSalaire.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AvanceRappelSalaireDeleteDialog);
