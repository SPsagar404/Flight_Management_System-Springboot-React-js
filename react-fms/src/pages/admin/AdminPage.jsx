import { useEffect, useRef, useState } from 'react';
import FlightService from '../../services/flight.service';
import { FlightSave } from '../../components/FlightSave';
import { FlightDelete } from '../../components/FlightDelete';
import Flight from '../../models/flight';


const AdminPage = () => {

    const [flightList, setFlightList] = useState([]);
    const [selectedFlight, setSelectedFlight] = useState(new Flight('', '', '','',''));
    const [errorMessage, setErrorMessage] = useState('');

    const saveComponent = useRef();
    const deleteComponent = useRef();

    useEffect(() => {
        FlightService.getAllFlights().then((response) => {
            setFlightList(response.data);
        });
    }, []);

    const createFlightRequest = () => {
        setSelectedFlight(new Flight('', '', ''));
        saveComponent.current?.showFlightModal();
    };

    const editFlightRequest = (item) => {
      setSelectedFlight(Object.assign({}, item));
        saveComponent.current?.showFlightModal();
    };

    const deleteFlightRequest = (flight) => {
        setSelectedFlight(flight);
        //console.log(selectedFlight);
      deleteComponent.current?.showDeleteModal();
    };

    const saveFlightWatcher = (flight) => {
        let itemIndex = flightList.findIndex(item => item.id === flight.id);

        if (itemIndex !== -1) {
            const newList = flightList.map((item) => {
                if (item.id === flight.id) {
                    return flight;
                }
                return item;
            });
            setFlightList(newList);
        } else {
            const newList = flightList.concat(flight);
            setFlightList(newList);
        }
    };

    const deleteFlight = () => {
        //console.log(selectedFlight.flightId);
        FlightService.deleteFlight(selectedFlight).then(_ => {
            setFlightList(flightList.filter(flight => flight.flightId !== selectedFlight.flightId));
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
        });
    };

    return (
        <div>
            <div className="container">
                <div className="pt-5">

                    {errorMessage &&
                    <div className="alert alert-danger">
                        {errorMessage}
                    </div>
                    }

                    <div className="card">
                        <div className="card-header">
                            <div className="row">
                                <div className="col-6">
                                    <h3>All Available Flights</h3>
                                </div>

                                <div className="col-6 text-end">
                                    <button className="btn btn-info" onClick={() => createFlightRequest()}>
                                        Add New Flight
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div className="card-body">
                            <table className="table table-striped">

                                <thead>
                                <tr>
                                    <th scope="col">SR No.</th>
                                    <th scope="col">Flight Number</th>
                                    <th scope="col">Operating Airlines</th>
                                    <th scope="col">Departure City</th>
                                    <th scope="col">Arrival City</th>
                                    <th scope="col">Date Of Departure</th>
                                    <th scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>

                                {flightList.map((item, ind) =>
                                    <tr key={item.id}>
                                        <th scope="row">{ind + 1}</th>
                                        <td>{item.flightNumber}</td>
                                        <td>{item.operatingAirlines}</td>
                                        <td>{item.departureCity}</td>
                                        <td>{item.arrivalCity}</td>
                                        <td>{new Date(item.dateOfDeparture).toLocaleDateString()}</td>
                                        <td>
                                            <button className="btn btn-info me-1" onClick={() => editFlightRequest(item)}>
                                                Edit
                                            </button>
                                            <button className="btn btn-danger" onClick={() => deleteFlightRequest(item)}>
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                )}

                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <FlightSave ref={saveComponent} course={selectedFlight} onSaved={(p) => saveFlightWatcher(p)}></FlightSave>
            <FlightDelete ref={deleteComponent} onConfirmed={() => deleteFlight()}></FlightDelete>
        </div>
    );
};

export {AdminPage};
