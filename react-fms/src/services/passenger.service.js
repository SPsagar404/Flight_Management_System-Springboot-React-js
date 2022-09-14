import { BASE_API_URL } from '../common/Constants';
import axios from 'axios';
import { authHeader } from './base.service';


const API_URL = BASE_API_URL + '/passenger';

class PassengerService {

    saveFlight(passenger) {
        return axios.post(API_URL, passenger, {headers: authHeader()});
    }

    deleteFlight(passenger) {
        return axios.delete(API_URL + '/' + passenger.passengerId, {headers: authHeader()});
    }

    getAllFlights() {
        return axios.get(API_URL);
    }

    getFlightById(passenger) {
        return axios.get(API_URL + '/' + passenger.passengerId, {headers: authHeader()});
    }

}

export default new PassengerService();
