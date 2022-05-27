<template>
    <div class="registration-panel">
		<div class="register-info-box">
			<h2>Have an account?</h2>
			<p>Click here to log in instead</p>
            <label id="label-login" for="log-login-show">Login</label>
		</div>
        <div v-bind:class="{ 'registration-side-panel': user.role && user.role != 'CLIENT', 'registration-side-panel-hidden': !user.role || user.role == 'CLIENT' }">
			<h3>REQUEST ELABORATION</h3>
            <textarea cols="40" rows="5" v-model="user.applicationDetails"></textarea>
        </div>
		<div class="registration-white-panel">
			<h2>REGISTER</h2>
            <div class="row d-flex mt-5">
                <div class="col-md-12">
                    <label v-bind:class="{ input_label: emailAvailable, input_label_invalid: !emailAvailable }">
                        <input type="email" name="email" v-model="user.email" required="required" v-on:blur="checkEmailAvailability">
                        <span class="keep_hovered">EMail</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-6">
                    <label class="input_label">
                        <input type="password" name="password" v-model="user.password" required="required">
                        <span class="keep_hovered">Password</span>
                    </label>
                </div>
                <div class="col-md-6">
                    <label class="input_label">
                        <input type="password" name="confirm-password" v-model="confirmPassword" required="required">
                        <span class="keep_hovered">Confirm Password</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-6">
                    <label class="input_label">
                        <input type="text" name="first-name" v-model="user.firstName" required="required">
                        <span class="keep_hovered">First Name</span>
                    </label>
                </div>
                <div class="col-md-6">
                    <label class="input_label">
                        <input type="text" name="last-name" v-model="user.lastName" required="required">
                        <span class="keep_hovered">Last Name</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-6">
                    <input type='radio' id='client' checked='checked' name='radio' class="blue_option" v-model="user.role" value="CLIENT">
                    <label for='client'>Client</label>
                </div>
                <div class="col-md-6">
                    <input type='radio' id='lodging_owner' checked='checked' name='radio' class="blue_option" v-model="user.role" value="LODGING_OWNER">
                    <label for='lodging_owner'>Host</label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-6">
                    <input type='radio' id='boat_owner' name='radio' class="blue_option" v-model="user.role" value="BOAT_OWNER">
                    <label for='boat_owner'>Captain</label>
                </div>
                <div class="col-md-6">
                    <input type='radio' id='instructor' name='radio' class="blue_option" v-model="user.role" value="FISHING_INSTRUCTOR">
                    <label for='instructor'>Instructor</label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-2">
                    <label class="input_label">
                        <input type="text" name="phone-prefix" v-model="user.country.phoneCode" readonly disabled>
                        <span class="keep_hovered">Prefix</span>
                    </label>
                </div>
                <div class="col-md-10">
                    <label class="input_label">
                        <input type="text" name="birth-date" v-model="user.contactPhone" required="required">
                        <span class="keep_hovered">Contact Phone</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="residence-address" v-model="user.address" required="required">
                        <span class="keep_hovered">Address</span>
                    </label>
                </div>
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="residence-city" v-model="user.city" required="required">
                        <span class="keep_hovered">City</span>
                    </label>
                </div>
                <div class="col-md-4">
                    <label class="input_label">
                        <select name="countries" id="countries" v-model="user.country">
                          <option v-for="c in countries" :key="c.id" :value="c">{{c.name}}</option>
                        </select>
                        <span class="keep_hovered">Country</span>
                    </label>
                </div>
            </div>
			<input type="button" value="Register" v-on:click="registerUser()"/>
		</div>
	</div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'Registration',
    data(){
        return {
            user: {
                country: {}
            },
            confirmPassword: "",
            emailAvailable: true,
            countries: []
        }
    },
    mounted: function() {
        UserService.getCountries().then((response) => {
            this.countries = response.data;   
        });
    },
    methods: {
        registerUser(){
            if(this.user.role && this.user.role == 'CLIENT')
                UserService.registerUser(this.user);
            else if(this.user.role)
                UserService.registerOwner(this.user);
        },
        checkEmailAvailability(){
            if(!this.user.email || UserService.isEmailAvailable(this.user.email))
                this.emailAvailable = true
            else
                this.emailAvailable = false
        }
    }
}

</script>

<style>
    .registration-panel{
        position: relative;
        top: 50%;
        transform: translateY(50%);
        text-align:center;
        width:50%;
        right:0;left:0;
        margin:auto;
        height:400px;
        background: rgb(9,53,121);
        background: linear-gradient(90deg, rgba(9,53,121,1) 0%, rgba(9,51,121,1) 35%, rgba(0,95,255,1) 100%);
    }
    .registration-white-panel{
        background-color: rgba(255,255, 255, 1);
        position:absolute;
        top:-150px;
        width:50%;
        right:calc(50% - 50px);
        transition:.3s ease-in-out;
        z-index:0;
        box-shadow: 0 0 15px 9px #00000096;
        color:#242424;
        text-align:left;
        padding:50px;
    }
    .registration-white-panel input[type="button"] {
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
    .registration-white-panel input[type="button"]:hover {
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

    .registration-side-panel{
        background-color: rgba(255,255, 255, 1);
        position:absolute;
        width: 40%;
        top:-100px;
        right:calc(75% + 200px);
        transition:.3s ease-in-out;
        z-index:0;
        box-shadow: 0 0 15px 9px #00000096;
        color:#242424;
        text-align:left;
        padding:50px;
    }
    .registration-side-panel-hidden{
        background-color: rgba(255,255, 255, 1);
        position:absolute;
        opacity: 0%;
        width: 40%;
        top:-100px;
        right:calc(50% + 200px);
        transition:.3s ease-in-out;
        z-index:0;
        box-shadow: 0 0 15px 9px #00000096;
        color:#242424;
        text-align:left;
        padding:50px;
    }
    .registration-side-panel-hidden textarea,
    .registration-side-panel textarea {
        border:1px solid rgb(143, 176, 233);
        border-radius: 10px;
        resize: none;
        height: 400px;
        width: 100%;
        transition: all .3s;
    }

    .registration-side-panel textarea:hover {
        border:1px solid rgba(0,95,255,1);
        border-radius: 10px;
        resize: none;
        height: 400px;
        width: 100%;
        transition: all .3s;
    }

    .registration-side-panel textarea:focus {
        border:1px solid rgba(0,95,255,1);
        border-radius: 10px;
        resize: none;
        height: 400px;
        width: 100%;
        transition: all .3s;
    }

    .registration-panel input[type="radio"]{
        position:relative;
        display:none;
    }
    .registration-panel{
        color:#B8B8B8;
    }
    .registration-panel #label-login, 
    .registration-panel #label-register{
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
    .login-info-box{
        width:30%;
        padding:0 50px;
        top:20%;
        left:0;
        position:absolute;
        text-align:left;
    }
    .register-info-box{
        width:30%;
        padding:0 50px;
        top:20%;
        right:0;
        position:absolute;
        text-align:left;
        color: whitesmoke
    }
    .right-log{right:50px !important;}

    .register-show{
        z-index: 1;
        transition:0.3s ease-in-out;
        color:#242424;
        text-align:left;
        padding:50px;
    }

    /* INPUT FIELDS */
    .wrapper {
        max-width: 560px;
        margin: 100px auto;
    }
    label.input_label {
        position: relative;
        display: block;
        margin: 0 0 40px 0;
    }
    label.input_label > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label:hover > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid rgb(143, 176, 233);
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label > input:valid {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }
    label.input_label > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label > input:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > input:focus {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }   
    label.input_label > select {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        margin: 0 0 0 0;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label > select:valid {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }
    label.input_label > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label > select:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > select:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > input:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > select:focus {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }


    label.input_label_invalid {
        position: relative;
        display: block;
        margin: 0 0 40px 0;
    }
    label.input_label_invalid > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label_invalid:hover > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #e4675e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label_invalid > input:valid {
        border-bottom: 1px solid #d93025;
        box-shadow: 0 1px 0 0 #d93025;
    }
    label.input_label_invalid > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label_invalid > input:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: #d93025;
    }
    label.input_label_invalid > input:focus {
        border-bottom: 1px solid #d93025;
        box-shadow: 0 1px 0 0 #d93025;
    }   
    label.input_label_invalid > select {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        margin: 0 0 0 0;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label_invalid > select:valid {
        border-bottom: 1px solid #d93025;
        box-shadow: 0 1px 0 0 #d93025;
    }
    label.input_label_invalid > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label_invalid > select:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: #d93025;
    }
    label.input_label_invalid > select:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: #d93025;
    }
    label.input_label_invalid > input:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: #d93025;
    }
    label.input_label_invalid > select:focus {
        border-bottom: 1px solid #d93025;
        box-shadow: 0 1px 0 0 #d93025;
    }


    /* GENDER SELECTION */  
    input[type="radio"] {
        display: none;
    }

    input[type="radio"] + label {
        z-index: 10;
        margin: 0 10px 10px 0;
        position: relative;
        color: #ced4da;
        text-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
        font-weight: bold;
        background-color: #ffffff;
        border: 2px solid #ced4da;
        cursor: pointer;
        transition: all 200ms ease;
    }

    input[type="radio"].male_option:hover + label {
        color: white;
        background-color: rgb(143, 176, 233);
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].male_option:checked + label {
        color: white;
        background-color: rgba(0,95,255,1);
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].female_option:hover + label {
        color: white;
        background-color: #ffd1d9;
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].female_option:checked + label {
        color: white;
        background-color: #FB9AAC;
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"] + label {
        padding: 5px 40%;
        border-radius: 10px;
    }
</style>