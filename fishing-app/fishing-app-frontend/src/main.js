import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store';
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import FlagIcon from 'vue-flag-icon'
import VCalendar from 'v-calendar';

library.add(fas)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(FlagIcon);
Vue.use(VCalendar);
Vue.use(require('vue-moment'));


Vue.config.productionTip = false
Vue.use(VeeValidate);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
