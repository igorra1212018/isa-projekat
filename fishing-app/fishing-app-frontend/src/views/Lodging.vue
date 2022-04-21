<template>
    <div class="white-panel">
		<div class="register-show">
			<h2>{{lodging.name}}</h2>
            <p>{{lodging.address.address}} {{lodging.address.city}} {{lodging.address.country}}</p>
            <p>{{lodging.description}}</p>
		</div>
	</div>
</template>

<script>

import LodgingService from '../services/LodgingService';

export default {
    name: 'Lodging',
    data(){
        return {
            lodging: {}
        }
    },
    mounted: function() {
        LodgingService.getLodging(this.$route.params.id).then(res => {
          this.lodging = res.data
        });
    },
    methods: {
    }
}

</script>

<style>
</style>