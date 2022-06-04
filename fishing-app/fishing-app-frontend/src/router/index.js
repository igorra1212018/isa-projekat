import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Users from '../views/Users.vue'
import Registration from '../views/Registration.vue'
import UserProfile from '../views/UserProfile.vue'
import UserReservations from '../views/UserReservations.vue'
import ComplaintsView from '../views/ComplaintsView.vue'
import ReservableView from '../views/ReservableView.vue'
import Login from '../views/Login.vue'
import Unauthorized from '../views/Unauthorized.vue'
import AddFishingLesson from '../views/AddFishingLesson.vue'
import AdminViewUsers from '../views/AdminViewUsers.vue'
import AdminReservables from '../views/AdminReservables.vue'
import AdminViewUserRequests from '../views/AdminViewUserRequests.vue'
import AdminRegisterNewAdmin from '../views/AdminRegisterNewAdmin.vue'
import UserViewReservables from '../views/UserViewReservables.vue'
import EditFishingLesson from '../views/EditFishingLesson.vue'
import AdminViewReviews from '../views/AdminViewReviews.vue'
import AdminViewComplaints from '../views/AdminViewComplaints.vue'
import AdminLoyaltyProgram from '../views/AdminLoyaltyProgram.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    redirect: '/home/lodging/all/0',
  },
  {
    path: '/home/:type/all/:page',
    name: 'Home',
    component: Home
  },
  {
    path: '/users',
    name: 'Users',
    component: Users,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/:id',
    name: 'UserProfile',
    component: UserProfile,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/complaints',
    name: 'ComplaintsView',
    component: ComplaintsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/user/reservations/:id',
    name: 'UserReservations',
    component: UserReservations,
    meta: {
      requiresAuth: true
    }
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
    path: '/reservable/:reservable_type/:id',
    name: 'ReservableView',
    component: ReservableView
  },
  {
    path: '/unauthorized',
    name: 'Unauthorized',
    component: Unauthorized
  },
  {
    path: '/addFishingLesson',
    name: 'AddFishingLesson',
    component: AddFishingLesson
  },
  {
    path: '/adminViewUsers',
    name: 'AdminViewUsers',
    component: AdminViewUsers
  },
  {
    path: '/adminReservables',
    name: 'AdminReservables',
    component: AdminReservables
  },
  {
    path: '/adminViewUserRequests',
    name: 'AdminViewUserRequests',
    component: AdminViewUserRequests
  },
  {
    path: '/adminRegisterNewAdmin',
    name: 'AdminRegisterNewAdmin',
    component: AdminRegisterNewAdmin
  },
  {
    path: '/userViewReservables',
    name: 'UserViewReservables',
    component: UserViewReservables
  },
  {
    path: '/reservables/fishinglesson/:id/edit',
    name: 'EditFishingLesson',
    component: EditFishingLesson
  },
  {
    path: '/adminViewReviews',
    name: 'AdminViewReviews',
    component: AdminViewReviews
  },
  {
    path: '/adminViewComplaints',
    name: 'AdminViewComplaints',
    component: AdminViewComplaints
  },
  {
    path: '/adminLoyaltyProgram',
    name: 'AdminLoyaltyProgram',
    component: AdminLoyaltyProgram
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('user');
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!loggedIn) {
      next({ name: 'Login' })
    } else {
      next()
    }
  } else {
    next()
  }
});

export default router
