
//申明调取方法，定义变量message提示
Toast={
    success:function (toastMSG) {
        Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: toastMSG,
            showConfirmButton: false,
            timer: 2000
        })
    },
    error:function (toastMSG) {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: toastMSG,
            showConfirmButton: false,
            timer: 2000
        })
    },
    warning:function (toastMSG) {
        Swal.fire({
            position: 'top-end',
            icon: 'waring',
            title: toastMSG,
            showConfirmButton: false,
            timer: 2000
        })
    }
};