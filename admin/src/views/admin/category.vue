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

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                                        <th>分类ID</th>
                        <th>父ID</th>
                        <th>分类名称</th>
                        <th>顺序</th>
                <th class="hidden-480">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="category in categorys" :key="category.index">
                            <td>{{category.id}}</td>
                            <td>{{category.parent}}</td>
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
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="edit(category)">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120" ></i>
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="del(category.id)">
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
                        <h4 class="modal-title">分类表表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">父ID</label>
                                                <div class="col-sm-10">
                                                    <input
                                                            v-model="category.parent"
                                                            type="text" class="form-control"
                                                            placeholder="父ID" >
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">分类名称</label>
                                                <div class="col-sm-10">
                                                    <input
                                                            v-model="category.name"
                                                            type="text" class="form-control"
                                                            placeholder="分类名称" >
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">顺序</label>
                                                <div class="col-sm-10">
                                                    <input
                                                            v-model="category.sort"
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
        name: "business-category",
        components: {Pagination},
        data: function () {
            return {
                category:{},
                categorys: [],
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
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/category/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询分类表列表：", responseDTO);
                    let resp=responseDTO.data;
                    _this.categorys = resp.responseData.list;
                    _this.$refs.pagination.render(page,resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add(){
                let _this=this;
                console.log("为不引起eslint提醒，调用_list:"+_this);
                _this.category={};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save(page) {
                let _this = this;

                //保存校验，1!=1去掉自动生成的代码第一个||或
                if(1 != 1
                                || !Validator.require(_this.category.parent,"父ID")
                                || !Validator.require(_this.category.name,"分类名称")
                                || !Validator.length(_this.category.name,"分类名称",3,50)
                ){
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/business/admin/category/save', _this.category)
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存分类表结果：", responseAdd);
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
            edit(category){
                let _this=this;

                _this.category=$.extend({},category);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id){
                let _this=this;
                Confirm.show("删除分类表后不可恢复!",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/business/admin/category/delete/'+id)
                        .then((responseDel)=>{
                            Loading.hide();
                            console.log("删除分类表内容：",responseDel);
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