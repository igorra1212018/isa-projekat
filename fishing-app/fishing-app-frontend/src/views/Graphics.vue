<template>
   <div class="row d-flex" style="margin-top: 40px; bottom: 20px">
      <div class="col-md-4">
      </div>
      <div class="col-md-5">
         <div class="reservable-white-panel">
				<v-calendar
				class="custom-calendar max-w-full"
				:masks="masks"
				:attributes="reservations"
				disable-page-swipe
				is-expanded
				>
					<template v-slot:day-content="{ day, reservations }">
						<div class="flex flex-col h-full z-10 overflow-hidden">
						<span class="day-label text-sm text-gray-900">{{ day.day }}</span>
						<div class="flex-grow overflow-y-auto overflow-x-auto">
							<p
							v-for="r in reservations"
							:key="r.id"
							class="text-xs leading-tight rounded-sm p-1 mt-0 mb-1"
							>
							{{ r.id }}
							{{ r.reservedEntity }}
							</p>
						</div>
						</div>
					</template>
				</v-calendar>
         </div>
      </div>
      <div class="col-md-1">
      </div>
   </div>
</template>

<script>

import ReservableService from '../services/ReservableService';

export default {
    name: 'Graphics',
    data(){
        return {
			masks: {
				weekdays: 'WWW',
			},			
			reservations: [],
        }
    },
    mounted: function() {
		let user = localStorage.getItem('user')
		let userJSON = JSON.parse(user)
		
		new ReservableService('fishinglesson').getReservationsByOwnerId(userJSON.id).then(response => {
			this.reservations = JSON.parse(JSON.stringify(response.data))
			console.log(this.reservations)
			let reservationsJSON = JSON.parse(JSON.stringify(this.reservations))
			console.log(this.reservations)
			this.reservations = JSON.parse(JSON.stringify(this.reservations))
			console.log(this.reservations)
			let arrayJson = JSON.parse(JSON.stringify(reservationsJSON))
			console.log(arrayJson)
			arrayJson.forEach(this.constructDate)
			
			// let fromDate = dateRangeJSON.fromDate
			console.log(arrayJson)
			this.reservations = arrayJson
			// let date = JSON.parse(JSON.stringify(range))
			// this.reservations.dates = range.fromDate
			
			console.log('ocemo li')
		})
		.catch(err => {
			console.log("greska: " + err)
			if(err.response.status == 403)
			this.$router.push("/unauthorized")
		})
		;
    },
    methods: {
        constructDate(item) {
			item.dates = new Date(item.dateRange.fromDate[0] + ' ' + item.dateRange.fromDate[1] + ' ' + item.dateRange.fromDate[2])
		}
    }
}

</script>
<style lang="postcss" scoped>
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

::-webkit-scrollbar {
  width: 0px;
}
::-webkit-scrollbar-track {
  display: none;
}
/deep/ .custom-calendar.vc-container {
  --day-border: 1px solid #b8c2cc;
  --day-border-highlight: 1px solid #b8c2cc;
  --day-width: 90px;
  --day-height: 90px;
  --weekday-bg: #f8fafc;
  --weekday-border: 1px solid #eaeaea;
  border-radius: 0;
  width: 100%;
  & .vc-header {
    background-color: #f1f5f8;
    padding: 10px 0;
  }
  & .vc-weeks {
    padding: 0;
  }
  & .vc-weekday {
    background-color: var(--weekday-bg);
    border-bottom: var(--weekday-border);
    border-top: var(--weekday-border);
    padding: 5px 0;
  }
  & .vc-day {
    padding: 0 5px 3px 5px;
    text-align: left;
    height: var(--day-height);
    min-width: var(--day-width);
    background-color: white;
    &.weekday-1,
    &.weekday-7 {
      background-color: #eff8ff;
    }
    &:not(.on-bottom) {
      border-bottom: var(--day-border);
      &.weekday-1 {
        border-bottom: var(--day-border-highlight);
      }
    }
    &:not(.on-right) {
      border-right: var(--day-border);
    }
  }
  & .vc-day-dots {
    margin-bottom: 5px;
  }
}
</style>