import Vue from 'vue'
import App from './app' //./app.vue省略
//加入axios
import axios from 'axios'

//引入router
import router from './router'
//引入过滤器
import filter from "../public/static/js/filter";

//引用ES6的编码规范
Vue.config.productionTip = false
//prototype为vue的全局变量，使用this.propxxx获取值，$代表全局属性的约定,将$ajax设置为内置属性=axios
Vue.prototype.$ajax=axios;
/**
 * axios拦截器:打印日志
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：",config);
  return config;
},error => {});
axios.interceptors.response.use(function (response) {
  console.log("响应结果：",response);
  return response;
},error => {});
/**
 * 增加filter.js全局过滤器
 */
Object.keys(filter).forEach(key=> {
  Vue.filter(key,filter[key])
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

//代码中使用环境变量
console.log("环境：",process.env.NODE_ENV);
