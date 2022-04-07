import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Users from '../views/Users.vue'
import Registration from '../views/Registration.vue'
import UserProfile from '../views/UserProfile.vue'
import OwnerRegistration from '../views/OwnerRegistration.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/users',
    name: 'Users',
    component: Users
  },
  {
    path: '/user_profile',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/owner_registration',
    name: 'OwnerRegistration',
    component: OwnerRegistration
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
