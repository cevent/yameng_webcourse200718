<template>
    <div>
        <h1>您好，欢迎登陆亚盟网课后台</h1>
    </div>
</template>

<script>
    export default {
        name: "welcome",
        mounted:function() {
            //设置了admin的跳转及子路由跳转，这里可取消
            //this.$parent.activeSidebar("welcome-sidebar");
        },
        methods:{

        }
    }
</script>

<style scoped>

</style>