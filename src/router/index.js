import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import AdminView from "@/views/AdminView.vue";
import LayoutView from "@/views/Layout.vue";
import RegisterView from "@/views/RegisterView.vue";

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: LoginView,
    children: []
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/',
    name: 'Layout',
    component: LayoutView,
    children:[
      {
        path: '',
        name: 'home',
        component: HomeView
      },
      {
        path: 'admin',
        name: 'about',
        component: AdminView
      },
      {
        // havent done
        path: 'user',
        name: 'home',
        component: HomeView
      },
      {
        // havent done
        path: 'StudentInfo',
        name: '',
        component: HomeView
      },
      {
        // havent done
        path: 'StaffInfo',
        name: '',
        component: HomeView
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
