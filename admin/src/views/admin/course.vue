<template>
    <div>
        <p>
            <button class="btn btn-bold btn-success btn-round " v-on:click="add()">
                <i class="ace-icon fa fa-edit bigger-110"></i>
                新增
            </button>&nbsp;
            <button class="btn btn-bold btn-success btn-round " v-on:click="list(1)">
                <i class="ace-icon fa fa-refresh bigger-110"></i>
                刷新
            </button>
        </p>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

        <div class="row">
            <div class="col-md-4" v-for="course in courses" :key="course.index">
                <div class="thumbnail search-thumbnail">
                    <!--如果图片为空则显示默认图片-->
                    <img v-show="!course.image" class="media-object" src="/static/image/电商社交新零售.jpg"/>
                    <img v-show="course.image" class="media-object" v-bind:src="course.image"/>
                    <div class="caption">
                        <div class="clearfix">
                            <span class="pull-right label label-primary info-label">
                                {{COURSE_LEVEL | optionKV(course.level)}}
                            </span>
                            <span class="pull-right label label-success info-label">
                                {{COURSE_CHARGE | optionKV(course.charge)}}
                            </span>
                            <span class="pull-right label label-warning info-label">
                                {{COURSE_STATUS | optionKV(course.status)}}
                            </span>
                        </div>

                        <h3 class="search-title">
                            <a href="#" class="blue">{{course.name}}</a>
                        </h3>
                        <p>
                            <span class="blue bolder bigger-150 ">{{course.price}}&nbsp;
                                <i class="fa fa-rmb"></i>
                            </span>
                        </p>
                        <p>{{course.summary}}</p>
                        <p>
                            <span class="badge badge-info">课程ID：{{course.id}}</span>&nbsp;
                            <span class="badge badge-info">排序：{{course.sort}}</span>&nbsp;
                            <span class="badge badge-info">时长：{{course.time | formatSecond}}</span>
                        </p>
                        <p>
                            <button class="btn btn-xs btn-yellow" v-on:click="toChapter(course)">
                                <i class="ace-icon fa fa-arrows bigger-120">&nbsp;章节</i>
                            </button>&nbsp;
                            <button class="btn btn-xs btn-success" v-on:click="editContent(course)">
                                <i class="ace-icon fa fa-edit bigger-120">&nbsp;内容</i>
                            </button>&nbsp;
                            <button class="btn btn-xs btn-primary" v-on:click="openSortModule(course)">
                                <i class="ace-icon fa fa-sort bigger-120">&nbsp;排序</i>
                            </button>&nbsp;
                            <button class="btn btn-xs btn-info" v-on:click="edit(course)">
                                <i class="ace-icon fa fa-pencil bigger-120">&nbsp;编辑</i>
                            </button>&nbsp;
                            <button class="btn btn-xs btn-danger" v-on:click="del(course.id)">
                                <i class="ace-icon fa fa-trash-o bigger-120">&nbsp;删除</i>
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">课程表表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <!--增加zTree分类-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label">
                                    分类
                                </label>
                                <div class="col-sm-10">
                                    <ul id="tree" class="ztree"></ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程名</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.name"
                                            type="text" class="form-control"
                                            placeholder="课程名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程概述</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.summary"
                                            type="text" class="form-control"
                                            placeholder="课程概述">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">时长 </label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.time"
                                            type="text" class="form-control"
                                            placeholder="时长 ">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">价格（元）</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.price"
                                            type="text" class="form-control"
                                            placeholder="价格（元）">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">封面</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.image"
                                            type="text" class="form-control"
                                            placeholder="封面">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">级别</label>
                                <div class="col-sm-10">
                                    <select v-model="course.level" class="form-control">
                                        <option v-for="L in COURSE_LEVEL" :key="L.index" v-bind:value="L.key">
                                            {{L.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">收费</label>
                                <div class="col-sm-10">
                                    <select v-model="course.charge" class="form-control">
                                        <option v-for="C in COURSE_CHARGE" :key="C.index" v-bind:value="C.key">
                                            {{C.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-10">
                                    <select v-model="course.status" class="form-control">
                                        <option v-for="S in COURSE_STATUS" :key="S.index" v-bind:value="S.key">
                                            {{S.value}}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">报名数</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.enroll"
                                            type="text" class="form-control"
                                            placeholder="报名数">
                                </div>
                            </div>
                            <div class="form-group">
                                <!--修改顺序为不可编辑-->
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="course.sort"
                                            type="text" class="form-control"
                                            disabled>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" v-on:click="save()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <div id="course-content-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">课程内容编辑</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!--显示最后保存的时间-->
                                    {{saveContentIntervalLabel}}
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <!--富文本框位置-->
                                    <div id="courseContent"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" v-on:click="saveCourseContent()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.content.modal -->
        <div id="course-sort-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">课程内容编辑</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="control-label col-lg-3">
                                    当前排序
                                </div>
                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.oldSort" name="oldSort" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="control-label col-lg-3">
                                    新排序
                                </div>
                                <div class="col-lg-9">
                                    <input class="form-control" v-model="sort.newSort" name="newSort">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" v-on:click="saveSort()">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.sort.modal -->
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        name: "business-course",
        components: {Pagination},
        data: function () {
            return {
                course: {},
                courses: [],
                COURSE_LEVEL: COURSE_LEVEL,
                COURSE_CHARGE: COURSE_CHARGE,
                COURSE_STATUS: COURSE_STATUS,
                categoryDtos:[],
                tree:{},
                saveContentIntervalLabel:"",
                sort:{
                    id:"", //传入course-id
                    oldSort:0,
                    newSort:0
                }
            }
        },
        mounted: function () {
            let _this = this;
            _this.$refs.pagination.size = 10;
            _this.allCategory();
            _this.list(1);

        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询课程表列表：", responseDTO);
                    let resp = responseDTO.data;
                    _this.courses = resp.responseData.list;
                    _this.$refs.pagination.render(page, resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                //点击新增取消选中
                _this.tree.checkAllNodes(false);
                console.log("为不引起eslint提醒，调用_list:" + _this);
                //新增sort设置；当前分页的总条数+1
                _this.course = {
                    sort:_this.$refs.pagination.total+1
                };
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save(page) {
                let _this = this;

                //保存校验，1!=1去掉自动生成的代码第一个||或
                if (1 != 1
                    || !Validator.require(_this.course.name, "课程名")
                    || !Validator.length(_this.course.name, "课程名", 3, 50)
                    || !Validator.length(_this.course.summary, "课程概述", 3, 2000)
                    || !Validator.length(_this.course.image, "封面", 3, 100)
                ) {
                    return;
                }
                //调用请求之前：获取选中的tree分支
                let categoryDtos=_this.tree.getCheckedNodes();
                if(Tool.isEmpty(categoryDtos)){
                    Toast.warning("请先选择分类!");
                    return;
                }
                console.log("categorys-tree: "+categoryDtos);
                _this.course.categoryDtos=categoryDtos;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save', _this.course)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存课程表结果：", responseAdd);
                        let resp = responseAdd.data;
                        if (resp.success) {
                            $("#form-modal").modal('hide');
                            _this.list(1);
                            //添加提示
                            Toast.success("保存成功！");
                        } else {
                            Toast.warning(resp.responseMsg);
                        }
                    })
            },
            /**
             * 点击【编辑】
             */
            edit(course) {
                let _this = this;
                //查找当前course勾选的分类
                _this.course = $.extend({}, course);
                _this.listCategorys(course.id);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除课程表后不可恢复!", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id)
                        .then((responseDel) => {
                            Loading.hide();
                            console.log("删除课程表内容：", responseDel);
                            let resp = responseDel.data;
                            if (resp.success) {
                                _this.list(1);
                                Toast.success("删除成功！");
                            }
                        });
                });
            },
            /**
             * 点击【章节】
             */
            toChapter(course) {
                /**
                 * key值不要写字符串，后面缓存用的越来越多，key名有可能冲突。如果key名要变，所有get、set都需要改
                 * 使用工具类session-storage.js
                 * @type {default.methods}
                 * @private SESSION_KEY_COURSE放入sesstion缓存
                 * ①chapter取变量（mounted）的时候，也使用该变量名
                 * ②chapter跳到to section的时候使用该变量名SESSION_KEY_CHAPTER
                 * ③section取变量（mounted）的时候，也使用该变量名SESSION_KEY_CHAPTER
                 */
                let _this = this;
                //SessionStorage.set("course", course);
                SessionStorage.set(SESSION_KEY_COURSE, course);
                _this.$router.push("/business/chapter");
            },
            /**
             * 引入【zTree】
             */
            initTree(){
                //申明引入this下的category
                let _this=this;
                let settings={
                    check:{
                        enable:true
                    },
                    data:{
                        simpleData:{
                            //增加parentID的配置
                            idKey:"id",
                            pIdKey:"parent",
                            rootPId:"00000000",
                            enable: true
                        }
                    }
                };
                //引入initTree()调取的category
                let zNodes=_this.categoryDtos;
                _this.tree=$.fn.zTree.init($("#tree"),settings,zNodes);
                //初始化后，展开所有节点
                _this.tree.expandAll(true);

            },
            /**
             * 引用查询分类方法
             */
            allCategory() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all')
                    .then((responseDTO) => {
                        Loading.hide();
                        console.log("查询分类表列表：", responseDTO);
                        let resp = responseDTO.data;
                        _this.categoryDtos = resp.responseData;
                        //调用初始化tree
                        _this.initTree();
                    })
            },
            /**
             * 查找课程下所有分类
             */
            listCategorys(courseId){
                let _this=this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list-course-category/'+courseId)
                .then((responseDTO)=>{
                    Loading.hide();
                    console.log("查看当前课程下所有分类结果：",responseDTO);
                    let response=responseDTO.data;
                    let categoryDtos=response.responseData;
                    //勾选查询到的分类
                    _this.tree.checkAllNodes(false);
                    for (let i=0;i<categoryDtos.length;i++){
                        let node=_this.tree.getNodeByParam("id",categoryDtos[i].categoryId);
                        _this.tree.checkNode(node,true);
                    }
                })
            },
            /**
             * 编辑内容按钮：打开编辑框
             */
            editContent(course){
                let _this=this;
                let id=course.id;
                _this.course=course;
                $("#courseContent").summernote({
                    focus:true,
                    height:300
                });
                //先清空历史内容
                $("#courseContent").summernote('code','');
                //再清空interval自动保存的时间
                _this.saveContentIntervalLabel="";

                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/admin/course/find-course-content/'+id)
                .then((responseDto)=>{
                    Loading.hide();
                    let response=responseDto.data;
                    if(response.success){
                        //保存成功不关闭内容框
                        $("#course-content-modal").modal({backdrop:'static',keyboard:false});
                        if(response.responseData){
                            //写入内容库
                            $("#courseContent").summernote('code',response.responseData.content);
                        }

                        //开启定时自动保存5s
                        let saveContentInterval=setInterval(function () {
                            _this.saveCourseContent();
                        },5000);
                        //关闭内容框，清空自动保存内容
                        $('#course-content-modal').on('hidden.bs.modal',function (removeAuto) {
                            clearInterval(saveContentInterval);
                        });

                    }else{
                        Toast.warning(response.responseMsg);
                    }
                });
            },
            /**
             * 插入课程内容
             */
            saveCourseContent(){
                let _this=this;
                //读取文本框代码
                let content=$("#courseContent").summernote("code");
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save-course-content',
                    {
                        id:_this.course.id,
                        content:content
                    })
                .then((responseSave)=>{
                    Loading.hide();
                    let response=responseSave.data;
                    if(response.success){
                        let now=Tool.dateFormat("yyyy-MM-dd hh:mm:ss");
                        _this.saveContentIntervalLabel="[ 最后保存时间：] "+now;
                        //Toast.success("课程内容保存成功！");
                    }else{
                        Toast.warning(response.responseMsg);
                    }
                });
            },
            /**
             * 打开排序module
             */
            openSortModule(course){
                let _this=this;
                //初始化sort
                _this.sort={
                    id:course.id,
                    oldSort:course.sort,
                    newSort:course.sort
                };
                $("#course-sort-modal").modal("show");
            },
            /**
             * 更新排序
             */
            saveSort(){
                let _this=this;
                if(_this.sort.newSort===_this.sort.oldSort){
                    Toast.warning("排序没有变化");
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/update-sort',_this.sort)
                .then((responseSort)=>{
                    let response=responseSort.data;
                    if(response.success){
                        Toast.success("更新排序成功");
                        $("#course-sort-modal").modal("hide");
                        _this.list(1);
                    }else{
                        Toast.error("更新排序失败");
                    }
                })
            }
        }
    }
</script>

<style scoped>
    /*scoped：样式只应用于当前组件
    */
    .caption h3 {
        font-size: 24px;
    }

</style>