<template>
  <div>
    <div class="d-flex justify-content-center" style="margin-top:20px; margin-left:40px">
      <div class="filter-panel">
        <div class="filter-panel-parameters">
          <h2>Search</h2>
          <div class="row d-flex mt-5">
            <div class="col-md-4">
                <input type='radio' id='lodgings' checked='checked' name='radio' class="blue_option" v-model="type" value="Lodgings">
                <label for='lodgings'>Lodgings</label>
            </div>
            <div class="col-md-4">
                <input type='radio' id='boats' name='radio' class="blue_option" v-model="type" value="Boats">
                <label for='boats'>Boats</label>
            </div>
            <div class="col-md-4">
                <input type='radio' id='lessons' name='radio' class="blue_option" v-model="type" value="Lessons">
                <label for='lessons'>Lessons</label>
            </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-12">
                  <label class="input_label">
                      <input type="text" name="name" v-model="searchParameters.name">
                      <span class="keep_hovered">Name</span>
                  </label>
              </div>
          </div>
          <div class="row d-flex mt-5">
              <div class="col-md-12">
                  <label class="input_label">
                      <input type="text" name="city" v-model="filterCity">
                      <span class="keep_hovered">City</span>
                  </label>
              </div>
          </div>
          <input type="button" value="Search" v-on:click="search()"/>
        </div>
      </div>
      <div class="row row-cols-md-2" style = "width: 75%; margin-left: 50px" v-for="l in lodgings" :key="l.id">
        <div class="col">
          <article class="entity_card">
            <figure class="card-image">
              <img src="../resources/DefaultLodgingIcon.jpg" alt="" />
            </figure>   
            <div class="card-content">
              <header class="card-header-restaurant">
              <h2>{{l.name}}</h2>
              <br />
              <span>Status</span>   
                <address style="margin-top:10px">
                  <span class="icon-pin" aria-hidden="true"></span>
                  {{getFullAddress(l.address)}}
                </address>
              </header>
            </div>    
            <ul class="card-stats" style="margin-bottom:20px">
              <li>
                <strong>4</strong>
                Articles
              </li>
              <li>
                <strong>5</strong>
                Rating
              </li>
            </ul>   
            <button class="card-button">View</button>
          </article>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import LodgingService from '../services/LodgingService';

export default {
  name: 'Home',
  components: {
    
  },
  data: function () {
    return {
      lodgings: {},
      searchParameters: {},
      currentUser: null,
      name: '',
      type: '',
      location: '',
      rating: '',
      open: false,
      filterName: "",
      filterType: "",
      filterCity: "",
      currentSort:'status',
      currentSortDir:'desc',
      filterRatingFrom: ''
    }
  },
  mounted: function() {
    LodgingService.getLodgings().then(res => {
      this.lodgings = res.data
    });
  },
  methods: {
    getFullAddress: function (address) {
       return address.address + " " + address.city + " " + address.country;
    },
    search() {
        LodgingService.getLodgingsBySearch(this.searchParameters).then(response => {
            this.lodgings = response.data
        })
        .catch(err => {
            console.error(err);
        })
    },
  }
}
</script>

<style>
  .filter-panel{
      background-color: rgba(255,255, 255, 1);
      position: relative;
      margin-left: 20px;
      width:20%;
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
    width: 360px;
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
</style>