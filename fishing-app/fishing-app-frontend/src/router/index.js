import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Users from '../views/Users.vue'
import Registration from '../views/Registration.vue'
import UserProfile from '../views/UserProfile.vue'
import OwnerRegistration from '../views/OwnerRegistration.vue'
import Lodging from '../views/Lodging.vue'
import Login from '../views/Login.vue'
import Unauthorized from '../views/Unauthorized.vue'

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
    path: '/user/:id',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/register',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/owner_registration',
    name: 'OwnerRegistration',
    component: OwnerRegistration
  },
  {
    path: '/lodging/:id',
    name: 'Lodging',
    component: Lodging
  },
  {
    path: '/unauthorized',
    name: 'Unauthorized',
    component: Unauthorized
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/','/login', '/register', '/home'];
  const unauthorizedOnlyPages = ['/login', '/register']
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');
  if (authRequired && !loggedIn) {
    next('/login');
  } 
  else if (unauthorizedOnlyPages.includes(to.path) && loggedIn) {
    next('/');
  }
  else {
    next();
  }
});

export default router
