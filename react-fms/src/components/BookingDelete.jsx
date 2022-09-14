import { forwardRef, useImperativeHandle, useState } from 'react';
import { Modal } from 'react-bootstrap';


const BookingDelete = forwardRef((props, ref) => {

    const [show, setShow] = useState(false);

    useImperativeHandle(ref, () => ({
        showDeleteModal() {
            setShow(true);
        }
    }));

    const deleteBooking = () => {
        props.onConfirmed();
        setShow(false);
    };

    return (
        <Modal show={show}>

            <div className="modal-header">
                <h5 className="modal-title">Confirmation</h5>
                <button type="button" className="btn-close" onClick={() => setShow(false)}></button>
            </div>

            <div className="modal-body">
                Are you sure to cancel the selected booking?
            </div>

            <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={() => setShow(false)}>Cancel</button>
                <button type="button" className="btn btn-danger" onClick={() => deleteBooking()}>I'm sure!</button>
            </div>

        </Modal>
    );
});

export {BookingDelete};