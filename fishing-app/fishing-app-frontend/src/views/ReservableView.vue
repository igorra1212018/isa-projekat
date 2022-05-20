<template>
    <div class="row d-flex" style="margin-top: 40px; bottom: 20px">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <div class="reservable-white-panel">
                <div style="width: 100%; height: 400px;" v-if="reservable.primaryImage && reservable.primaryImage.data">
                    <img :src="convertImageToBase64(reservable.primaryImage.data)" style="width: 100%; height: 400px">
                </div>
                <div class="pc-tab">
                    <input checked="checked" id="tab1" type="radio" v-model="selectedTab" value="Overview" />
                    <input id="tab2" type="radio" v-model="selectedTab" value="Actions" />
                    <input id="tab3" type="radio" v-model="selectedTab" value="Reviews" />
                    <input id="tab4" type="radio" v-model="selectedTab" value="Gallery" />
                    <nav>
                      <ul>
                        <li class="reservable-view-tab">
                          <label for="tab1" style="width: 25%"><h5>Overview</h5></label>
                        </li>
                        <li class="reservable-view-tab">
                          <label for="tab2" style="width: 25%"><h5>Actions</h5></label>
                        </li>
                        <li class="reservable-view-tab">
                          <label for="tab3" style="width: 25%"><h5>Reviews</h5></label>
                        </li>
                        <li class="reservable-view-tab">
                          <label for="tab4" style="width: 25%"><h5>Gallery</h5></label>
                        </li>
                      </ul>
                    </nav>
                </div>
                <div class="reservable-view-content-area" v-if="selectedTab == 'Overview'">
                    <h1 style="text-align: center">{{reservable.name}}</h1>
                    <p style="text-align: center; font-size: 16px">{{reservable.address.address}} {{reservable.address.city}} {{reservable.address.country.name}}</p>
                    <h4 style="text-align: center">{{averageRating}}</h4>
                    <p class="reservable-description" style="border-bottom: solid 1px gray;"><em>{{reservable.description}}</em></p>
                    <div class="row d-flex row-cols-md-3">
                        <div class="col" v-for="a in reservable.amenities" :key="a.id">
                            <div class="amenity-card">
                                <font-awesome-icon icon="fas fa-wifi" size="6x" v-if="a.amenityIcon == 'wifi'" />
                                <font-awesome-icon icon="fas fa-snowflake" size="6x" v-if="a.amenityIcon == 'hvac'" />
                                <font-awesome-icon icon="fas fa-square-parking" size="6x" v-if="a.amenityIcon == 'parking'" />
                                <font-awesome-icon icon="fas fa-camera" size="6x" v-if="a.amenityIcon == 'tour'" />
                                <h4 style="margin-top: 15px">{{a.amenityName}}</h4>
                                <h5 v-if="a.price > 0"><em>{{a.price}}$</em></h5>
                                <h5 v-if="a.price <= 0"><em>Free</em></h5>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="reservable-view-content-area" v-if="selectedTab == 'Actions'">
                    <div class="row d-flex" v-for="a in actions" :key="a.id">
                        <p>{{a.discount}}</p>
                        <p>{{a.dateRange.fromDate}} - {{a.dateRange.toDate}}</p>
                        <input type="button" class="blue-button" value="Reserve" v-on:click="reserveAction(a)"/>
                    </div>
                </div>
                <div class="reservable-view-content-area" v-if="selectedTab == 'Gallery'">
                    <div class="row row-cols-md-2">
                        <div class="col" v-for="l in reservable.images" :key="l.id">
                            <div class="reservable-image-container">
                                <img :src="convertImageToBase64(l.data)" style="height: 100%; width: 100%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="reservable-white-panel" v-if="user">
              <div class="reservable-side-panel-parameters">
                <h2>Reserve</h2>
                <v-date-picker v-model="range" mode="dateTime" :available-dates="availableReservableReservationDates" :attributes='attributes' style="width: 100%; margin-bottom: 10px" is-range/>
                <div v-for="a in reservable.amenities" :key="a.id">
                    <div v-if="a.price > 0">
                        <input type="checkbox" :value="a.id" v-bind:id="a.id" v-model="selectedAmenities">
                        <label v-bind:for="a.id">{{a.amenityName}}</label>
                    </div>
                </div>
                <input type="button" class="blue-button" value="Reserve" v-on:click="reserveReservable()"/>
              </div>
            </div>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</template>

<script>

import ReservableService from '../services/ReservableService';

export default {
    name: 'ReservableView',
    data(){
        return {
            reservable: {},
            reservableService: {},
            user: {},
            range: {},
            selectedAmenities: [],
            availableReservableReservationDates: [],
            actions: [],
            reservationParameters: {},
            selectedTab: 'Overview',
            attributes: []
        }
    },
    mounted: function() {
        this.reservableService = new ReservableService(this.$route.params.reservable_type);
        this.reservableService.getReservable(this.$route.params.id).then(res => {
          this.reservable = res.data
        });
        this.user = localStorage.getItem('user');
        this.reservableService.getAvailableReservablesReservationDates(this.$route.params.id).then(res => {
          res.data.forEach(value => {
              this.availableReservableReservationDates.push({ start: new Date(value.fromDate[0], value.fromDate[1], value.fromDate[2], value.fromDate[3], value.fromDate[4]), end: new Date(value.toDate[0], value.toDate[1], value.toDate[2], value.toDate[3], value.toDate[4]) })
          })
        });
        this.reservableService.getActionReservationsByReservable(1).then(res => {
            this.actions = res.data
            this.actions.forEach(value => {
                console.log(value)
                this.attributes.push({
                    highlight: 'green',
                    dates: {
                        start: new Date(value.dateRange.fromDate[0], value.dateRange.fromDate[1], value.dateRange.fromDate[2]),
                        end: new Date(value.dateRange.toDate[0], value.dateRange.toDate[1], value.dateRange.toDate[2])
                    }
                })
            })
        })
    },
    methods: {
        reserveReservable() {
            this.reservationParameters.reservableId = parseInt(this.$route.params.id)
            this.reservationParameters.userId = JSON.parse(this.user).id
            this.reservationParameters.fromDate = this.range.start
            this.reservationParameters.fromDate.setMonth(this.reservationParameters.fromDate.getMonth() - 1)
            this.reservationParameters.toDate = this.range.end
            this.reservationParameters.toDate.setMonth(this.reservationParameters.toDate.getMonth() - 1)
            this.reservationParameters.amenities = this.selectedAmenities;
            this.reservableService.reserveReservable(this.reservationParameters)
        },
        reserveAction(a) {
            this.reservationParameters.reservableId = parseInt(this.$route.params.id)
            this.reservationParameters.userId = JSON.parse(this.user).id
            this.reservationParameters.actionId = a.id
            this.reservationParameters.fromDate = new Date(a.dateRange.fromDate[0], a.dateRange.fromDate[1], a.dateRange.fromDate[2], a.dateRange.fromDate[3], a.dateRange.fromDate[4])
            this.reservationParameters.fromDate.setMonth(this.reservationParameters.fromDate.getMonth() - 1)
            this.reservationParameters.toDate = new Date(a.dateRange.toDate[0], a.dateRange.toDate[1], a.dateRange.toDate[2], a.dateRange.toDate[3], a.dateRange.toDate[4])
            this.reservationParameters.toDate.setMonth(this.reservationParameters.toDate.getMonth() - 1)
            this.reservationParameters.amenities = this.selectedAmenities;
            this.reservableService.reserveReservable(this.reservationParameters)
        },
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        }
    },
    computed: {
        averageRating() {
            if(!this.reservable.reviews || this.reservable.reviews.length == 0)
                return "Unrated"
            let reviewSum = 0;
            for (var i = 0; i < this.reservable.reviews.length; i++) { 
                reviewSum +=  this.reservable.reviews[i].rating
            }
            return reviewSum/this.reservable.reviews.length
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