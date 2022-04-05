import React, { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

export default function AlertModal(props) {
  return (
      <Modal
        {...props}
        backdrop="static"
        keyboard={false}
      >
        <ModalHeader closeButton>
          Modal title
        </ModalHeader>
        <ModalBody>
          I will not close if you click outside me. Don't even try to press
          escape key.
        </ModalBody>
        <ModalFooter>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
        </ModalFooter>
      </Modal>
  );
}