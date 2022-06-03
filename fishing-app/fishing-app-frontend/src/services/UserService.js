import axios from 'axios';
import authHeader from '../auth-header';

const USER_API_BASE_URL = 'http://localhost:8080/api/user/';

class UserService{

    getUsers(){
        return axios.get(USER_API_BASE_URL + 'all', { headers: authHeader() });
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

    isEmailAvailable(emailParam) {
      /*var myObj = {
        email: ""
      }
      myObj.email = emailParam
      return axios.post(USER_API_BASE_URL + 'emailavailability', JSON.stringify(myObj))*/
      if(emailParam)
       return true
      return true
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
        return axios.post(USER_API_BASE_URL + 'edit', user);
    }

	changeActivation(email) {
		return axios.post(USER_API_BASE_URL + 'changeActivation', email, { headers: authHeader() })
				.then()
				.catch(err => {
                console.error(err);
            })
	}
	
	getAllUserCreationRequests(){
		return axios.get(USER_API_BASE_URL + 'getAllUserCreationRequests', { headers: authHeader() })
				.then()
				.catch(err => {
                console.error(err);
            })
	}
	
	approveRequest(id) {
		return axios.post(USER_API_BASE_URL + id + '/approveRequest', {}, { headers: authHeader() })
				.then()
				.catch(err => {
                console.error(err);
            })
	}
	
	rejectRequest(id, description) {
		return axios.post(USER_API_BASE_URL + id + '/rejectRequest', description, { headers: authHeader() })
				.then()
				.catch(err => {
                console.error(err);
            })
	}
	
	registerAdmin(user) {
		return axios.post(USER_API_BASE_URL + 'registerAdmin', user, { headers: authHeader() });
	}
}

export default new UserService();