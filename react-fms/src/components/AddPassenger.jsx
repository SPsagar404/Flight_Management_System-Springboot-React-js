import { forwardRef, useEffect, useImperativeHandle, useState } from 'react';
import { Modal } from 'react-bootstrap';
import Flight from '../models/flight';
import flightService from '../services/flight.service';


const AddPassenger = forwardRef((props, ref) => {

    useImperativeHandle(ref, () => ({
        //interaction with parent
        showFlightModal() {
            setTimeout(() => {
                setShow(true);
            }, 0);
        }
    }));

    //send it from parent
    useEffect(() => {
        setCourse(props.course);
    }, [props.course]);

    const [course, setCourse] = useState(new Flight('', '', '','',));
    const [errorMessage, setErrorMessage] = useState('');
    const [submitted, setSubmitted] = useState(false);
    const [show, setShow] = useState(false);

    const saveCourse = (e) => {
        e.preventDefault();

        setSubmitted(true);

        if(!course.flightNumber || !course.operatingAirlines || !course.departureCity || !course.arrivalCity || !course.dateOfDeparture) {
            return;
        }

        flightService.saveFlight(course).then(response => {
            //...
            props.onSaved(response.data);
            setShow(false);
            setSubmitted(false);
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        })
    };

    //<input name="" onChange=(evvent) => handlechange(event)>
    const handleChange = (e) => {
        const {name, value} = e.target;

        setCourse((prevState => {
            return {
                ...prevState,
                [name]: value
            };
        }));
    };

    return (
        <Modal show={show}>
            <form onSubmit={(e) => saveCourse(e)}
            noValidate
            className={submitted ? 'was-validated' : ''}>

                <div className="modal-header">
                    <h5 className="modal-title">Flight Details</h5>
                    <button type="button" className="btn-close" onClick={() => setShow(false)}></button>
                </div>

                <div className="modal-body">

                    {errorMessage &&
                    <div className="alert alert-danger">
                        {errorMessage}
                    </div>
                    }

                    <div className="form-group">
                        <label htmlFor="title">Flight Number :</label>
                        <input
                            type="text"
                            name="flightNumber"
                            placeholder="Flight Number"
                            className="form-control"
                            required
                            value={course.flightNumber}
                            onChange={(e) => handleChange(e)}
                        />
                        <div className="invalid-feedback">
                        Flight Number is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="subtitle">Operating Airlines: </label>
                        <input
                            type="text"
                            name="operatingAirlines"
                            placeholder="Operating Airlines"
                            className="form-control"
                            required
                            value={course.operatingAirlines}
                            onChange={(e) => handleChange(e)}
                        />
                        <div className="invalid-feedback">
                        Operating Airlines is required.
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="subtitle">Departure City: </label>
                        <input
                            type="text"
                            name="departureCity"
                            placeholder="Departure City"
                            className="form-control"
                            required
                            value={course.departureCity}
                            onChange={(e) => handleChange(e)}
                        />
                        <div className="invalid-feedback">
                        Departure City is required.
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="subtitle">Arrival City: </label>
                        <input
                            type="text"
                            name="arrivalCity"
                            placeholder="Arrival City"
                            className="form-control"
                            required
                            value={course.arrivalCity}
                            onChange={(e) => handleChange(e)}
                        />
                        <div className="invalid-feedback">
                        Arrival City is required.
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="subtitle">Date Of Departure: </label>
                        <input
                            type="text"
                            name="dateOfDeparture"
                            placeholder="Date Of Departure"
                            className="form-control"
                            required
                            value={course.dateOfDeparture}
                            onChange={(e) => handleChange(e)}
                        />
                        <div className="invalid-feedback">
                        Date Of Departure is required.
                        </div>
                    </div>

                    

                </div>

                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" onClick={() => setShow(false)}>Close</button>
                    <button type="submit" className="btn btn-primary">Save Changes</button>
                </div>

            </form>
        </Modal>
    );
});

export {AddPassenger};
