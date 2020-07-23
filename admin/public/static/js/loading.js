Loading={
    show:function () {
        $.blockUI({
            message:'<img src="/static/image/loading.gif" />',
            css:{
                padding:"10px",
                left:"50%",
                width:"80px",
                marginLeft:"-40px"
            }
        });
    },
    hide:function () {
        //本地查询太快，加延迟出现
        setTimeout(function () {
            $.unblockUI();
        },500)
    }
}