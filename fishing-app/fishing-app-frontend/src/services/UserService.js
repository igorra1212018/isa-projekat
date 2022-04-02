import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api/';

class UserService{

    getUsers(){
        return axios.get(USER_API_BASE_URL + 'users');
    }

    getUser(id){
        return axios.get(USER_API_BASE_URL + 'user/' + id)
    }

    registerUser(user){
        return axios.post(USER_API_BASE_URL + 'register', user);
    }

    updateUser(user){
        return axios.post(USER_API_BASE_URL + 'edit_user_profile', user);
    }
}

export default new UserService();