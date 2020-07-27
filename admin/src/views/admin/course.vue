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
                    <img v-show="!course.image" class="media-object" src="/static/image/电商社交新零售.jpg" />
                    <img v-show="course.image" class="media-object" v-bind:src="course.image" />
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
                            </button>
                            <button class="btn btn-xs btn-info" v-on:click="edit(course)">
                                <i class="ace-icon fa fa-pencil bigger-120">&nbsp;编辑</i>
                            </button>
                            <button class="btn btn-xs btn-danger" v-on:click="del(course.id)">
                                <i class="ace-icon fa fa-trash-o bigger-120">&nbsp;删除</i>
                            </button>
                        </p>
                    </div>
                </div>
            </div>
        </div>

<!--        <table id="simple-table" class="table  table-bordered table-hover">-->
<!--            <thead>-->
<!--            <tr>-->
<!--                                        <th>课程ID</th>-->
<!--                        <th>课程名</th>-->
<!--                        <th>课程概述</th>-->
<!--                        <th>时长 </th>-->
<!--                        <th>价格（元）</th>-->
<!--                        <th>封面</th>-->
<!--                        <th>级别</th>-->
<!--                        <th>收费</th>-->
<!--                        <th>状态</th>-->
<!--                        <th>报名数</th>-->
<!--                        <th>顺序</th>-->
<!--                <th class="hidden-480">操作</th>-->
<!--            </tr>-->
<!--            </thead>-->

<!--            <tbody>-->
<!--            <tr v-for="course in courses" :key="course.index">-->
<!--                        <td>{{course.id}}</td>-->
<!--                        <td>{{course.name}}</td>-->
<!--                        <td>{{course.summary}}</td>-->
<!--                        <td>{{course.time}}</td>-->
<!--                        <td>{{course.price}}</td>-->
<!--                        <td>{{course.image}}</td>-->
<!--                        <td>{{COURSE_LEVEL | optionKV(course.level)}}</td>-->
<!--                        <td>{{COURSE_CHARGE | optionKV(course.charge)}}</td>-->
<!--                        <td>{{COURSE_STATUS | optionKV(course.status)}}</td>-->
<!--                        <td>{{course.enroll}}</td>-->
<!--                        <td>{{course.sort}}</td>-->

<!--                <td>-->
<!--                    <div class="hidden-sm hidden-xs btn-group">-->
<!--                        <button class="btn btn-xs btn-info" v-on:click="edit(course)">-->
<!--                            <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--                        </button>-->
<!--                        <button class="btn btn-xs btn-danger" v-on:click="del(course.id)">-->
<!--                            <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--                        </button>-->
<!--                    </div>-->

<!--                    <div class="hidden-md hidden-lg">-->
<!--                        <div class="inner pos-rel">-->
<!--                            <button class="btn btn-minier btn-primary dropdown-toggle"-->
<!--                                    data-toggle="dropdown" data-position="auto">-->
<!--                                1-->
<!--                                <i class="ace-icon fa fa-cog icon-only bigger-120"></i>-->
<!--                            </button>-->
<!--                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">-->
<!--                                <li>-->
<!--                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="edit(course)">-->
<!--                                        <span class="blue">-->
<!--                                            <i class="ace-icon fa fa-pencil bigger-120" ></i>-->
<!--                                        </span>-->
<!--                                    </a>-->
<!--                                </li>-->
<!--                                <li>-->
<!--                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="del(course.id)">-->
<!--                                        <span class="blue">-->
<!--                                            <i class="ace-icon fa fa-trash bigger-120"></i>-->
<!--                                        </span>-->
<!--                                    </a>-->
<!--                                </li>-->
<!--                            </ul>-->
<!--                        </div>-->
<!--                    </div>-->
<!--                </td>-->
<!--            </tr>-->
<!--            </tbody>-->
<!--        </table>-->

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog" >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">课程表表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">课程名</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.name"
                                                    type="text" class="form-control"
                                                    placeholder="课程名" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">课程概述</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.summary"
                                                    type="text" class="form-control"
                                                    placeholder="课程概述" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">时长 </label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.time"
                                                    type="text" class="form-control"
                                                    placeholder="时长 " >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">价格（元）</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.price"
                                                    type="text" class="form-control"
                                                    placeholder="价格（元）" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">封面</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.image"
                                                    type="text" class="form-control"
                                                    placeholder="封面" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">级别</label>
                                        <div class="col-sm-10">
                                           <select v-model="course.level" class="form-control">
                                               <option v-for="L in COURSE_LEVEL" :key="L.index" v-bind:value="L.key">{{L.value}}</option>
                                           </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">收费</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.charge" class="form-control">
                                                <option v-for="C in COURSE_CHARGE" :key="C.index" v-bind:value="C.key">{{C.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">状态</label>
                                        <div class="col-sm-10">
                                            <select v-model="course.status" class="form-control">
                                                <option v-for="S in COURSE_STATUS" :key="S.index" v-bind:value="S.key">{{S.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">报名数</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.enroll"
                                                    type="text" class="form-control"
                                                    placeholder="报名数" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">顺序</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="course.sort"
                                                    type="text" class="form-control"
                                                    placeholder="顺序" >
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

    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        name: "business-course",
        components: {Pagination},
        data: function () {
            return {
                course:{},
                courses: [],
                COURSE_LEVEL:COURSE_LEVEL,
                COURSE_CHARGE:COURSE_CHARGE,
                COURSE_STATUS:COURSE_STATUS
            }
        },
        mounted: function () {
            let _this = this;
            _this.$refs.pagination.size=10;
            _this.list(1);

        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询课程表列表：", responseDTO);
                    let resp=responseDTO.data;
                    _this.courses = resp.responseData.list;
                    _this.$refs.pagination.render(page,resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add(){
                let _this=this;
                console.log("为不引起eslint提醒，调用_list:"+_this);
                _this.course={};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save(page) {
                let _this = this;

                //保存校验，1!=1去掉自动生成的代码第一个||或
                if(1 != 1
                                || !Validator.require(_this.course.name,"课程名")
                                || !Validator.length(_this.course.name,"课程名",3,50)
                                || !Validator.length(_this.course.summary,"课程概述",3,2000)
                                || !Validator.length(_this.course.image,"封面",3,100)
                ){
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/course/save', _this.course)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存课程表结果：", responseAdd);
                        let resp = responseAdd.data;
                        if (resp.success) {
                            $("#form-modal").modal('hide');
                            _this.list(1);
                            //添加提示
                            Toast.success("保存成功！");
                        }else{
                            Toast.warning(resp.responseMsg);
                        }
                    })
            },
            /**
             * 点击【编辑】
             */
            edit(course){
                let _this=this;

                _this.course=$.extend({},course);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id){
                let _this=this;
                Confirm.show("删除课程表后不可恢复!",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/course/delete/'+id)
                        .then((responseDel)=>{
                            Loading.hide();
                            console.log("删除课程表内容：",responseDel);
                            let resp=responseDel.data;
                            if(resp.success){
                                _this.list(1);
                                Toast.success("删除成功！");
                            }
                        });
                });
            },
            /**
             * 点击【章节】
             */
            toChapter(course){
                let _this=this;
                SessionStorage.set("course",course);
                _this.$router.push("/business/chapter");
            }
        }
    }
</script>

<style scoped>
    /*scoped：样式只应用于当前组件
    */
    .caption h3{
        font-size: 24px;
    }

</style>