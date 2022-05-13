<template>
    <div>
    </div>
</template>

<script>

import ReservableService from '../services/ReservableService';

export default {
    name: 'UserReservations',
    data(){
        return {
            user: {},
            lodgingReservations: [],
            boatReservations: [],
            fishingLessonReservations: [],
            fishingLessons: {},
            lodgingService: {},
            boatService: {},
        }
    },
    mounted: function() {
        this.loadReservations()
    },
    methods: {
        loadReservations() {
            this.user = localStorage.getItem('user');
            this.lodgingService = new ReservableService('lodging')
            this.boatService = new ReservableService('boat')
            this.fishingLessonService = new ReservableService('fishinglesson')
            this.lodgingService.getReservableByUser(this.$route.params.id).then(res => {
              this.lodgingReservations = res.data
              this.boatService.getReservableByUser(this.$route.params.id).then(res2 => {
                this.boatReservations = res2.data
                this.fishingLessonService.getReservableByUser(this.$route.params.id).then(res3 => {
                  this.fishingLessonReservations = res3.data
                });
              });
            });
        }
    }
}

</script>

<style>

</style>