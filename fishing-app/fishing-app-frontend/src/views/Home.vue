<template>
  <div>
    <div class="d-flex justify-content-center" style="margin-top:20px; margin-left:40px">
      <div class="filter-panel">
        <div class="filter-panel-parameters">
          <h2>Search</h2>
          <div class="row d-flex mt-5">
            <div class="col-md-4">
                <input type='radio' id='lodgings' checked='checked' name='type' class="blue_option" v-model="type" value="lodging">
                <label for='lodgings'><font-awesome-icon icon="fa-solid fa-house" /></label>
            </div>
            <div class="col-md-4">
                <input type='radio' id='boats' name='type' class="blue_option" v-model="type" value="boat">
                <label for='boats'><font-awesome-icon icon="fa-solid fa-ship" size="lg"/></label>
            </div>
            <div class="col-md-4">
                <input type='radio' id='lessons' name='type' class="blue_option" v-model="type" value="fishinglesson">
                <label for='lessons'><font-awesome-icon icon="fa-solid fa-fish-fins" /></label>
            </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-8">
                  <label class="input_label">
                      <input type="text" name="name" v-model="searchParameters.name">
                      <span class="keep_hovered">Name</span>
                  </label>
              </div>
              <div class="col-md-2">
                <input type='radio' id='sort_name_ascending' name='sort' class="blue_option" v-model="currentSort" value="name_ascending">
                <label for='sort_name_ascending'><font-awesome-icon icon="fa-solid fa-sort-up" /></label>
              </div>
              <div class="col-md-2">
                <input type='radio' id='sort_name_descending' name='sort' class="blue_option" v-model="currentSort" value="name_descending">
                <label for='sort_name_descending'><font-awesome-icon icon="fa-solid fa-sort-down" /></label>
              </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-6">
                  <label class="input_label">
                      <select name="countries" id="countries" v-model="searchParameters.location.country">
                        <option :value="defaultCountry">Everywhere</option>
                        <option v-for="c in countries" :key="c.id" :value="c">{{c.name}}</option>
                      </select>
                      <span class="keep_hovered">Country</span>
                  </label>
              </div>
              <div class="col-md-6">
                  <label class="input_label">
                      <input type="text" name="city" v-model="searchParameters.location.city">
                      <span class="keep_hovered">City</span>
                  </label>
              </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-4">
                  <label class="input_label">
                      <input type="number" min="0" name="fromPrice" v-model="searchParameters.fromPrice">
                      <span class="keep_hovered">Price From</span>
                  </label>
              </div>
              <div class="col-md-4">
                  <label class="input_label">
                      <input type="number" min="0" name="toPrice" v-model="searchParameters.toPrice">
                      <span class="keep_hovered">Price To</span>
                  </label>
              </div>
              <div class="col-md-2">
                <input type='radio' id='sort_price_ascending' name='sort' class="blue_option" v-model="currentSort" value="price_ascending">
                <label for='sort_price_ascending'><font-awesome-icon icon="fa-solid fa-sort-up" /></label>
              </div>
              <div class="col-md-2">
                <input type='radio' id='sort_price_descending' name='sort' class="blue_option" v-model="currentSort" value="price_descending">
                <label for='sort_price_descending'><font-awesome-icon icon="fa-solid fa-sort-down" /></label>
              </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-6">
                  <label class="input_label">
                      <input type="date" id="from_date" name="from_date" v-model="searchParameters.dateRange.fromDate">
                      <span class="keep_hovered">Date From</span>
                  </label>
              </div>
              <div class="col-md-6">
                  <label class="input_label">
                      <input type="date" id="to_date" name="to_date" v-model="searchParameters.dateRange.toDate">
                      <span class="keep_hovered">Date To</span>
                  </label>
              </div>
          </div>
          <input type="button" class="blue-button" value="Search" v-on:click="search()"/>
        </div>
      </div>
      <div style="width: 100%; height: 100%">
        <div class="row row-cols-md-3" style = "width: 75%; margin-left: 50px">
          <div class="col" v-for="r in reservables" :key="r.id">
            <article class="entity_card">
              <figure class="card-image">
                <img :src="convertImageToBase64(r.primaryImage.data)" alt="" >
              </figure>   
              <div class="card-content">
                <header class="card-header-restaurant">
                <h2>{{r.name}}</h2>
                <br />
                <span>Status</span>   
                  <address style="margin-top:10px">
                    <span class="icon-pin" aria-hidden="true"></span>
                    {{getFullAddress(r.address)}} <flag :iso="r.address.country.iso" />
                  </address>
                </header>
              </div>    
              <ul class="card-stats" style="margin-bottom:20px">
                <li>
                  <strong>{{r.capacity}}<font-awesome-icon icon="fa-solid fa-person" /></strong>
                  Capacity
                </li>
                <li>
                  <strong>{{r.price}}$/<font-awesome-icon icon="fa-solid fa-sun" /></strong>
                  Price
                </li>
                <li>
                  <strong>{{r.reviews.length}}</strong>
                  Reviews
                </li>
                <li>
                  <strong>{{averageRating(r)}}</strong>
                  Rating
                </li>
              </ul>   
              <button class="card-button" v-on:click="viewReservable(r)">View</button>
            </article>
          </div>
        </div>
        <nav style="width: 50%; margin: 0 auto;">
          <ul class="pagination">
            <li class="page-item" v-if="!firstPage"><a class="page-link" v-on:click="goToPage(getPreviousPage())">Previous</a></li>
            <li class="page-item" v-for="p in totalPages" :key="p"><a class="page-link" v-on:click="goToPage(getPage(p))">{{p}}</a></li>
            <li class="page-item" v-if="!lastPage"><a class="page-link" v-on:click="goToPage(getNextPage())">Next</a></li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>

import ReservableService from '../services/ReservableService';
import UserService from '../services/UserService';

export default {
  name: 'Home',
  components: {
    
  },
  data: function () {
    return {
      reservables: {},
      reservableService: {},
      searchParameters: {
        name: '',
        location: {},
        dateRange: {},
      },
      currentUser: null,
      countries: [],
      defaultCountry: {
        id: 0,
        name: "Everywhere"
      },
      type: '',
      currentSort:'name_ascending',
      totalPages: 1,
      firstPage: false,
      lastPage: false
    }
  },
  mounted: function() {
    this.loadData();
  },
  watch: {
    '$route.params': {
      handler(newRoute, oldRoute) {
        if(newRoute != oldRoute) {
          this.reservableService = new ReservableService(this.$route.params.type)
          this.reservableService.getAllReservablesSearch(this.searchParameters, this.$route.params.page).then(res => {
            this.reservables = res.data.content
            this.totalPages = res.data.totalPages
            this.firstPage = res.data.first
            this.lastPage = res.data.last
          });
        }
      },
      deep: true
    }
  },
  methods: {
    loadData() {
      this.reservableService = new ReservableService(this.$route.params.type)
      this.type = this.$route.params.type
      UserService.getCountries().then((response) => {
        this.countries = response.data;   
      });
      this.reservableService.getAllReservablesSearch(this.searchParameters, this.$route.params.page).then(res => {
        this.reservables = res.data.content
        this.totalPages = res.data.totalPages
        this.firstPage = res.data.first
        this.lastPage = res.data.last
      });
    },
    getFullAddress: function (address) {
       return address.address + " " + address.city + " " + address.country.name;
    },
    search() {
        this.searchParameters.sortType = this.currentSort.split("_")[0]
        this.searchParameters.sortDir = this.currentSort.split("_")[1]
        if(this.searchParameters.dateRange.fromDate)
          this.searchParameters.dateRange.fromDate = new Date(this.searchParameters.dateRange.fromDate)
        if(this.searchParameters.dateRange.toDate)
          this.searchParameters.dateRange.toDate = new Date(this.searchParameters.dateRange.toDate)
        this.reservableService.getAllReservablesSearch(this.searchParameters, this.$route.params.page).then(res => {
          this.reservables = res.data.content
          this.totalPages = res.data.totalPages
          this.firstPage = res.data.first
          this.lastPage = res.data.last
          let oldType = this.$route.params.type
          console.log(oldType)
          console.log(this.type)
          this.$router.push("/home/" + this.type + "/all/0")
        })
    },
    viewReservable(reservable) {
      window.location.href = "http://localhost:8081/reservable/" + this.$route.params.type.split("?")[0] + "/" + reservable.id;
    },
    convertImageToBase64(byteArray) {
        return 'data:image/jpeg;base64,' + byteArray;
    },
    averageRating(r) {
        if(!r.reviews || r.reviews.length == 0)
            return "-"
        let reviewSum = 0;
        for (const element of r.reviews) { 
            reviewSum +=  element.rating
        }
        return reviewSum/r.reviews.length
    },
    getPreviousPage() {
      return parseInt(this.$route.params.page) - 1
    },
    getNextPage() {
      return parseInt(this.$route.params.page) + 1
    },
    getPage(p) {
      return parseInt(p) - 1
    },
    goToPage(page) {
      this.$router.push("/home/" + this.$route.params.type + "/all/" + page)
    }
  }
}
</script>

<style>
  .filter-panel{
      background-color: rgba(255,255, 255, 1);
      position: relative;
      margin-left: 20px;
      width:30%;
      text-align:center;
      transition:.3s ease-in-out;
      z-index:0;
      box-shadow: 0 0 15px 9px #00000096;
  }
  .filter-panel-parameters{
      z-index: 1;
      transition:0.3s ease-in-out;
      color:#242424;
      text-align:left;
      padding:50px;
  }
  .entity_card {
    position: relative;
    max-width: 360px;
    padding-bottom: 1.3125em;
    margin-bottom: 60px;
    color: #424242;
    font-size: 1rem;
    background-color: #fff;
    border-radius: .3125em;
    box-shadow: 0 .625em 2em 0 #000000;
  }
  .card-image {
    border-radius: .3125em .3125em 0 0;
    width: 100%;
    height: 202px;
    margin: 0;
    overflow: hidden;
  }
  .card-image img {
    display: block;
    width: 100%;
  }
  .card-content {
    padding: 1.3125em;
  }
  .card-header {
    padding-bottom: 1.3125em;
    text-align: center;
    background-image: linear-gradient(to right,rgba(236, 48, 20) 0%,rgba(250, 30, 20, 0.9) 100%);
    color: white
  }
  .card-header-restaurant {
    padding-bottom: 1.3125em;
    text-align: center;
  }
  .card-header h2 {
    margin-top: 0;
  }
  .card-header address {
    margin-bottom: 1em;
    font-style: normal;
  }
  .card-header address .icon-pin {
    color: #d77bfc;
  }
  .card-header span {
    font-size: .8em;
  }
  .card-header + .card-stats {
    border-top: 1px solid #f2f2f2;
  }
  .card-stats {
    padding-top: 1.3125em;
    padding-left: 0;
    margin: 0;
    text-align: center;
  }
  .card-stats li {
    display: inline-block;
    margin: 0 1em;
    font-size: .75em;
    vertical-align: middle;
  }
  .card-stats [class^="icon-"] {
    display: block;
    margin: 0 auto;
    color: #aaa;
    font-size: 2em;
  }
  .card-stats strong {
    display: block;
    font-size: 2.5em;
    margin: .3em 0 .1em;
  }
  .card-button {
    position: absolute;
    bottom: -1.3125em;
    left: 50%;
    transform: translateX(-50%);
    border: 0;
    padding: .75em 3em;
    color: #fff;
    font-size: 1em;
    font-weight: 700;
    white-space: nowrap;
    background: rgba(0,95,255,1);
    border-radius: 1.5em;
    box-shadow: 0 .5em 1em 0 #000000;
    cursor: pointer;
  }
  .card-button:hover, .card-button:focus {
    background: rgba(0,95,255,1);
  }
  .card-button:active {
    background: rgba(0,95,255,1);
  }
  .form {
    position: fixed;
    top: 0;
    left: 0;
    padding: 1rem;
    background-color: #fff;
    border-bottom-right-radius: .5rem;
    box-shadow: 0 0 .5rem #000000;
  }
  .form label {
    font-size: 1;
    font-weight: bold;
    display: block;
    margin-bottom: .5em;
  }
  .form input {
    width: 100%;
  }
  .center {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
  }
  .icon-pin {
    color: inherit;
    font-size: 1em;
    position: relative;
    display: inline-block;
    margin-left: .33333em;
    margin-top: .16666em;
    width: 1em;
    height: 1em;
    border: solid .08333em currentColor;
    border-radius: .58333em .58333em .58333em 0;
    background-color: currentColor;
    transform: rotate(-45deg);
  }
  .icon-pin:before {
    content: '';
    position: absolute;
    left: .25em;
    top: .25em;
    width: .33333em;
    height: .33333em;
    color: white;
    border: solid .08333em currentColor;
    border-radius: .25em;
    background-color: currentColor;
  }
  .restaurant-grid {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-evenly;
  }
  .restaurant-grid__item {
    width: 100%;
  }
  .hidden_label {
    display: none;
    height: 0px
  }
  input[type="radio"].blue_option:hover + label {
      color: white;
      text-align: left;
      background-color: rgb(143, 176, 233);
      border: 2px solid white;
      transition: all .3s;
  }
  input[type="radio"].blue_option:checked + label {
      color: white;
      background-color: rgba(0,95,255,1);
      border: 2px solid white;
      transition: all .3s;
  }
  .blue-button {
      max-width: 150px;
      width: 100%;
      background: rgba(0,95,255,1);
      color: #f9f9f9;
      border: none;
      padding: 10px;
      text-transform: uppercase;
      border-radius: 10px;
      float:right;
      cursor:pointer;
      transition: all .3s;
  }
  .blue-button:hover {
      transform: scale(110%);
      background: rgba(0,95,255,1);
      color: #f9f9f9;
      border: none;
      padding: 10px;
      text-transform: uppercase;
      border-radius: 10px;
      float:right;
      cursor:pointer;
      transition: all .3s;
  }
</style>