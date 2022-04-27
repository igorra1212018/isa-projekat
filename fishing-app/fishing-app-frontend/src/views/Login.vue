<template>
    <div class="login-panel">
		<div class="login-register-info-box">
			<h2>Don't have an account?</h2>
			<p>Click here to register in instead</p>
            <a href="/register"><label id="label-register" for="log-login-show">Register</label></a>
		</div>
		<div class="login-white-panel">
			<h2>LOGIN</h2>
            <div class="row d-flex mt-5">
                <div class="col-md-12">
                    <label class="input_label">
                        <input type="email" name="email" v-model="user.email" required="required">
                        <span class="keep_hovered">EMail</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-12">
                    <label class="input_label">
                        <input type="password" name="password" v-model="user.password" required="required">
                        <span class="keep_hovered">Password</span>
                    </label>
                </div>
            </div>
			<input type="button" value="Login" style="font-size:16px" v-on:click="login()"/>
		</div>
	</div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'Login',
    data(){
        return {
            user: {
                email: "",
                password: ""
            }
        }
    },
    methods: {
        async login(){
            UserService.login(this.user).then(() => {
                if(localStorage.getItem('user'))
                    this.$router.push("/")
            });
        }
    }
}

</script>

<style>
.login-panel{
    position: relative;
    transform: translateY(50%);
    text-align:center;
    width:50%;
    right:0;left:0;
    margin:auto;
    height:300px;
    background: rgb(9,53,121);
    background: linear-gradient(90deg, rgba(9,53,121,1) 0%, rgba(9,51,121,1) 35%, rgba(0,95,255,1) 100%);
}
.login-panel input[type="radio"]{
    position:relative;
    display:none;
}
.login-panel{
    color:#B8B8B8;
}
.login-panel a {
    color: white;
    text-decoration: none;
}
.login-panel #label-register{
    border:1px solid white;
    padding:5px 5px;
    width:150px;
    display:block;
    text-align:center;
    border-radius:10px;
    cursor:pointer;
    font-weight: 600;
    font-size: 18px;
}
.login-panel #label-register:hover{
    border:1px solid white;
    background-color: white;
    color: rgba(0,95,255,1);
    padding:5px 5px;
    display:block;
    text-align:center;
    border-radius:10px;
    cursor:pointer;
    font-weight: 600;
    font-size: 18px;
    scale: 110%;
    transition: all .3s;
}
.login-register-info-box{
    width:30%;
    top:20%;
    right:0;
    position:absolute;
    text-align:left;
    color: whitesmoke
}
.login-white-panel{
    background-color: rgba(255,255, 255, 1);
    position:absolute;
    top:-50px;
    width:50%;
    right:calc(50% - 50px);
    transition:.3s ease-in-out;
    z-index:0;
    box-shadow: 0 0 15px 9px #00000096;
    color:#242424;
    text-align:left;
    padding:50px;
}
.login-white-panel input[type="button"] {
    max-width: 150px;
    width: 100%;
    background: rgba(0,95,255,1);
    color: #f9f9f9;
    border: none;
    padding: 10px;
    text-transform: uppercase;
    border-radius: 10px;
    float:right;
    cursor:pointer;
    transition: all .3s;
}
.login-white-panel input[type="button"]:hover {
    transform: scale(110%);
    background: rgba(0,95,255,1);
    color: #f9f9f9;
    border: none;
    padding: 10px;
    text-transform: uppercase;
    border-radius: 10px;
    float:right;
    cursor:pointer;
    transition: all .3s;
}
</style>