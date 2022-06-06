<template>
   <div class="row d-flex" style="margin-top: 40px; bottom: 20px" v-if="trick">
      <div class="col-md-4">
      </div>
      <div>
         <div class="white-panel">
            <input id="input" name="input" v-model="searchParam" rows="4" cols="50" placeholder="search" @change="search">
            <table class="table" v-if="trick2">
               <thead>
                  <tr>
                     <th>Reservable type</th>
                     <th>Name</th>
                     <th>Rules</th>
                     <th>Address</th>
                     <th>Price</th>
                     <th>Capacity</th>
                     <th>Available time</th>
                     <th>DELETE</th>
                     <th>EDIT</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="r in lodgingReservables2" :key="r.id">
                     <td>{{ r.reservableType }}</td>
                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('lodging', r.id)">
							X
                        </button>	
                     </td>
                     <td>
                         <button type="submit" formenctype="text/plain" v-on:click="editReservable('lodging', r.id)">
							edit
                        </button>
                     </td>
                  </tr>
                  
                  <tr v-for="r in boatReservables2" :key="r.id">
                     <td>{{ r.reservableType }}</td>

                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('boat', r.id)">
							X
                        </button>	
                     </td>
                     <td>
                        <button type="submit" formenctype="text/plain" v-on:click="editReservable('boat', r.id)">
							edit
                        </button>
                     </td>
                  </tr>
                  
                  <tr v-for="r in fishingLessonReservables2" :key="r.id" >
                     <td>{{ r.reservableType }}</td>
                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('fishinglesson', r.id)">
							X
                        </button>	
                     </td>
                     <td>
                        <button type="submit" formenctype="text/plain" v-on:click="editReservable('fishinglesson', r.id)">
							edit
                        </button>
                     </td>
                  </tr>
               </tbody>
            </table>
            <table class="table" v-else>
               <thead>
                  <tr>
                     <th>Reservable type</th>
                     <th>Name</th>
                     <th>Rules</th>
                     <th>Address</th>
                     <th>Price</th>
                     <th>Capacity</th>
                     <th>Available time</th>
                     <th>DELETE</th>
                     <th>EDIT</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="r in lodgingReservables" :key="r.id">
                     <td>{{ r.reservableType }}</td>
                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('lodging', r.id)">
							X
                        </button>		
                     </td>
                     <td>
                        <button type="submit" formenctype="text/plain" v-on:click="editReservable('lodging', r.id)">
							edit
                        </button>
                    </td>
                  </tr>
                  
                  <tr v-for="r in boatReservables" :key="r.id">
                     <td>{{ r.reservableType }}</td>
                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('boat', r.id)">
							X
                        </button>	
                     </td>
                     <td>
                         <button type="submit" formenctype="text/plain" v-on:click="editReservable('boat', r.id)">
							edit
                        </button>	
                     </td>
                  </tr>
                  
                  <tr v-for="r in fishingLessonReservables" :key="r.id" >
                     <td>{{ r.reservableType }}</td>
                     <td>{{ r.name }}</td>
                     <td>{{ r.rules }}</td>
                     <td>{{ r.address.city }}</td>
                     <td>{{ r.price }}</td>
                     <td>{{ r.capacity }}</td>
                     <td>{{ r.availableDataRanges }}</td>
                     <td> 
						<button type="submit" formenctype="text/plain" v-on:click="deleteReservable('fishinglesson', r.id)">
							X
                        </button>		
                     </td>
                     <td>
                        <button type="submit" formenctype="text/plain" v-on:click="editReservable('fishinglesson', r.id)">
							edit
                        </button>
                     </td>
                  </tr>
               </tbody>
            </table>
            <div> </div>
            
         </div>
      </div>
      <div class="col-md-1">
      </div>
   </div>
</template>

<script>

import ReservableService from '../services/ReservableService';

export default {
    name: 'UserViewReservables',
    data(){
        return {
			boatReservables: [],
			fishingLessonReservables: [],
			lodgingReservables: [],
			user: {
                id: -1
            },
            trick: true,
            searchParam: "",
            boatReservables2: [],
			fishingLessonReservables2: [],
            lodgingReservables2: [],
            trick2: false
        }
    },
    mounted: function() {
        this.user = localStorage.getItem('user')
        console.log(this.user)
        console.log((JSON.parse(this.user)).id)
		new ReservableService('boat').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
			this.boatReservables = response.data
			
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		});
		
		new ReservableService('fishinglesson').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
			this.fishingLessonReservables = response.data
			console.log(this.fishingLessonReservables)
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		});
		
		new ReservableService('lodging').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
            this.lodgingReservables = response.data
            
			
		})
		.catch(err => {
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		});
		
		
		console.log(this.reservables)
    },
    methods: {
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        deleteReservable(type, id) {
			//darko
			console.log(id);

			new ReservableService(type).deleteReservable(id);
			// this.$forceUpdate();
			// this.componentKey += 1;
			window.location.reload();
        },
        search(){

            if (this.searchParam == "") {
                this.trick = true;
                this.trick2 = false
                return
            }

            // new ReservableService('boat').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
			// this.boatReservables = response.data
			
            // })
            // .catch(err => {
            //     if(err.response.status == 403)
            //     this.$router.push("/unauthorized")
            // });
            
            // new ReservableService('fishinglesson').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
            //     this.fishingLessonReservables = response.data
            // })
            // .catch(err => {
            //     if(err.response.status == 403)
            //     this.$router.push("/unauthorized")
            // });
            
            // new ReservableService('lodging').getAllReservablesByUser((JSON.parse(this.user)).id).then(response => {
            //     this.lodgingReservables = response.data
                
                
            // })
            // .catch(err => {
            //     if(err.response.status == 403)
            //     this.$router.push("/unauthorized")
            // });

            this.boatReservables2 = this.boatReservables.slice()
            this.fishingLessonReservables2 = this.fishingLessonReservables.slice()
            this.lodgingReservables2 = this.lodgingReservables.slice()

            this.boatReservables2 = this.boatReservables2.filter(this.searchPredicate)
            this.fishingLessonReservables2 = this.fishingLessonReservables2.filter(this.searchPredicate)
            this.lodgingReservables2 = this.lodgingReservables2.filter(this.searchPredicate)

            //this.trick = false
            this.trick2 = true
            
            // this.forceRender()
           // this.boatReservables = []
            //this.fishingLessonReservables = []s
            //this.lodgingReservables = []
            //this.forceRender()
            //console.log(this.fishingLessonReservables)
        },
        searchPredicate(obj) {
            console.log(obj)
            // obj = JSON.parse(obj)
            if (obj.name.includes(this.searchParam) || obj.reservableType.includes(this.searchParam) || obj.price == (this.searchParam)){
                console.log('true')
                return true
            }else{
                console.log('false')
                return false
            }
        },
        forceRender() {
            // this.trick = false
            // this.$nextTick().then(() => {
            // // Add the component back in
            // this.trick = true;
            // });
            this.$forceUpdate();
            console.log(this.fishingLessonReservables)
        },
        editReservable(type, id) {
            console.log("ajde")
            this.$router.push(`/reservables/${type}/${id}/edit`)
            return "/" + type + "/" + id + "/edit"
        }
        
    }

    
}

</script>
<style>
.reservable-white-panel{
    background-color: rgba(255, 255, 255, 1);
    width:50%;
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