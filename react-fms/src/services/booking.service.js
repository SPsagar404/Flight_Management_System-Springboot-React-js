import { BASE_API_URL } from '../common/Constants';
import axios from 'axios';
import { authHeader } from './base.service';


const API_URL = BASE_API_URL + '/booking';

class BookingService {

    saveBooking(booking) {
        return axios.post(API_URL, booking, {headers: authHeader()});
    }
    
    deleteBooking(booking) {
        //alert(booking)
        return axios.delete(API_URL + '/' + booking.bookingId, {headers: authHeader()});
    }
    getAllBookingFlights(userId) {
        return axios.get(API_URL+ '/' + userId, {headers: authHeader()});
    }
}

export default new BookingService();
