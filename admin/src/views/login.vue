<template>
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h1>
                                <i class="ace-icon fa fa-leaf green"></i>
                                <span class="red">亚盟</span>
                                <span class="white" id="id-text2">网课后台管理</span>
                            </h1>
                            <h4 class="blue" id="id-company-text">&copy; 亚盟电商平台 2020-Tech by Cevent</h4>
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee green"></i>
                                            请输入管理员登录名和密码
                                        </h4>

                                        <div class="space-6"></div>

                                        <form>
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <div class="space"></div>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <input type="checkbox" class="ace" />
                                                        <span class="lbl"> 记住我</span>
                                                    </label>

                                                    <!-- v-on:click="" = @click="" -->
                                                    <button
                                                            type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary"
                                                            @click="login()">
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">登录</span>
                                                    </button>
                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>

<!--                                        <div class="social-or-login center"> 去掉微信/QQ扫码登录-->
<!--                                            <span class="bigger-110">Or Login Using</span>-->
<!--                                        </div>-->

<!--                                        <div class="space-6"></div>-->

<!--                                        <div class="social-login center">-->
<!--                                            <a class="btn btn-primary">-->
<!--                                                <i class="ace-icon fa fa-wechat"></i>-->
<!--                                            </a>-->

<!--                                            <a class="btn btn-info">-->
<!--                                                <i class="ace-icon fa fa-qq"></i>-->
<!--                                            </a>-->

<!--                                            <a class="btn btn-danger">-->
<!--                                                <i class="ace-icon fa fa-github"></i>-->
<!--                                            </a>-->
<!--                                        </div>-->
                                    </div><!-- /.widget-main -->

                                    <div class="toolbar clearfix">
                                        <div>
                                            <a href="#" data-target="#forgot-box" class="forgot-password-link">
                                                <i class="ace-icon fa fa-arrow-left"></i>
                                                忘记密码
                                            </a>
                                        </div>

                                        <div>
                                            <a href="#" data-target="#signup-box" class="user-signup-link">
                                                前往注册
                                                <i class="ace-icon fa fa-arrow-right"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->
                        </div>
                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->

</template>

<script>

    //mounted:页面每加载一次执行一次。解决组件初始化样式问题
    export default {
        name:"login",
        mounted:function () {
            $("body").attr("class","login-layout blur-login");
            console.log("login");
        },
        //指定@click=""点击事件，路由跳转的地址
        methods:{
            login(){
                this.$router.push("/admin")
            }
    }
    }
</script>

<style>

</style>
