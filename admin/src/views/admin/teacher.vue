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
            <div v-for="teacher in teachers" :key="teacher.index" class="col-md-3 center">
                <div>
                <span class="profile-picture">
                    <img v-show="!teacher.image" v-bind:title="teacher.introduce"
                         class="editable img-responsive editable-click editable-empty"
                         src="/static/image/yameng-logo.png">
                    <img v-show="teacher.image" v-bind:src="teacher.image" v-bind:title="teacher.introduce"
                         class="editable img-responsive editable-click editable-empty">
                </span>

                    <!--space调整上下间距-->
                    <div class="space-4"></div>

                    <div class="width-85 label label-info label-xlg arrowed-in arrowed-in-right">
                        <div class="inline position-relative">
                            <a href="javascript:;" class="user-title-label dropdown-toggle " data-toggle="dropdown">
                                <i class="ace-icon fa fa-circle light-green"></i>
                                &nbsp;
                                <span class="white">{{teacher.position}}</span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="space-6"></div>
                <div class="text-center">
                    <a href="javascript:;" class="text-info bigger-110" v-bind:title="teacher.motto">
                        <i class="ace-icon fa fa-user"></i>&nbsp;
                        <span class="text-primary">{{teacher.name}} - {{teacher.nickname}}</span>
                    </a>
                </div>

                <div class="space-6"></div>
                <div class="profile-contact-links align-center">
                    <button class="btn btn-xs btn-info" v-on:click="edit(teacher)">
                        <i class="ace-icon fa fa-edit bigger-120">&nbsp;编辑</i>
                    </button>&nbsp;
                    <button class="btn btn-xs btn-danger" v-on:click="del(teacher.id)">
                        <i class="ace-icon fa fa-trash bigger-120">&nbsp;删除</i>
                    </button>&nbsp;
                </div>
                <div class="hr hr16 dotted"></div>
            </div>
        </div>

        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">讲师表表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">讲师姓名</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="teacher.name"
                                            type="text" class="form-control"
                                            placeholder="讲师姓名">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">昵称</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="teacher.nickname"
                                            type="text" class="form-control"
                                            placeholder="昵称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">头像</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="teacher.image"
                                            type="text" class="form-control"
                                            placeholder="头像">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">职位</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="teacher.position"
                                            type="text" class="form-control"
                                            placeholder="职位">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">座右铭</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="teacher.motto"
                                            type="text" class="form-control"
                                            placeholder="座右铭">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">介绍</label>
                                <div class="col-sm-10">
                                    <!--rows设置多行文本高度-->
                                    <textarea v-model="teacher.introduce"
                                    type="text" class="form-control" rows="5"
                                    placeholder="介绍">
                                    </textarea>
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
        </div>
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";

    export default {
        name: "business-teacher",
        components: {Pagination},
        data: function () {
            return {
                teacher: {},
                teachers: [],
            }
        },
        mounted: function () {
            let _this = this;
            _this.$refs.pagination.size = 10;
            _this.list(1);

        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询讲师表列表：", responseDTO);
                    let resp = responseDTO.data;
                    _this.teachers = resp.responseData.list;
                    _this.$refs.pagination.render(page, resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                console.log("为不引起eslint提醒，调用_list:" + _this);
                _this.teacher = {};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save(page) {
                let _this = this;

                //保存校验，1!=1去掉自动生成的代码第一个||或
                if (1 != 1
                    || !Validator.require(_this.teacher.name, "讲师姓名")
                    || !Validator.length(_this.teacher.name, "讲师姓名", 3, 50)
                    || !Validator.length(_this.teacher.nickname, "昵称", 3, 50)
                    || !Validator.length(_this.teacher.image, "头像", 3, 100)
                    || !Validator.length(_this.teacher.position, "职位", 3, 50)
                    || !Validator.length(_this.teacher.motto, "座右铭", 3, 100)
                    || !Validator.length(_this.teacher.introduce, "介绍", 3, 500)
                ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/save', _this.teacher)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存讲师表结果：", responseAdd);
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
            edit(teacher) {
                let _this = this;

                _this.teacher = $.extend({}, teacher);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除讲师表后不可恢复!", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/teacher/delete/' + id)
                        .then((responseDel) => {
                            Loading.hide();
                            console.log("删除讲师表内容：", responseDel);
                            let resp = responseDel.data;
                            if (resp.success) {
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