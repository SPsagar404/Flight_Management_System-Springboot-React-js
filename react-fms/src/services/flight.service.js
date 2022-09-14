import { BASE_API_URL } from '../common/Constants';
import axios from 'axios';
import { authHeader } from './base.service';


const API_URL = BASE_API_URL + '/flight';

class FlightService {

    saveFlight(flight) {
        return axios.post(API_URL, flight, {headers: authHeader()});
    }

    deleteFlight(flight) {
        return axios.delete(API_URL + '/' + flight.flightId, {headers: authHeader()});
    }

    getAllFlights() {
        return axios.get(API_URL);
    }

    getFlightById(flight) {
        return axios.get(API_URL + '/' + flight.flightId, {headers: authHeader()});
    }

}

export default new FlightService();
