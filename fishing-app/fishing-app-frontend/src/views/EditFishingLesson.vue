<template>
    <div class="white-panel">
		<div class="register-show">
			<h2>Edit fishing lesson</h2>
            <div class="row d-flex mt-5">
                <div class="col-md-12">
                    <label class="input_label">
                        <input type="text" name="name" v-model="fishingLesson.name">
                        <span class="keep_hovered">Name</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="description" v-model="fishingLesson.description">
                        <span class="keep_hovered">Description</span>
                    </label>
                </div>
                
                
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-6">
                    <label class="input_label">
                        <input type="text" name="biography" v-model="fishingLesson.biography">
                        <span class="keep_hovered">Short biography</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-12">
                    <label class="input_label">
                        <input type="text" name="capacity" v-model="fishingLesson.capacity">
                        <span class="keep_hovered">Maximum number of people</span>
                    </label>
                </div>
            </div>
            <div class="row d-flex mt-4">
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="rules" v-model="fishingLesson.rules">
                        <span class="keep_hovered">Rules</span>
                    </label>
                </div>
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="availableEquipment" v-model="fishingLesson.availableEquipment">
                        <span class="keep_hovered">Equipment</span>
                    </label>
                </div>
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="price" v-model="fishingLesson.price">
                        <span class="keep_hovered">Price</span>
                    </label>
                </div>
            </div>
                <div class="col-md-4">
                    <label class="input_label">
                        <input type="text" name="cancelCondition" v-model="fishingLesson.cancelCondition">
                        <span class="keep_hovered">Price for canceling (%)</span>
                    </label>
                </div>
            </div>
			<input type="button" value="Edit fishing lesson" v-on:click="editFishingLesson()"/>
		
        <label for="discount">Discount: </label>
        <input type="text" id="discount" name="discount" v-model="action.discount">
        <label for="a">From: </label>
        <input type="date" id="a" name="a" v-model="action.from">
        <label for="b">To: </label>
        <input type="date" id="b" name="b" v-model="action.to">
        <label for="c">Valid until: </label>
        <input type="date" id="c" name="c" v-model="action.validUntil">
        <input type="button" value="Add action" v-on:click="addAction(action)"/>
		


	</div>
</template>

<script>

import FishingLessonService from '../services/FishingLessonService';

export default {
    name: 'EditFishingLesson',
    data(){
        return {
            fishingLesson: {
            
            },
            action: {}
        }
    },
    mounted: function() {
        this.loadFishingLessonData()
    },
    methods: {
        editFishingLesson() {
			FishingLessonService.editFishingLesson(this.fishingLesson);
        },
        loadFishingLessonData() {

            let user = localStorage.getItem('user')
            let userJSON = JSON.parse(user)
            

            FishingLessonService.getFishingLesson(this.$route.params.id).then(response => {
                if(userJSON.id != response.data.owner.id)
                    this.$router.push("/unauthorized")
                this.fishingLesson = response.data
                this.action.reservableId = this.fishingLesson.id
            })
            .catch(err => {
                console.error(err);
                if(err.response.status == 403)
                    this.$router.push("/unauthorized")
            })
        },
        addAction(action) {
            FishingLessonService.addAction(action);
        }
    }
}

</script>

<style>
    .login-reg-panel{
        position: relative;
        top: 50%;
        transform: translateY(50%);
        text-align:center;
        width:50%;
        right:0;left:0;
        margin:auto;
        height:400px;
        background: rgb(9,53,121);
        background: linear-gradient(90deg, rgba(9,53,121,1) 0%, rgba(9,51,121,1) 35%, rgba(0,95,255,1) 100%);
    }
    .white-panel{
        background-color: rgba(255,255, 255, 1);
        position: relative;
        top: 20%;
        right:0;left:0;
        width:40%;
        margin:auto;
        text-align:center;
        transition:.3s ease-in-out;
        z-index:0;
        box-shadow: 0 0 15px 9px #00000096;
    }
    .login-reg-panel input[type="radio"]{
        position:relative;
        display:none;
    }
    .login-reg-panel{
        color:#B8B8B8;
    }
    .login-reg-panel #label-login, 
    .login-reg-panel #label-register{
        border:1px solid white;
        padding:5px 5px;
        width:150px;
        display:block;
        text-align:center;
        border-radius:10px;
        cursor:pointer;
        font-weight: 600;
        font-size: 18px;
    }
    .login-info-box{
        width:30%;
        padding:0 50px;
        top:20%;
        left:0;
        position:absolute;
        text-align:left;
    }
    .register-info-box{
        width:30%;
        padding:0 50px;
        top:20%;
        right:0;
        position:absolute;
        text-align:left;
        color: whitesmoke
    }
    .right-log{right:50px !important;}

    .register-show{
        z-index: 1;
        transition:0.3s ease-in-out;
        color:#242424;
        text-align:left;
        padding:50px;
    }
    
    .register-show input[type="button"] {
        max-width: 150px;
        width: 100%;
        margin-left: 20px;
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

    .register-show input[type="button"]:disabled {
        transform: scale(110%);
        background: rgb(162, 196, 255);
        color: #f9f9f9;
        border: none;
        padding: 10px;
        text-transform: uppercase;
        border-radius: 10px;
        float:right;
        cursor:auto;
        transition: all .3s;
    }

    .register-show input[type="button"]:hover:enabled {
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

    /* INPUT FIELDS */
    .wrapper {
        max-width: 560px;
        margin: 100px auto;
    }
    label.input_label {
        position: relative;
        display: block;
        margin: 0 0 40px 0;
    }
    label.input_label > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label:hover > input {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid rgb(143, 176, 233);
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label > input:valid {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }
    label.input_label > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label > input:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > input:focus {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }   
    label.input_label > select {
        position: relative;
        background-color: transparent;
        border: none;
        border-bottom: 1px solid #9e9e9e;
        border-radius: 0;
        outline: none;
        height: 45px;
        width: 100%;
        font-size: 16px;
        margin: 0 0 0 0;
        padding: 0;
        box-shadow: none;
        box-sizing: content-box;
        transition: all .3s;
    }
    label.input_label > select:valid {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }
    label.input_label > span {
        color: #9e9e9e;
        position: absolute;
        top: -10px;
        left: 0;
        font-size: 16px;
        cursor: text;
        transition: .2s ease-out;
    }
    label.input_label > select:focus + span {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > select:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > input:valid + span.keep_hovered {
        transform: translateY(-25px) scale(0.8);
        transform-origin: 0;
        color: rgba(0,95,255,1);
    }
    label.input_label > select:focus {
        border-bottom: 1px solid rgba(0,95,255,1);
        box-shadow: 0 1px 0 0 rgba(0,95,255,1);
    }

    /* GENDER SELECTION */  
    input[type="radio"] {
        display: none;
    }

    input[type="radio"] + label {
        z-index: 10;
        margin: 0 10px 10px 0;
        position: relative;
        color: #ced4da;
        text-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
        font-weight: bold;
        background-color: #ffffff;
        border: 2px solid #ced4da;
        cursor: pointer;
        transition: all 200ms ease;
    }

    input[type="radio"].male_option:hover + label {
        color: white;
        background-color: rgb(143, 176, 233);
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].male_option:checked + label {
        color: white;
        background-color: rgba(0,95,255,1);
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].female_option:hover + label {
        color: white;
        background-color: #ffd1d9;
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"].female_option:checked + label {
        color: white;
        background-color: #FB9AAC;
        border: 2px solid white;
        transition: all .3s;
    }

    input[type="radio"] + label {
        padding: 5px 40%;
        border-radius: 10px;
    }
</style>