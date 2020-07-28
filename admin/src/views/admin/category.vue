<template>
    <div>
        <div class="row">
            <div class="col-md-6">
                <p>
                    <button class="btn btn-bold btn-success btn-round " v-on:click="addLevel1()">
                        <i class="ace-icon fa fa-edit bigger-110"></i>
                        新增一级
                    </button>&nbsp;
                    <button class="btn btn-bold btn-success btn-round " v-on:click="all()">
                        <i class="ace-icon fa fa-refresh bigger-110"></i>
                        刷新
                    </button>
                </p>

                <table id="level1-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>分类ID</th>
                        <th>分类名称</th>
                        <th>顺序</th>
                        <th class="hidden-480">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <!--增加行点击事件，点击level1，显示对应的level2，左右栏显示-->
                    <tr v-for="category in level1" :key="category.index"
                        v-on:click="clickLevel1(category)" v-bind:class="{'active':category.id===active.id}">
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" v-on:click="edit(category)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                                <button class="btn btn-xs btn-danger" v-on:click="del(category.id)">
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
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                               v-on:click="edit(category)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                               v-on:click="del(category.id)">
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
            </div>


            <div class="col-md-6">
                <p>
                    <button class="btn btn-bold btn-success btn-round " v-on:click="addLevel2()">
                        <i class="ace-icon fa fa-edit bigger-110"></i>
                        新增二级
                    </button>&nbsp;
                </p>
                <table id="level2-table" class="table  table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>分类ID</th>
                        <th>分类名称</th>
                        <th>顺序</th>
                        <th class="hidden-480">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <!--增加行点击事件，点击level1，显示对应的level2，左右栏显示-->
                    <tr v-for="category in level2" :key="category.index">
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>

                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-info" v-on:click="edit(category)">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                                <button class="btn btn-xs btn-danger" v-on:click="del(category.id)">
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
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                               v-on:click="edit(category)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="view"
                                               v-on:click="del(category.id)">
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

            </div>
        </div>


        <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">分类表表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">父分类</label>
                                <div class="col-sm-10">
                                    <!--如果没有点击父分类，则(一级父分类)显示无-->
                                    <p class="form-control-static">
                                        {{active.name || "无"}}
                                    </p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">分类名称</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="category.name"
                                            type="text" class="form-control"
                                            placeholder="分类名称">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">
                                    <input
                                            v-model="category.sort"
                                            type="text" class="form-control"
                                            placeholder="顺序">
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
    export default {
        name: "business-category",
        data: function () {
            return {
                category: {},
                categorys: [],
                level1: [],
                level2: [],
                active: {}, //点击添加样式
            }
        },
        mounted: function () {
            let _this = this;
            _this.all();

        },
        methods: {
            /**
             * 列表查询
             */
            all() {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all')
                    .then((responseDTO) => {
                        Loading.hide();
                        console.log("查询分类表列表：", responseDTO);
                        let resp = responseDTO.data;
                        _this.categorys = resp.responseData;
                        //将所有记录格式化为树状结构
                        //格式化之前，需要先清空level1
                        _this.level1 = [];
                        for (let i = 0; i < _this.categorys.length; i++) {
                            let categs = _this.categorys[i];
                            if (categs.parent === '00000000') {
                                _this.level1.push(categs);
                                for (let k = 0; k < _this.categorys.length; k++) {
                                    let child = _this.categorys[k];
                                    //level1增加children属性，存放二级分类数据
                                    if (child.parent === categs.id) {
                                        //当二级分类下面的children为空时，初始化children
                                        if (Tool.isEmpty(categs.children)) {
                                            categs.children = [];
                                        }
                                        categs.children.push(child);
                                    }
                                }
                            }
                        }
                        //新增二级后页面不显示，需要再次点击一级，增加自动点击事件
                        //注意：界面的渲染需要vue绑定好变量后才生效，需加延迟100ms
                        _this.level2=[];
                        setTimeout(function () {
                            //触发active点击
                            $("tr.active").trigger("click");
                        },100);
                    })
            },
            /**
             * 点击【新增一级分类】
             */
            addLevel1() {
                let _this = this;
                //清空当前分类
                _this.active = {};
                _this.level2 = [];
                _this.category = {
                    parent: "00000000"
                };
                $("#form-modal").modal("show");
            },
            addLevel2() {
                let _this = this;
                //判断一级分类
                if (Tool.isEmpty(_this.active)) {
                    Toast.warning("请先点击一级分类");
                    return;
                }
                _this.category = {
                    parent: _this.active.id
                };
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;

                //保存校验，1!=1去掉自动生成的代码第一个||或
                if (1 != 1
                    || !Validator.require(_this.category.parent, "父ID")
                    || !Validator.require(_this.category.name, "分类名称")
                    || !Validator.length(_this.category.name, "分类名称", 3, 50)
                ) {
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save', _this.category)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存分类表结果：", responseAdd);
                        let resp = responseAdd.data;
                        if (resp.success) {
                            $("#form-modal").modal('hide');
                            _this.all();
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
            edit(category) {
                let _this = this;

                _this.category = $.extend({}, category);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id) {
                let _this = this;
                Confirm.show("删除分类表后不可恢复!", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id)
                        .then((responseDel) => {
                            Loading.hide();
                            console.log("删除分类表内容：", responseDel);
                            let resp = responseDel.data;
                            if (resp.success) {
                                _this.all();
                                Toast.success("删除成功！");
                            }
                        });
                });
            },
            /**
             * 点击【显示二级菜单】
             */
            clickLevel1(category) {
                let _this = this;
                _this.active = category;
                _this.level2 = category.children;
            }
        }
    }
</script>

<style scoped>
    .active td {
        background-color: #d6e9c6 !important;
    }
</style>