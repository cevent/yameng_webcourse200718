<template>
    <div>
        <h3 >
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointe blue bigger-110"></i>&nbsp;
            <router-link to="/business/course" class="blue">{{course.name}}</router-link>
        </h3>
        <hr>
        <p>
        <router-link class="btn btn-bold btn-yellow btn-round " to="/business/course">
            <i class="ace-icon fa fa-arrow-left bigger-110"></i>
            返回课程
        </router-link>
        &nbsp;
        <!--新建表单按钮，点击调用  方法-->
        <button class="btn btn-bold btn-success btn-round " v-on:click="add()">
            <i class="ace-icon fa fa-edit bigger-110"></i>
            新增
        </button>
        &nbsp;
        <!--新增刷新列表按钮，点击调用list()方法-->
        <button class="btn btn-bold btn-success btn-round " v-on:click="list(1)">
            <i class="ace-icon fa fa-refresh bigger-110"></i>
            刷新
        </button>
        </p>
        <!--调用pagination组件 ref定义引用名 :list组件内部定义的方法 :list="list"为执行的list方法-->
        <pagination ref="pagination" v-bind:list="list"></pagination>
        <!--设置页面保留8个按钮-->
        <!--        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>-->
        <!--柵格化代码：ctrl+alt+L -->
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>章节ID</th>
                <th>章节名称</th>
                <th class="hidden-480">操作</th>
            </tr>
            </thead>

            <tbody>
            <!--获取数据-->
            <tr v-for="chapter in chapters" :key="chapter.index">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">

                        <button class="btn btn-xs btn-yellow" v-on:click="toSection(chapter)">
                            <i class="ace-icon fa fa-arrows bigger-120">&nbsp;小节</i>
                        </button>

                        <button class="btn btn-xs btn-info" v-on:click="editChapter(chapter)">
                            <i class="ace-icon fa fa-pencil bigger-120">&nbsp;编辑</i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="deleteChapter(chapter.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120">&nbsp;删除</i>
                        </button>

                    </div>
                    <!--hidden-md：中等屏幕隐藏编辑与删除按钮，变为设置，hidden-lg大屏幕可见-->
                    <div class="hidden-md hidden-lg">
                        <div class="inner pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle"
                                    data-toggle="dropdown" data-position="auto">
                                1
                                <i class="ace-icon fa fa-cog icon-only bigger-120"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                       v-on:click="editChapter(chapter)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                       v-on:click="deleteChapter(chapter.id)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-trash bigger-120"></i>
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>


        <!--一个页面同时存在多个modal时，用class选择器时会出现重复，需要给每个modal增加id-->
        <!--1.引入bootstrap-js模态弹窗-->
        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">章节表单</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal">
                            <div class="form-group">
                                <!--for="关联id"-->
                                <label class="col-sm-2 control-label">章节名称</label>
                                <div class="col-sm-10">
                                    <!--v-model的name需要和chapterDto的属性名一致-->
                                    <input v-model="chapter.name" type="text" class="form-control" placeholder="名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <!--for="关联id"-->
                                <label class="col-sm-2 control-label">课程名称</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static" >{{course.name}}</p>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <!--data-dismiss="modal"点击关闭模态框-->
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" v-on:click="addPageChapter()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        name: "chapter",
        components: {Pagination},
        //使用data定义组件内的变量，可用于双向数据绑定（vue核心功能）
        data: function () {
            return {
                chapter: {}, //新增章节的对象
                chapters: [],
                course: {} //接收session-course
            }
        },
        mounted: function () {
            //this.$parent.activeSidebar("business-chapter-sidebar")
            //页面初始化加载methods：list
            let _this = this;
            //分页后，设置每页行数
            _this.$refs.pagination.size = 10;
            //取出session中的course跳转参数，加入 || {}空对象，防止course.**报错
            //let course = SessionStorage.get("course") || {};

            //从大章的session中取出js变量
            let course = SessionStorage.get(SESSION_KEY_COURSE) || {};

            //判断是否为空对象，为空跳转welcome
            if (Tool.isEmpty(course)) {
                _this.$router.push("/welcome");
            }
            _this.course = course;

            _this.list(1);//初始化执行第一页
            //slider样式激活
            this.$parent.activeSidebar("business-course-sidebar");

        },
        methods: {
            //page:currentPage页面变化
            list(page) {
                let _this = this;
                //_this.$ajax.get('http://127.0.0.1:8699/business/admin/chapter/chapterDto?currentPage=1&initPageNum=5').then((responseDTO)=>{

                //调用加载方法
                Loading.show();

                //切换post请求
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/chapterDto',
                    {
                        currentPage: page,
                        //$refs:根据别名，子组件获取子组件，size=获取下拉框数据
                        initPageNum: _this.$refs.pagination.size,
                        courseId:_this.course.id //根据session传入的课程ID筛选章节
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询章节列表：", responseDTO);
                    //获取数据传给data:chapters，加入分页失效，无法获取page信息
                    //_this.chapters=responseDTO.data.list(为html元素对象data返回值对象，接收@RequestBody流对象信息)
                    //0.引入前
                    //_this.chapters = responseDTO.data.list;

                    //1.引入后：responseDto：responseData
                    let resp = responseDTO.data;
                    _this.chapters = resp.responseData.list;
                    //获取分页结果，并把总条数返回，重新渲染
                    _this.$refs.pagination.render(page, resp.responseData.sumPage);
                })
            },
            add() {
                let _this = this;
                console.log("为不引起eslint提醒，调用_list:" + _this);
                //不将上次编辑的数据回显到新建中
                _this.chapter = {};
                //显示模态框
                $("#form-modal").modal("show");
            },
            /**
             * 保存
             */
            addPageChapter: function (page) {
                let _this = this;
                //校验
                if (!Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId, "课程ID", 3, 8)
                ) {
                    return;
                }
                _this.chapter.courseId=_this.course.id;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/addChapter', _this.chapter)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存章节结果：", responseAdd);
                        let resp = responseAdd.data;
                        if (resp.success) {
                            //backdrop:'static'禁止点击空白处关闭modal
                            $("#form-modal").modal('hide');
                            _this.list(1);
                            //添加提示
                            Toast.success("保存成功！");
                        } else {
                            Toast.warning(resp.responseMsg);
                        }
                    })
            },
            editChapter(chapter) {
                let _this = this;

                //数据绑定，引入当前id，需要进行jQuery-extend的引用，{target},复制原数据，不影响页面对象
                _this.chapter = $.extend({}, chapter);
                $("#form-modal").modal("show");
            },
            deleteChapter(id) {
                let _this = this;

                //引入confirm
                Confirm.show("删除章节后不可恢复!", function () {
                    Loading.show();
                    //restFul分割请求，对应controller定义的mapping跳转类型
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delChapter/' + id)
                        .then((responseDel) => {
                            Loading.hide();
                            console.log("删除章节内容：", responseDel);
                            let resp = responseDel.data;
                            if (resp.success) {
                                _this.list(1);
                                Toast.success("删除成功！");
                            }
                        });
                });
            },
            /**
             * 点击【小节】
             */
            toSection(chapter){
                let _this=this;
                //获取session常量
                SessionStorage.set(SESSION_KEY_CHAPTER,chapter);
                _this.$router.push("/business/section")
            }
        }
    }
</script>

<style scoped>

</style>