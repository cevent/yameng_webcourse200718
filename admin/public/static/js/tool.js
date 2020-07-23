Tool={
    //1.空值null/""校验，返回true
    isEmpty:function (obj) {
        if(typeof obj=='string'){
            return !obj || obj.replace(/\s+/g,"")=="";
        }else{
            return (!obj || JSON.stringify(obj)==="{}" || obj.length===0);
        }
    },

    //2.非空校验
    isNotEmpty:function (obj) {
        return !this.isEmpty();
    },

    //2.长度校验
    isLength:function (str,min,max) {
        return $.trim(str).length >= min && $.trim(str).length <=max;
    }
}