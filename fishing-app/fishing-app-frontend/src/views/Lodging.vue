<template>
    <div class="white-panel">
		<div class="register-show">
			<h2>{{lodging.name}}</h2>
            <p>{{lodging.address.address}} {{lodging.address.city}} {{lodging.address.country}}</p>
            <p>{{lodging.description}}</p>
            <div class="row d-flex mt-4" v-if="user">
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
            <button class="card-button" v-on:click="reserveLodging()">View</button>
		</div>
	</div>
</template>

<script>

import LodgingService from '../services/LodgingService';

export default {
    name: 'Lodging',
    data(){
        return {
            lodging: {},
            user: {},
            availableYears: [],
            availableMonths: [],
            selectedYear: 1,
            selectedMonth: 1,
            selectedDay: 1,
            selectedYearTo: 1,
            selectedMonthTo: 1,
            selectedDayTo: 1,
            availableLodgingReservationDates: [],
            reservationParameters: {}
        }
    },
    mounted: function() {
        LodgingService.getLodging(this.$route.params.id).then(res => {
          this.lodging = res.data
        });
        this.user = localStorage.getItem('user');
        LodgingService.getAvailableLodgingReservationDates(this.$route.params.id).then(res => {
          this.availableLodgingReservationDates = res.data
        });
        this.availableYears.push(new Date().getFullYear() )
        this.availableYears.push(new Date().getFullYear() + 1)
        this.availableYears.push(new Date().getFullYear() + 2)
        for (let i = 1; i <= 12; i++) {
          this.availableMonths.push(i)
        }
    },
    methods: {
        reserveLodging() {
            this.reservationParameters.lodgingId = this.$route.params.id
            this.reservationParameters.userId = 1
            this.reservationParameters.fromDate = new Date(this.selectedYear, this.selectedMonth-1, this.selectedDay+1)
            this.reservationParameters.toDate = new Date(this.selectedYearTo, this.selectedMonthTo-1, this.selectedDayTo+1)
            LodgingService.reserveLodging(this.reservationParameters)
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
                this.availableLodgingReservationDates.forEach((value) => {
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
                this.availableLodgingReservationDates.forEach((value) => {
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
</style>