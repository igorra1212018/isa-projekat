<template>
    <div class="white-panel" style="margin-top: 40px; bottom: 20px">
        <div style="width: 100%; height: 400px;">
            <!--<div class="reservable-title-card">
                <h1>{{reservable.name}}</h1>
            </div>-->
            <img :src="convertImageToBase64(reservable.primaryImage.data)" style="width: 100%; height: 400px">
        </div>
        <div class="pc-tab">
            <input checked="checked" id="tab1" type="radio" v-model="selectedTab" value="Overview" />
            <input id="tab2" type="radio" v-model="selectedTab" value="Services" />
            <input id="tab3" type="radio" v-model="selectedTab" value="Rules" />
            <input id="tab4" type="radio" v-model="selectedTab" value="Gallery" />
            <nav>
              <ul>
                <li class="reservable-view-tab">
                  <label for="tab1" style="width: 25%"><h5>Overview</h5></label>
                </li>
                <li class="reservable-view-tab">
                  <label for="tab2" style="width: 25%"><h5>Services</h5></label>
                </li>
                <li class="reservable-view-tab">
                  <label for="tab3" style="width: 25%"><h5>Rules</h5></label>
                </li>
                <li class="reservable-view-tab">
                  <label for="tab4" style="width: 25%"><h5>Gallery</h5></label>
                </li>
              </ul>
            </nav>
        </div>
		<div class="reservable-view-content-area" v-if="selectedTab == 'Overview'">
            <h1 style="text-align: center">{{reservable.name}}</h1>
            <p style="text-align: center; font-size: 16px">{{reservable.address.address}} {{reservable.address.city}} {{reservable.address.country}}</p>
            <p class="reservable-description"><em>{{reservable.description}}</em></p>
            <!--<div class="row d-flex mt-4" v-if="user">
                <div class="col-md-3">
                    <select name="year_from" id="year_from" v-model="selectedYear">
                      <option v-for="y in availableYears" :value="y" :key="y">{{y}}</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="month_from" id="month_from" v-model="selectedMonth">
                      <option v-for="m in availableMonths" :value="m" :key="m">{{m}}</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="day_from" id="day_from" v-model="selectedDay">
                      <option v-for="d in getAvailableReservationDays" :value="d" :key="d">{{d}}</option>
                    </select>
                </div>
            </div>
            <div class="row d-flex mt-4" v-if="user">
                <div class="col-md-3">
                    <select name="selected_year_to" id="selected_year_to" v-model="selectedYearTo">
                      <option v-for="y in availableYears" :value="y" :key="y">{{y}}</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="selected_month_to" id="selected_month_to" v-model="selectedMonthTo">
                      <option v-for="m in availableMonths" :value="m" :key="m">{{m}}</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="selected_day_to" id="selected_day_to" v-model="selectedDayTo">
                      <option v-for="d in getAvailableReservationDaysTo" :value="d" :key="d">{{d}}</option>
                    </select>
                </div>
            </div>
            <button class="card-button" v-on:click="reserveReservable()">View</button>-->
		</div>
        <div class="reservable-view-content-area" v-if="selectedTab == 'Services'">
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
            availableYears: [],
            availableMonths: [],
            selectedYear: 1,
            selectedMonth: 1,
            selectedDay: 1,
            selectedYearTo: 1,
            selectedMonthTo: 1,
            selectedDayTo: 1,
            availableReservableReservationDates: [],
            reservationParameters: {},
            selectedTab: 'Overview'
        }
    },
    mounted: function() {
        this.reservableService = new ReservableService(this.$route.params.reservable_type);
        this.reservableService.getReservable(this.$route.params.id).then(res => {
          this.reservable = res.data
        });
        this.user = localStorage.getItem('user');
        this.reservableService.getAvailableReservablesReservationDates(this.$route.params.id).then(res => {
          this.availableReservableReservationDates = res.data
        });
        this.availableYears.push(new Date().getFullYear() )
        this.availableYears.push(new Date().getFullYear() + 1)
        this.availableYears.push(new Date().getFullYear() + 2)
        for (let i = 1; i <= 12; i++) {
          this.availableMonths.push(i)
        }
    },
    methods: {
        reserveReservable() {
            this.reservationParameters.reservableId = parseInt(this.$route.params.id)
            this.reservationParameters.userId = 1
            this.reservationParameters.fromDate = new Date(this.selectedYear, this.selectedMonth-1, this.selectedDay+1)
            this.reservationParameters.toDate = new Date(this.selectedYearTo, this.selectedMonthTo-1, this.selectedDayTo+1)
            this.reservableService.reserveReservable(this.reservationParameters)
        },
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        }
    },
    computed: {
        getAvailableReservationDays() {
            let maxDays = 0;
            if(this.selectedMonth == 1 || this.selectedMonth == 3 || this.selectedMonth == 5 || this.selectedMonth == 7 || this.selectedMonth == 8 || this.selectedMonth == 10 || this.selectedMonth == 12 )
                maxDays = 31
            else if(this.selectedMonth == 2 && (this.selectedYear % 4) == 0)
                maxDays = 29
            else if(this.selectedMonth == 2)
                maxDays = 28
            else
                maxDays = 30
            let availableDays = []
            for (let i = 1; i <= maxDays; i++) {
                this.availableReservableReservationDates.forEach((value) => {
                    var testingDate = new Date(this.selectedYear, this.selectedMonth-1, i+1);
                    if((testingDate > new Date(value.fromDate[0], value.fromDate[1]-1, value.fromDate[2]+1)) && (testingDate < new Date(value.toDate[0], value.toDate[1]-1, value.toDate[2]+1)))
                        availableDays.push(i)
                });
            }
            return availableDays
        },
        getAvailableReservationDaysTo() {
            let maxDays = 0;
            if(this.selectedMonthTo == 1 || this.selectedMonthTo == 3 || this.selectedMonthTo == 5 || this.selectedMonthTo == 7 || this.selectedMonthTo == 8 || this.selectedMonthTo == 10 || this.selectedMonthTo == 12 )
                maxDays = 31
            else if(this.selectedMonthTo == 2 && (this.selectedYearTo % 4) == 0)
                maxDays = 29
            else if(this.selectedMonthTo == 2)
                maxDays = 28
            else
                maxDays = 30
            let availableDays = []
            for (let i = 1; i <= maxDays; i++) {
                this.availableReservableReservationDates.forEach((value) => {
                    var testingDate = new Date(this.selectedYearTo, this.selectedMonthTo-1, i+1);
                    if((testingDate > new Date(value.fromDate[0], value.fromDate[1]-1, value.fromDate[2]+1)) && (testingDate < new Date(value.toDate[0], value.toDate[1]-1, value.toDate[2]+1)))
                        availableDays.push(i)
                });
            }
            return availableDays
        }
    }
}

</script>
<style>
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
</style>