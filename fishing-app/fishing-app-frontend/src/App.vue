<template>
  <div id="app">
    <nav class="navMenu navbar-dark bg-dark">
      <a href="/" style="float:left">Homepage</a>
      <a :href="getUserReservationsHref" v-if="user">Reservations</a>
      <a :href="getUserProfileHref" v-if="user">Profile</a>
      <a href=/addFishingLesson v-if="isInstructor">Add fishing lesson</a>
      <a href=/adminViewUsers v-if="isAdmin">Users</a>
      <a href=/adminReservables v-if="isAdmin">Admin reservables</a>
      <a href=/userViewReservables v-if="!isAdmin && user">User reservables</a>
      <a href=/adminViewUserRequests v-if="isAdmin && isMainAdmin">Requests</a>
      <a href=/adminViewReviews v-if="isAdmin">Reviews</a>
      <a href=/adminRegisterNewAdmin v-if="isAdmin">Register new admin</a>
      <a href="/" v-if="user" v-on:click="logout()">Logout</a>
      <a href="/login" v-if="!user">Login</a>
    </nav>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
      return {
          user: {},
          isAdmin: 0,
          isMainAdmin: 0,
          isInstructor: 0
      }
  },
  mounted: function() {
      this.setupData()
  },
  computed: {
    getUserProfileHref() {
      return "/user/" + (JSON.parse(this.user)).id
    },
    getUserReservationsHref() {
      return "/user/reservations/" + (JSON.parse(this.user)).id
    }
  },
  methods: {
      logout() {
          localStorage.removeItem('user');
          this.userId = 0;
          this.isAdmin = 0;
          this.isMainAdmin = 0;
          this.isInstructor = 0;
      },
      setupData() {
        this.user = localStorage.getItem('user')
        let userJSON = JSON.parse(this.user)
        console.log(this.user)
        this.isAdmin = userJSON.roles.indexOf('ROLE_ADMINISTRATOR') >= 0
        this.isMainAdmin = userJSON.email == 'admin@gmail.com'
        this.isInstructor = userJSON.roles.indexOf('ROLE_FISHING_INSTRUCTOR') >= 0
        console.log('hola')
        console.log(this.isInstructor)
      }
  },
  watch: {
    $route () {
      this.setupData()
    }
  }
}
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300&display=swap');
@import '~@sum.cumo/vue-datepicker/dist/Datepicker.css';

#app {
  font-family: 'Roboto', sans-serif;
  font-size: 12px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body, html {
  height:100vh;
  min-height:550px;
  margin: 0;
  background-image: url("resources/FishingBackground.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}

.navMenu {
  align-content: right;
  padding-left:20px !important;
  padding-right:20px !important;
  padding-top:3px !important;
  text-align: right;
  width: 100%;
  font-size: 16px;
}

.navMenu a {
  margin-left: 10px;
  margin-right: 10px;
}

.navMenu a {
	color: #f6f4e6;
	text-decoration: none;
	text-transform: uppercase;
	font-weight: 500;
	display: inline-block;
	-webkit-transition: all 0.2s ease-in-out;
	transition: all 0.2s ease-in-out;
}

.navMenu a:hover {
  color: rgba(0,95,255,1);
}

</style>
