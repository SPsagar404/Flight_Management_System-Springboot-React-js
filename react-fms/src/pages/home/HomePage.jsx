import { useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import './HomePage.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserGraduate } from '@fortawesome/free-solid-svg-icons';
import flightService from '../../services/flight.service';
import bookingService from '../../services/booking.service';
import Booking from '../../models/booking';
import { AddPassenger } from '../../components/AddPassenger';



const HomePage = () => {

  const [flightList, setFlightList] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const [infoMessage, setInfoMessage] = useState('');

  const currentUser = useSelector(state => state.user);

  const saveComponent = useRef();


  useEffect(() => {
    flightService.getAllFlights().then((response) => {
      setFlightList(response.data);
    });
  }, []);

  
  const booking = (flight) => {
    if (!currentUser?.id) {
      setErrorMessage(alert('You should login to book a flight.'));
      return;
    }
    const booking = new Booking(currentUser.id, flightList.id, flightList.flightNumber,flightList.departureCity,flightList.arrivalCity, flightList.dateOfDeparture);
    booking.userId=currentUser.id;
    
    booking.flight=flight;
    console.log(booking.flight); 
    bookingService.saveBooking(booking).then(() => {
      setInfoMessage(alert('Flight booking done successfully...'));
    }).catch((err) => {
      setErrorMessage(alert('Unexpected error occurred.'));
      console.log(err);
    });
  };

  return (
    <div className="container p-3">
      {errorMessage &&
        <div className="alert alert-danger">
          {errorMessage}
        </div>
      }

      {infoMessage &&
        <div className="alert alert-success">
          {infoMessage}
        </div>
      }

      <div className="d-flex flex-wrap">
        <h4 className="text-center">{flightList.length} flights are available </h4>
            <table class="table table-striped mt-2">
          <thead>
            <tr>
              <th scope='col'>SR.No</th>
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
                      <td className="fw-bold">{ind+1}</td>
                  <td >{item.flightNumber}</td>
                  <td>{item.operatingAirlines}</td>
                  <td>{item.departureCity}</td>
                  <td>{item.arrivalCity}</td>
                  <td>{item.dateOfDeparture}</td>
                  <td><button
                                 className="btn btn-info w-100"
                                    onClick={() => booking(item)}>
                                    Book Flight
                                </button></td>
                </tr>
                )}
            </tbody>
            </table>
            </div>
            

        </div>
    );
};

export {HomePage};
