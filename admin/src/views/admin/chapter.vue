<template>
    <div>
        <p>
            <!--新增刷新列表按钮，点击调用list()方法-->
            <button class="btn btn-bold btn-success btn-round " v-on:click="list(1)">
                <i class="ace-icon fa fa-refresh bigger-110"></i>
                刷新
            </button>
        </p>
        <!--调用pagination组件 ref定义引用名 :list组件内部定义的方法 :list="list"为执行的list方法-->
        <pagination ref="pagination" v-bind:list="list" ></pagination>
        <!--设置页面保留8个按钮-->
<!--        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>-->
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
                chapters: []
            }
        },
        mounted: function () {
            //this.$parent.activeSidebar("business-chapter-sidebar")
            //页面初始化加载methods：list
            let _this = this;
            //分页后，设置每页行数
            _this.$refs.pagination.size=10;
            _this.list(1);//初始化执行第一页

        },
        methods: {
            //page:currentPage页面变化
            list(page) {
                let _this = this;
                //_this.$ajax.get('http://127.0.0.1:8699/business/admin/chapter/chapterDto?currentPage=1&initPageNum=5').then((responseDTO)=>{

                //切换post请求
                _this.$ajax.post('http://127.0.0.1:8699/business/admin/chapter/chapterDto',
                    {
                        currentPage: page,
                        //$refs:根据别名，子组件获取子组件，size=获取下拉框数据
                        initPageNum: _this.$refs.pagination.size
                    }).then((responseDTO) => {
                    console.log("查询章节列表：", responseDTO);
                    //获取数据传给data:chapters，加入分页失效，无法获取page信息
                    //_this.chapters=responseDTO.data.list(为html元素对象data返回值对象，接收@RequestBody流对象信息)
                    _this.chapters = responseDTO.data.list;
                    //获取分页结果，并把总条数返回，重新渲染
                    _this.$refs.pagination.render(page,responseDTO.data.sumPage);
                })
            }
        }
    }
</script>

<style scoped>

</style>