Confirm={
    //接收页面的回调函数，是否success
    show:function (confirmMsg,callback) {

        Swal.fire({
            title: '确认?',
            text: confirmMsg,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确认!'
        }).then((result) => {
            if (result.value) {
                if(callback){
                    callback()
                }
            }
        });


    }
}