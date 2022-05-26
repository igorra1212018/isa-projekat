<template>
    <div class="row d-flex" style="margin-top: 40px">
    </div>
</template>

<script>

import ComplaintService from '../services/ComplaintService';

export default {
    name: 'ComplaintsView',
    data(){
        return {
            user: {
                id: 0
            },
            complaints: [],
        }
    },
    mounted: function() {
        this.loadComplaints()
    },
    methods: {
        loadComplaints() {
            this.user = localStorage.getItem('user');
            ComplaintService.getComplaintsByUserId(JSON.parse(this.user).id).then(res => {
              this.reservations = res.data
            });
        }
    }
}

</script>

<style>
</style>