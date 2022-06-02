import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/user/';

class UserService{

    getUsers(){
        return axios.get(USER_API_BASE_URL + 'all');
    }

    getCountries(){
      return axios.get(USER_API_BASE_URL + 'countries/all');
    }

    getUser(id){
        return axios.get(USER_API_BASE_URL + id, { headers: authHeader() })
    }

    getUserByEmail(userEmail){
        return axios.get(USER_API_BASE_URL + 'email/' + userEmail, { headers: authHeader() })
    }

    login(user) {
      return axios
        .post('http://localhost:8080/api/auth/signin', user)
        .then(response => {
          if (response.data.accessToken) {
            localStorage.setItem('user', JSON.stringify(response.data));
          }
          return response.data;
        });
    }

    logout() {
      localStorage.removeItem('user');
    }

    registerUser(user){
        return axios.post(USER_API_BASE_URL + 'register', user);
    }
    
    registerOwner(user){
        return axios.post(USER_API_BASE_URL + 'register_owner', user);
    }

    updateUser(user){
        return axios.post(USER_API_BASE_URL + 'edit', user, { headers: authHeader() });
    }
}

export default new UserService();