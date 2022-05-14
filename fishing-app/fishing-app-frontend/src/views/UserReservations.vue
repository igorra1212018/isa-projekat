<template>
    <div class="row d-flex" style="margin-top: 40px">
        <div class="col-md-2">
        </div>
        <div class="col-md-4" v-for="r in reservations" :key="r.id">
            <div class="reservation-panel">
                <img :src="convertImageToBase64(r.reservedEntity.primaryImage.data)" style="width: 100%; height: 400px">
                <h2>{{r.dateRange.fromDate}} - {{r.dateRange.toDate}}</h2>
                <div v-for="a in r.amenities" :key="a.id">
                    <p>{{a.amenityName}}</p>>
                </div>
                <input type="button" class="blue-button" value="Cancel" v-if="!r.cancelled" v-on:click="cancel(r.id)"/>
            </div>
        </div>
        <div class="col-md-2">
        </div>
    </div>
</template>

<script>

import ReservableService from '../services/ReservableService';

export default {
    name: 'UserReservations',
    data(){
        return {
            user: {},
            reservations: [],
            lodgingService: {}
        }
    },
    mounted: function() {
        this.loadReservations()
    },
    methods: {
        loadReservations() {
            this.user = localStorage.getItem('user');
            this.lodgingService = new ReservableService('lodging')
            this.lodgingService.getReservationsByUser(this.$route.params.id).then(res => {
              this.reservations = res.data
            });
        },
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        cancel(reservationId) {
            this.lodgingService.cancelReservation(reservationId)
        }
    }
}

</script>

<style>
.reservation-panel {
    width: 100%;
    margin: 0 auto;
    background-color: white
}
</style>