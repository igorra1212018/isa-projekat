<template>
    <div class="row d-flex" style="margin-top: 40px">
        <div class="col-md-3">
        </div>
        <div class="col-md-6" v-for="r in reservations" :key="r.id">
            <div class="row d-flex reservation-panel">
                <div class="col-md-3">
                    <img :src="convertImageToBase64(r.reservedEntity.primaryImage.data)" style="width: 100%; height: 100%">
                </div>
                <div class="col-md-6">
                    <h2>{{r.reservedEntity.name}} </h2>
                    <em v-if="r.cancelled" style="color: rgb(228, 40, 40)">Cancelled</em>
                    <p>{{new Date(r.dateRange.fromDate[0], r.dateRange.fromDate[1], r.dateRange.fromDate[2])}} - {{new Date(r.dateRange.toDate[0], r.dateRange.toDate[1], r.dateRange.toDate[2])}}</p>
                    <div v-for="a in r.amenities" :key="a.id">
                        <p>{{a.amenityName}}</p>
                    </div>
                    <div v-if="canReviewReservation(r) && canReviewEntity(r.reservedEntity)">
                        <textarea cols="40" rows="5" v-model="r.reviewDescription"></textarea>
                        <select name="rating" id="rating" v-model="r.rating">
                            <option :value="1">1</option>
                            <option :value="2">2</option>
                            <option :value="3">3</option>
                            <option :value="4">4</option>
                            <option :value="5">5</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-3">
                    <input type="button" class="user-reservations-red-button" value="Cancel" v-if="canCancelReservation(r)" v-on:click="cancel(r.id)"/>
                    <input type="button" class="user-reservations-blue-button" value="Review" v-if="!r.cancelled" v-on:click="review(r)"/>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <div style="background-color: white">
                <h2>Subscriptions</h2>
                <div v-for="s in subscriptions" :key="s.id">
                    <p>{{s.name}}</p>
                </div>
            </div>
        </div>
        <div class="col-md-1">
        </div>
    </div>
</template>

<script>

import ReservableService from '../services/ReservableService';
import ReviewService from '../services/ReviewService';

export default {
    name: 'UserReservations',
    data(){
        return {
            user: {
                id: 0
            },
            reservations: [],
            subscriptions: [],
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
            this.lodgingService.getAllSubscribedReservables(this.$route.params.id).then(res => {
              this.subscriptions = res.data
            });
        },
        convertImageToBase64(byteArray) {
            return 'data:image/jpeg;base64,' + byteArray;
        },
        cancel(reservationId) {
            this.lodgingService.cancelReservation(reservationId).then(() => {
                this.$router.go();
            })
        },
        review(reservation) {
            let review = {};
            console.log(reservation)
            review.description = reservation.reviewDescription;
            review.rating = reservation.rating;
            review.reservableId = reservation.reservedEntity.id;
            review.userId = JSON.parse(this.user).id;
            ReviewService.addReview(review);
        },
        canCancelReservation(reservation) {
            var tmp = new Date(reservation.dateRange.fromDate[0], reservation.dateRange.fromDate[1], reservation.dateRange.fromDate[2], reservation.dateRange.fromDate[3], reservation.dateRange.fromDate[4])
            if(!reservation.cancelled && (new Date() < tmp.setDate(tmp.getDate() - 3)))
                return true
            return false
        },
        canReviewReservation(reservation) {
            if(!reservation.cancelled && new Date(reservation.dateRange.toDate[0], reservation.dateRange.toDate[1], reservation.dateRange.toDate[2]) < new Date()) 
                return true
            return false;
        },
        canReviewEntity(reservable) {
            if(reservable.reviews) {
                for (let i = 0; i < reservable.reviews.length; i++) {
                    if (reservable.reviews[i].user.id == JSON.parse(this.user).id) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

</script>

<style>
.reservation-panel {
    margin: 0 auto;
    padding-left: 0px !important;
    background-color: white
}
.user-reservations-blue-button {
    max-width: 150px;
    width: 100%;
    background: rgba(0,95,255,1);
    color: #f9f9f9;
    border: none;
    padding: 10px;
    border-radius: 10px;
    text-transform: uppercase;
    float:right;
    cursor:pointer;
}
.user-reservations-red-button {
    max-width: 150px;
    width: 100%;
    background: rgb(228, 40, 40);
    color: #f9f9f9;
    border: none;
    padding: 10px;
    border-radius: 10px;
    text-transform: uppercase;
    float:right;
    cursor:pointer;
}
</style>