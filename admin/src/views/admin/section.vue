<template>
    <div>
        <h3 >
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointe blue bigger-110"></i>&nbsp;
            <router-link to="/business/course" class="blue">{{course.name}}</router-link>
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointe blue bigger-110"></i>&nbsp;
            <router-link to="/business/chapter" class="blue">{{chapter.name}}</router-link>
        </h3>
        <hr>
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

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                        <th>小节ID</th>
                        <th>小节标题</th>
                        <th>视频地址</th>
                        <th>时长-单位秒</th>
                        <th>收费-C收费-F免费</th>
                        <th>顺序</th>
                <th class="hidden-480">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="section in sections" :key="section.index">
                        <td>{{section.id}}</td>
                        <td>{{section.title}}</td>
                        <td>{{section.videoAdd}}</td>
                        <td>{{section.time | formatSecond}}</td>
                        <!-- CHARGE=list | 表示过滤器引用 option=section.value,section.value -->
                        <td>{{SECTION_CHARGE | optionKV(section.charge)}}</td>
                        <td>{{section.sort}}</td>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(section)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>
                        <button class="btn btn-xs btn-danger" v-on:click="del(section.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inner pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle"
                                    data-toggle="dropdown" data-position="auto">
                                1
                                <i class="ace-icon fa fa-cog icon-only bigger-120"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="edit(section)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120" ></i>
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="del(section.id)">
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

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog" >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">小节表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">标题</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="section.title"
                                                    type="text" class="form-control"
                                                    placeholder="标题" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">课程名称</label>
                                        <div class="col-sm-10">
                                            <p class="form-control-static">{{course.name}}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">章节名称</label>
                                        <div class="col-sm-10">
                                            <p class="form-control-static">{{chapter.name}}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">视频地址</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="section.videoAdd"
                                                    type="text" class="form-control"
                                                    placeholder="视频地址" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">时长-单位秒</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="section.time"
                                                    type="text" class="form-control"
                                                    placeholder="时长-单位秒" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">是否收费</label>
                                        <div class="col-sm-10">
                                            <select v-model="section.charge" class="form-control">
                                                <option v-for="o in SECTION_CHARGE" :key="o.index" v-bind:value="o.key">{{o.value}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">顺序</label>
                                        <div class="col-sm-10">
                                            <input
                                                    v-model="section.sort"
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
        name: "business-section",
        components: {Pagination},
        data: function () {
            return {
                section:{},
                sections: [],
                SECTION_CHARGE:SECTION_CHARGE,
                course:{},
                chapter:{},
            }
        },
        mounted: function () {
            let _this = this;
            _this.$refs.pagination.size=10;
            let course=SessionStorage.get("course") || {};
            let chapter=SessionStorage.get("chapter") || {};
            if(Tool.isEmpty(course) || Tool.isEmpty(chapter)){
                _this.$router.push("/welcome");
            }
            _this.course=course;
            _this.chapter=chapter;

            _this.list(1);
            //slider样式激活
            this.$parent.activeSidebar("business-course-sidebar");

        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size,
                        courseId:_this.course.id,
                        chapterId:_this.chapter.id,
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询小节列表：", responseDTO);
                    let resp=responseDTO.data;
                    _this.sections = resp.responseData.list;
                    _this.$refs.pagination.render(page,resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add(){
                let _this=this;
                console.log("为不引起eslint提醒，调用_list:"+_this);
                _this.section={};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save(page) {
                let _this = this;
                _this.section.video="";
                //保存校验，1!=1去掉自动生成的代码第一个||或
                if(1 != 1
                                || !Validator.require(_this.section.title,"标题")
                                || !Validator.length(_this.section.title,"标题",3,50)
                                || !Validator.length(_this.section.videoAdd,"视频地址",3,200)
                ){
                    return;
                }
                //传入section
                _this.section.courseId=_this.course.id;
                _this.section.chapterId=_this.chapter.id;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/section/save', _this.section)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存小节结果：", responseAdd);
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
            edit(section){
                let _this=this;

                _this.section=$.extend({},section);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id){
                let _this=this;
                Confirm.show("删除小节后不可恢复!",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/section/delete/'+id)
                        .then((responseDel)=>{
                            Loading.hide();
                            console.log("删除小节内容：",responseDel);
                            let resp=responseDel.data;
                            if(resp.success){
                                _this.list(1);
                                Toast.success("删除成功！");
                            }
                        });
                });
            },
        }
    }
</script>

<style scoped>

</style>