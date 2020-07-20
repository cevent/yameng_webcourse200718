import Vue from 'vue'
import App from './app' //./app.vue省略
//引入router
import router from './router'
//引用ES6的编码规范
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
