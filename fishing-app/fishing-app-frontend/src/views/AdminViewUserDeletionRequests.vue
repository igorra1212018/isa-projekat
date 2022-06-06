<template>
   <div class="row d-flex" style="margin-top: 40px; bottom: 20px">
      <div class="col-md-4">
      </div>
      <div class="col-md-4">
         <div class="reservable-white-panel">
            <table class="table">
               <thead>
                  <tr>
                     <th>Id</th>
                     <th>User</th>
                     <th>Status</th>
                     <th>Description</th>
                     <th>Response</th>
                     <th>APPROVE</th>
                     <th>REJECT</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="r in requests" :key="r.id">
                     <td>{{ r.id }}</td>
                     <td>{{ r.user.email }}</td>
                     <td>{{ r.requestApproval }}</td>
                     <td>{{ r.requestDescription }}</td>
                     <td>{{ r.rejectionResponse }}</td>
                     <td> 
						<button type="submit" v-on:click="approveRequest(r.id)">
							✔
                        </button>	
                     </td>
                     <td> 
						<button type="submit" v-on:click="rejectRequest(r.id, rejectionDescription)" :disabled="rejectionDescription == ''">
							X
                        </button>	
                     </td>
                  </tr>
               </tbody>
            </table>
            
            <textarea id="area" name="area" v-model="rejectionDescription" rows="4" cols="50" placeholder="rejection description"></textarea>
            
         </div>
      </div>
      <div class="col-md-1">
      </div>
   </div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'AdminViewUserDeletionRequests',
    data(){
        return {
			requests: [],
			rejectionDescription: "",
			componentKey: 0
        }
    },
    mounted: function() {
		UserService.getAllUserDeletionRequests().then(response => {
			this.requests = response.data
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		})
		;
		console.log(this.requests);
    },
    methods: {
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        approveRequest(id) {
			UserService.approveDeletionRequest(id).then(location.reload());
        },
        rejectRequest(id, description) {
			UserService.rejectDeletionRequest(id, description).then(location.reload());
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