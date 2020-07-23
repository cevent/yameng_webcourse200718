import Vue from 'vue'
import App from './app' //./app.vue省略
//加入axios
import axios from 'axios'

//引入router
import router from './router'
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

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
