import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login'
import Admin from './views/admin'
import Welcome from './views/admin/welcome'
import Chapter from "./views/admin/chapter";
import Section from "./views/admin/section";
import Course from "./views/admin/course";

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
        path: "/",
        name: "admin",
        component: Admin,
        children:[{
            path: "welcome",
            name: "welcome",
            component: Welcome
        },{
            //配置章节路由chapter
            path: "business/chapter",
            name: "business/chapter",
            component: Chapter
        },{
            //配置小节路由section
            path: "business/section",
            name: "business/section",
            component: Section
        },{
            //配置课程路由course
            path: "business/course",
            name: "business/course",
            component: Course
        }]
    }]
})