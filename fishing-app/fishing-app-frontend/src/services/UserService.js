import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api/user/';

class UserService{

    getUsers(){
        return axios.get(USER_API_BASE_URL + 'all');
    }

    getUser(id){
        return axios.get(USER_API_BASE_URL + id)
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
}

export default new UserService();