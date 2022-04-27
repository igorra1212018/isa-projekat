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
                    <div class="col-md-12">
                        <label class="input_label">
                            <input type="password" name="password" v-model="user.password" required="required">
                            <span class="keep_hovered">Password</span>
                        </label>
                    </div>
                </div>
				<input type="button" value="Register" v-on:click="login()"/>
			</div>
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
            UserService.login(this.user).then((res) => {
                localStorage.setItem('token', JSON.stringify(res.data))
            })
        }
    }
}

</script>

<style>

</style>