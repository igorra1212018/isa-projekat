<template>
   <div class="row d-flex" style="margin-top: 40px; bottom: 20px">
      <div class="col-md-4">
      </div>
      <div class="col-md-4">
         <div class="reservable-white-panel">

			<div class="register-show">
			<label class="input_label">
				<input type="text" name="userPointsAdd" v-model="userPointsAdd">
				<span class="keep_hovered">User points add per reservation</span>
				<button type="submit" v-on:click="changeUserPointsAdd(userPointsAdd)">Apply to users</button>
			</label>
			<label class="input_label">
				<input type="text" name="ownerPointsAdd" v-model="ownerPointsAdd">
				<span class="keep_hovered">Owner points add per reservation</span>
				<button type="submit" v-on:click="changeOwnerPointsAdd(ownerPointsAdd)">Apply to owners</button>
			</label>

			<table class="table">
               <thead>
                  <tr>
                     <th>Id</th>
                     <th>Level name</th>
                     <th>Level threshold</th>
                     <th>User discount</th>
                     <th>Owner discount</th>
                     <th>DELETE</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="l in levels" :key="l.id">
                     <td>{{ l.id }}</td>
                     <td>{{ l.levelName }}</td>
                     <td>{{ l.levelThreshold }}</td>
                     <td>{{ l.userDiscount}}</td>
                     <td>{{ l.ownerDiscount }}</td>
                     <td> 
						<button type="submit" v-on:click="deleteLevel(l.id)">
							X
                        </button>	
                     </td>
                  </tr>
               </tbody>
            </table>
			</div>
			
            
            
			<div class="register-show">
			<h2>Add level</h2>
            <div class="row d-flex mt-5">
                <div class="col-md-12">
                    <label class="input_label">
                        <input type="text" name="levelName" v-model="levelToAdd.levelName">
                        <span class="keep_hovered">Level name</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="description" v-model="levelToAdd.levelThreshold">
                        <span class="keep_hovered">Points threshold</span>
                    </label>
                </div>
                
                
            </div>
            <div >
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="biography" v-model="levelToAdd.userDiscount">
                        <span class="keep_hovered">Discount for users</span>
                    </label>
                </div>
            </div>
            <div >
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="capacity" v-model="levelToAdd.ownerDiscount">
                        <span class="keep_hovered">Discount for owners</span>
                    </label>
                </div>
            </div>
            
        </div>
		<input type="button" value="Add level" v-on:click="addLevel(levelToAdd)"/>
         </div>
      </div>
      <div class="col-md-1">
      </div>
   </div>
</template>

<script>

import LoyaltyProgramService from '../services/LoyaltyProgramService';

export default {
    name: 'AdminLoyaltyProgram',
    data(){
        return {
			levels: [],
			levelToAdd: {},
			userPointsAdd: 0,
			ownerPointsAdd: 0,
			componentKey: 0
        }
    },
    mounted: function() {
		LoyaltyProgramService.getAllLevels().then(response => {
			this.levels = response.data
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		})
		;
		
		console.log(this.levels);
    },
    methods: {
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        deleteLevel(id) {
			LoyaltyProgramService.deleteLevel(id).then(location.reload());
		},
		addLevel(level) {
			LoyaltyProgramService.addLevel(level).then(location.reload());
		},
		changeUserPointsAdd(userPointsAdd) {
			LoyaltyProgramService.changeUserPointsAdd(userPointsAdd);
		},
		changeOwnerPointsAdd(ownerPointsAdd) {
			LoyaltyProgramService.changeOwnerPointsAdd(ownerPointsAdd);
		}
    }
}

</script>
<style>
.reservable-white-panel{
    background-color: rgba(255,255, 255, 1);
    width:150%;
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