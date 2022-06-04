 import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/complaints';

class ComplaintService{

    getComplaints(){
        return axios.get(USER_API_BASE_URL, { headers: authHeader() });
    }

    getComplaintsByUserId(userId){
        return axios.get(USER_API_BASE_URL + '?userId=' + userId , { headers: authHeader() });
    }
    
    approveComplaint(id){
        return axios.put(USER_API_BASE_URL + id + '/approve', {}, { headers: authHeader() });
    }

    rejectComplaint(id, description){
        return axios.put(USER_API_BASE_URL + id + '/reject', description, { headers: authHeader() });
    }   
}

export default new ComplaintService();