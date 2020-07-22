<template>
    <div class="pagination" role="group" aria-label="分页">
        <!--每个按钮的点击，执行selectPage方法-->
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===1"
                v-on:click="selectPage(1)">1</button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===1"
                v-on:click="selectPage(page-1)">上一页</button>

        <button type="button" class="btn btn-default btn-white btn-round"
                v-for="p in pages" :key="p" v-bind:id="'page-'+p"
                v-bind:class="{'btn-primary active':page===p}"
                v-on:click="selectPage(p)">{{p}}</button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===pageTotal"
                v-on:click="selectPage(page+1)">下一页</button>
        <button type="button" class="btn btn-default btn-white btn-round"
                v-bind:disabled="page===pageTotal"
                v-on:click="selectPage(pageTotal)">{{pageTotal||1}}</button>
        <!--尾页定义了pageTotal如果为空，则返回1-->
        &nbsp; &nbsp;
        <span class="m--padding-10">
            每页
            <select v-model="size">
                <option value="1">1</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            条，共【{{sumPage}}】条
        </span>
    </div>
</template>

<script>
    export default {
        name: "pagination",
        //props：定义给父组件使用的变量-->向子组件传递的参数，可以是一个函数/数据，这里暴露了list和itemCount给外部调用
        props:{
            list:{
                type:Function,
                default:null
            },
            //显示的页码数，共50页，显示5页，其他用...表示
            itemCount:Number
        },
        data:function () {
            return{
                //注意：这里的变量需要和chapter传入的变量名一致，变量名与PageDto一致，否则造成NaN等解析异常
                sumPage:0,  //总条数
                size:10,  //每页条数
                page:0,    //当前页码，接收chapter渲染的page页码
                pageTotal:0, //总页码
                pages:[]  //显示的页码数组
            }
        },
        methods:{
            /**
             * 渲染分页组件
             */
            render(page,sumPage){
                let _this=this;
                _this.page=page;
                _this.sumPage=sumPage;
                _this.pageTotal=Math.ceil(sumPage / _this.size);
                //默认分页后显示的页码数：10
                _this.pages=_this.getPageItems(_this.pageTotal,page,_this.itemCount || 10);
            },
            //查询每一页
            selectPage(page){
                let _this=this;
                if(page<1){
                    page=1;
                }
                if(page>_this.pageTotal){
                    page=_this.pageTotal;
                }
                if(this.page!==page){
                    _this.page=page;
                    if(_this.list){ //list为向外暴露的list
                        _this.list(page);
                    }
                }
            },

            //当前显示的页码
            getPageItems(sumPage,current,length){
                let items=[];
                if(length>=sumPage){
                    for(let i=1;i<=sumPage;i++){
                        items.push(i);
                    }
                }else{
                    let base=0;
                    //前移
                    if(current-0>Math.floor((length-1) / 2)){
                        //后移
                        base=Math.min(sumPage,current-0+Math.ceil((length-1) / 2)) - length;
                    }
                    for(let i=1;i<=length;i++){
                        items.push(base+i);
                    }
                }
                return items;
            }
        },

    }
</script>

<style scoped>
    .pagination {
        vertical-align: middle !important;
        font-size: 16px;
        margin-top: 0;
        margin-bottom: 10px;
    }

    .pagination button {
        margin-right: 5px;
    }

    .btn-primary.active {
        background-color: #2f7bba !important;
        border-color: #27689d !important;
        color: white !important;
        font-weight: 600;
    }

    /*.pagination select {*/
    /*vertical-align: middle !important;*/
    /*font-size: 16px;*/
    /*margin-top: 0;*/
    /*}*/
</style>