import Vue from 'vue'
import App from './App.vue'
//引用ES6的编码规范
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
