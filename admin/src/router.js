import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'

Vue.use(Router);
//修改是，严谨格式化，对已有的代码，不要格式化
export default new Router({
    // history指定的跳转路径只返回login，hash指定的返回加入了#/
    mode:'history',
    base:process.env.BASE_URL,
    //path指定跳转地址：输入任何值'*'都会跳转到login
    routes:[{
        path:'*',
        redirect:"/login",
    },{
        path:'',
        redirect:"/login",
    },{
        //component:指明/login跳转加载的组件
        path: "/login",
        component: Login
    },{
        //配置admin路由
        path: "/admin",
        component: Admin
    }]
})