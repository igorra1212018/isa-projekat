<template>
    <div class="container">
      <h2>Owner Registration Form</h2>
      <form class="form-horizontal">
        <div class="form-group">
          <label class="control-label col-sm-2" for="email">Email:</label>
          <div class="col-sm-10">
            <input type="email" class="form-control" v-model="user.email" id="email" placeholder="Enter email">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="pwd">Password:</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" v-model="user.password" id="password" placeholder="Enter password">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="pwd">Confirm Password:</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" v-model="confirmPassword" id="confirm-password" placeholder="Confirm password">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="fname">First Name:</label>
          <div class="col-sm-10">
            <input type="fname" class="form-control" id="fname" v-model="user.firstName" placeholder="First name">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="lname">Last Name:</label>
          <div class="col-sm-10">
            <input type="lname" class="form-control" id="lname" v-model="user.lastName" placeholder="Last name">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2">Gender:</label>
          <div class="col-sm-10">
            <div class="radio">
              <input type="radio" id="male" name="genderRadio" v-model="user.gender" value="MALE">
              <label for="male">Male</label>
            </div>
            <div class="radio">
              <input type="radio" id="female" name="genderRadio" v-model="user.gender" value="FEMALE">
              <label for="female">Female</label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="country">Country:</label>
          <div class="col-sm-10">
            <input type="country" class="form-control" v-model="user.country" id="country" placeholder="Country">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="city">City:</label>
          <div class="col-sm-10">
            <input type="city" class="form-control" v-model="user.city" id="city" placeholder="City">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="address">Address:</label>
          <div class="col-sm-10">
            <input type="address" class="form-control" v-model="user.address" id="address" placeholder="Address">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="phone">Phone:</label>
          <div class="col-sm-10">
            <input type="phone" class="form-control" v-model="user.contactPhone" id="phone" placeholder="Phone">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2">Application Type:</label>
          <div class="col-sm-10">
            <div class="radio">
              <input type="radio" id="boat_owner" v-model="user.role" name="optradio" value="BOAT_OWNER">
              <label for="boat_owner">Boat Owner</label>
            </div>
            <div class="radio">
              <input type="radio" id="lodging_owner" v-model="user.role" name="optradio" value="LODGING_OWNER">
              <label for="lodging_owner">Lodging Owner</label>
            </div>
            <div class="radio">
              <input type="radio" id="fishing_instructor" v-model="user.role" name="optradio" value="FISHING_INSTRUCTOR">
              <label for="fishing_instructor">Fishing Instructor</label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-sm-2" for="application_details">Application Details:</label>
          <div class="col-sm-10">
            <textarea class="form-control" rows="5" id="comment" v-model="user.applicationDetails" name="application_details"></textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" id="submit" class="btn btn-default" v-on:click.prevent="registerOwner()">Submit</button>
          </div>
        </div>
      </form>
    </div>
</template>

<script>

import UserService from '../services/UserService';

export default {
    name: 'OwnerRegistration',
    data(){
        return {
            user: {},
            confirmPassword: "",
        }
    },
    methods: {
        registerOwner(){
            UserService.registerOwner(this.user);
        }
    }
}

</script>

<style>
  @import 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css';
</style>