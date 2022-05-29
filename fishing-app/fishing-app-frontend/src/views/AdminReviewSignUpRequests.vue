<template>
   <div class="row d-flex" style="margin-top: 40px; bottom: 20px">
      <div class="col-md-4">
      </div>
      <div class="col-md-4">
         <div class="reservable-white-panel">
            <table class="table">
               <thead>
                  <tr>
                     <th>Email</th>
                     <th>First name</th>
                     <th>Last name</th>
                     <th>Address</th>
                     <th>City</th>
                     <th>Conutry</th>
                     <th>Activated</th>
                     <th>Activate/Deactivate</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="user in users" :key="user.id">
                     <td>{{ user.email }}</td>
                     <td>{{ user.firstName }}</td>
                     <td>{{ user.lastName }}</td>
                     <td>{{ user.address }}</td>
                     <td>{{ user.city }}</td>
                     <td>{{ user.country }}</td>
                     <td :key="componentKey">{{ user.activated }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="changeActivation(decodeURIComponent(user.email))">
							x
                        </button>	
                     </td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
      <div class="col-md-1">
      </div>
   </div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'AdminReviewSignUpRequest',
    data(){
        return {
			users: [],
			componentKey: 0
        }
    },
    mounted: function() {
		UserService.getUsers().then(response => {
			this.users = response.data
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		})
		;
		console.log(this.users);
    },
    methods: {
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        changeActivation(email) {
			console.log(email);
			this.users = [];
			UserService.changeActivation(email).then();
			this.getUsers().then();
			this.$forceUpdate();
			this.componentKey += 1;
        },
        getUsers(){
			UserService.getUsers().then(response => {
				this.users = response.data
			})
			.catch(err => {
				if(err.response.status == 403)
				this.$router.push("/unauthorized")
			});
        }
    }
}

</script>
<style>
.reservable-white-panel{
    background-color: rgba(255,255, 255, 1);
    width:100%;
    margin:auto;
    text-align:center;
    transition:.3s ease-in-out;
    z-index:0;
    box-shadow: 0 0 15px 9px #00000096;
}
.reservable-title-card {
    position: absolute;
    top: 50px;
    background: linear-gradient(to right, rgba(0,95,255,1), rgba(0,136,221,0.5));
    color: #f9f9f9;
    border: none;
    padding-top: 5px;
    padding-left: 10px;
    padding-right: 100px;
    padding-bottom: 5px;
}
.reservable-description {
    font-size: 16px;
    text-align: center;
    padding-top: 20px;
    border-top: solid 1px gray;
}
.pc-tab > input, .pc-tab section > div {
    display: none;
}
.pc-tab {
	width: 90%;
	margin: 0 auto;
    margin-left: 5%;
    margin-right: 5%
}
.pc-tab ul {
	list-style: none;
	margin: 0;
	padding: 0;
}
.pc-tab ul li label {
	float: left;
	padding: 15px 25px;
	border: 0;
    border-bottom: solid 1px gray;
	background: white;
}
.pc-tab ul li label:hover {
    border-bottom: solid 2px rgba(0,95,255,1);
}
.pc-tab ul li label:active {
	border-bottom: solid 2px rgba(0,95,255,1);
}
.pc-tab ul li label:after {
	border-bottom: solid 2px rgba(0,95,255,1);
}
.pc-tab ul li:not(:last-child) label {
	border-right-width: 0;
}
.reservable-view-content-area{
    z-index: 1;
    transition:0.3s ease-in-out;
    color:#242424;
    text-align:left;
    padding:50px;
    margin-top: 40px
}
.amenity-card {
    position: relative;
    max-width: 100%;
    padding-bottom: 1.3125em;
    margin-bottom: 60px;
    text-align: center;
}
.reservable-image-container {
    position: relative;
    max-width: 100%;
}
.reservable-side-panel-parameters {
    z-index: 1;
    transition:0.3s ease-in-out;
    color:#242424;
    text-align:left;
    padding:50px;
}
</style>