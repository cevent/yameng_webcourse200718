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

    //3.长度校验
    isLength:function (str,min,max) {
        return $.trim(str).length >= min && $.trim(str).length <=max;
    },

    //4.增加日期格式化方法
    dateFormat:function (format,date) {
      let result;
      if(!date){
          date=new Date();
      }
      const option={
          "y+":date.getFullYear().toString(),
          "M+":(date.getMonth()+1).toString(), //月份需要加1
          "d+":date.getDay().toString(),
          "h+":date.getHours().toString(),
          "m+":date.getMinutes().toString(),
          "s+":date.getSeconds().toString()
      };
      for(let i in option){
        result=new RegExp("("+i+")").exec(format);
        if(result){
            format=format.replace(result[1],(result[1].length==1)?(option[i]):(option[i].padStart(result[1].length,"0")));
        }
      }
      return format;
    }
}