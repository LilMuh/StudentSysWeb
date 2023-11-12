import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from "@/views/LoginView.vue";
import AdminView from "@/views/AdminView.vue";
import LayoutView from "@/views/Layout.vue";
import RegisterView from "@/views/RegisterView.vue";
import ForgetView from "@/views/ForgetView.vue";
import ChangePasswordView from "@/views/ChangePWView.vue";
import EssayView from "@/views/EssayView";

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
    path: '/forget',
    name: 'forget',
    component: ForgetView
  },
  {
    path: '/changePassword',
    name: 'changePassword',
    component: ChangePasswordView
  },
  {
    path: '/',
    name: '',
    component: LayoutView,
    children:[
      {
        path: '',
        name: 'home',
        component: HomeView
      },
      {
        path: 'admin',
        name: 'admin',
        component: AdminView
      },
      {
        path: 'Essay',
        name: 'essay',
        component: EssayView
      },
      {
        path: 'Design',
        name: 'design',
        component: HomeView
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// Router guard
router.beforeEach((to, from, next) =>{
  if(to.path==='/login'||to.path==='/register'){
    next();
  }
  const user = localStorage.getItem("user");
  if(!user && (
      to.path!=='/login'&&
      to.path!=='/register'&&
      to.path!=='/forget'&&
      to.path!=='/changePassword')
  ){
    return next("/login")
  }
  next();
})

export default router