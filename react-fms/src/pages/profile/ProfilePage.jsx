import { useEffect, useRef, useState } from 'react';
import UserService from '../../services/user.service';
import { useDispatch, useSelector } from 'react-redux';
import { Role } from '../../models/role';
import { clearCurrentUser } from '../../store/actions/user';
import { useNavigate } from 'react-router-dom';
import bookingService from '../../services/booking.service';
import Booking from '../../models/booking';
import { BookingDelete } from '../../components/BookingDelete';


const ProfilePage = () => {

    const [purchaseList, setPurchaseList] = useState([]);
    const [selectedBooking, setSelectedBooking] = useState(new Booking('', '', '','','','',''));
    //const[flightData,setFlightData] =useState();
    const [errorMessage, setErrorMessage] = useState('');

    const currentUser = useSelector(state => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const deleteComponent = useRef();

    useEffect(() => {
        //alert(currentUser.id);
        bookingService.getAllBookingFlights(currentUser.id).then((response) => {
            setPurchaseList(response.data);
            //console.log(response.data);

        });
    }, []);

    // const getFlightData=()=>

    const deleteBookingRequest = (booking) => {
        console.log(booking.flight);
        setSelectedBooking(booking);
      deleteComponent.current?.showDeleteModal();
    };
    const changeRole = () => {
      const newRole = currentUser.role === Role.ADMIN ? Role.USER : Role.ADMIN;

      UserService.changeRole(newRole).then(() => {
          //clear session
          dispatch(clearCurrentUser());
          navigate('/login');
      }).catch((err) => {
          setErrorMessage('Unexpected error occurred.');
          console.log(err);
      });
    };

    const deleteBooking = () => {
        //console.log(selectedBooking);
        bookingService.deleteBooking(selectedBooking).then(_ => {
            setPurchaseList(purchaseList.filter(booking => booking.bookingId !== selectedBooking.bookingId));
        }).catch((err) => {
            setErrorMessage('Unexpected error occurred.');
            console.log(err);
           
        });
    };

    return (
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
                                <h3>All Booked Flights</h3>
                            </div>
                            <div className="col-6 text-end">
                                Current role is <strong>{currentUser?.role} </strong>
                                {/* <button className="btn btn-primary" onClick={() => changeRole()}>
                                    Change Role
                                </button> */}
                            </div>
                        </div>

                    </div>
                    <div className="card-body">
                        <table className="table table-striped">

                            <thead>
                            <tr>
                                <th scope="col">SR.No</th>
                                <th scope="col">Booking Id</th>
                                <th scope="col">Booking Date</th>
                                <th scope="col">Flight Number</th>
                                <th scope='col'>Source</th>
                                <th scope='col'>Destination</th>
                                <th scope='col'>Date Of Departure</th>
                                <th scope="col">Passenger Name</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>

                            {purchaseList.map((item, ind) =>
                                <tr key={item.bookingId}>
                                    <th scope="row">{ind+1}</th>
                                    <td>{item.bookingId}</td>
                                    <td>{new Date(item.booking_date).toLocaleDateString()}</td>
                                    <td>{item.flight.flightNumber}</td>
                                    <td>{item.flight.departureCity}</td>
                                    <td>{item.flight.arrivalCity}</td>
                                    <td>{new Date(item.flight.dateOfDeparture).toLocaleDateString()}</td>
                                    <td>{currentUser.name}</td>
                                    <td>
                                        <button className="btn btn-danger" onClick={() => deleteBookingRequest(item)}>Cancel Booking</button>
                                    </td>

                                </tr>
                            )}


                            </tbody>

                        </table>
                    </div>
                </div>

            </div>
            <BookingDelete ref={deleteComponent} onConfirmed={() => deleteBooking()}></BookingDelete>
        </div>
    );
    };

export {ProfilePage};
