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
                     <th>Status</th>
                     <th>Decription</th>
                     <th>Response</th>
                     <th>Reservation Id</th>
                     <th>Entity</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="c in complaints" :key="c.id">
                     <td>{{ c.id }}</td>
                     <td>{{ c.approved }}</td>
                     <td>{{ c.description }}</td>
                     <td v-if="c.response">{{ c.response }}</td>
                     <td v-if="!c.response">PENDING</td>
                     <td>{{ c.reservation.id }}</td>
                     <td>{{ c.reservation.reservedEntity.name }}</td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
      <div class="col-md-1">
      </div>
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
              this.complaints = res.data
            });
        }
    }
}

</script>

<style>
</style>