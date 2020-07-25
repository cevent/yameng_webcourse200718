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
                <#list fieldUtilList as field>
                    <th>${field.nameCN}</th>
                </#list>
                <th class="hidden-480">操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s" :key="${domain}">
                <#list fieldUtilList as field>
                    <td>{{${domain}.${field.nameSmallHump}}}</td>
                </#list>

                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(${domain})">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>
                        <button class="btn btn-xs btn-danger" v-on:click="del(${domain}.id)">
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
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="edit(${domain})">
                                        <span class="blue">
                                            <i class="ace-icon fa fa-pencil bigger-120" ></i>
                                        </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="tooltip-info" data-rel="tooltip" title="view" v-on:click="del(${domain}.id)">
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
                        <h4 class="modal-title">${tableNameCN}表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <#list fieldUtilList as field>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">${field.nameCN}</label>
                                    <div class="col-sm-10">
                                        <input
                                                v-model="${domain}.${field.nameSmallHump}"
                                                type="text" class="form-control"
                                                placeholder="${field.nameCN}" >
                                    </div>
                                </div>
                            </#list>

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
        name: "${domain}",
        components: "${module}-${domain}",
        data: function () {
            return {
                ${domain}:{},
                ${domain}s: []
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
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/${module}/admin/${domain}/list',
                    {
                        currentPage: page,
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    Loading.hide();
                    console.log("查询${tableNameCN}列表：", responseDTO);
                    let resp=responseDTO.data;
                    _this.${domain}s = resp.responseData.list;
                    _this.$refs.pagination.render(page,resp.responseData.sumPage);
                })
            },
            /**
             * 点击【新增】
             */
            add(){
                let _this=this;
                console.log("为不引起eslint提醒，调用_list:"+_this);
                _this.${domain}={};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                //校验

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+'/${module}/admin/${domain}/save', _this.${domain})
                    .then((responseAdd) => {
                        Loading.hide();
                        console.log("保存${tableNameCN}结果：", responseAdd);
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
            edit(${domain}){
                let _this=this;

                _this.${domain}=$.extend({},${domain});
                $("#form-modal").modal("show");
            },
            /**
             * 点击【删除】
             */
            del(id){
                let _this=this;
                //引入confirm
                Confirm.show("删除${tableNameCN}后不可恢复!",function () {
                    Loading.show();
                    //restFul分割请求，对应controller定义的mapping跳转类型
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+'/${module}/admin/${domain}/delete/'+id)
                        .then((responseDel)=>{
                            Loading.hide();
                            console.log("删除${tableNameCN}内容：",responseDel);
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