<template>
    <div class="login-reg-panel">
		<div class="register-info-box">
			<h2>Don't have an account?</h2>
			<p>Click here to register in instead</p>
            <label id="label-login" for="log-login-show">Login</label>
		</div>
		<div class="white-panel">
			<div class="register-show">
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
                    <div class="col-md-12">
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
                            <input type="text" name="residence-country" v-model="user.country" required="required">
                            <span class="keep_hovered">Country</span>
                        </label>
                    </div>
                </div>
				<input type="button" value="Register" v-on:click="registerUser()"/>
			</div>
		</div>
	</div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'Registration',
    data(){
        return {
            user: {},
            confirmPassword: "",
        }
    },
    methods: {
        registerUser(){
            UserService.registerUser(this.user);
        }
    }
}

</script>

<style>

</style>