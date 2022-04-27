import Vue from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'

/*let token = JSON.parse( localStorage.getItem('token') );
if( token ){
     window.axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
}*/

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
