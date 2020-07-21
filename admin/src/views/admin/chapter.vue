<template>
    <!--柵格化代码：ctrl+alt+L -->
    <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
            <th>章节ID</th>
            <th>章节名称</th>
            <th>课程ID</th>
            <th class="hidden-480">操作</th>
        </tr>
        </thead>

        <tbody>
        <!--获取数据-->
        <tr v-for="chapter in chapters" :key="chapter">
            <td>{{chapter.id}}</td>
            <td>{{chapter.name}}</td>
            <td>{{chapter.courseId}}</td>
            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-success">
                        <i class="ace-icon fa fa-check bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-warning">
                        <i class="ace-icon fa fa-flag bigger-120"></i>
                    </button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

</template>

<script>
    export default {
        name: "chapter",
        //使用data定义组件内的变量，可用于双向数据绑定（vue核心功能）
        data: function(){
            return {
                chapters: []
            }
        },
        mounted:function() {
            //this.$parent.activeSidebar("business-chapter-sidebar")
            //页面初始化加载methods：list
            let _this=this;
            _this.list();
        },
        methods:{
            list(){
                let _this=this;
                _this.$ajax.get('http://127.0.0.1:8699/business/admin/chapter/chapterDto').then((responseDTO)=>{
                    console.log("查询章节列表：",responseDTO);
                    //获取数据传给data:chapters
                    _this.chapters=responseDTO.data;
                })
            }
        }
    }
</script>

<style scoped>

</style>