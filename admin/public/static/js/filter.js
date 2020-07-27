/**
 * 数组过滤器
 * @param list：对应页面设置常量：CHARGE:[{key:"C",value: "收费"},{key:"F",value: "免费"}],
 *                              <td>{{CHARGE | optionKV(section.charge)}}</td>
 *                              CHARGE=list | 表示过滤器引用 option=section.value,section.value
 * @param key：C、F
 * @return {string} 收费/免费
 * (list,key)在更新对象类型后弃用
 */
// let optionKV=(list,key)=> {
//     if(!list || !key){
//         return "";
//     }else{
//         let result="";
//         for(let i=0;i<list.length;i++){
//             if(key===list[i]["key"]){
//                 result=list[i]["value"];
//             }
//         }
//
//         return result;
//     }
// };
let optionKV=(object,key)=> {
    if(!object || !key){
        return "";
    }else{
        let result="";
        for(let enums in object){
            //console.log(object[enums]["key"]);
            if(key===object[enums]["key"]){
                result=object[enums]["value"];
            }
        }

        return result;
    }
};
/**
 * 增加时长过滤器
 * @param value 如：3600
 * @return {string} 10:00:00
 */
let formatSecond=(value)=>{
    value=value || 0;
    //将时间全部转化为int秒
    let second=parseInt(value,10);//秒
    let minutes=0;//分
    let hours=0;//小时
    if(second>60){
        //大于60时，转换
        minutes=Math.floor(second/60);
        second=Math.floor(second%60);
        if(minutes>60){
            hours=Math.floor(minutes/60);
            minutes=Math.floor(minutes%60);
        }
    }else{
        //小于60，不处理转换
    }
    //将获取结果拼接
    let result=""+PrefixInteger(second,2)+"";
    //如果秒，则分和时不显示---30
    // if(minutes>0){
    //     result=""+PrefixInteger(minutes,2)+":"+result;
    //     if(hours>0){
    //         result=""+PrefixInteger(hours,2)+":"+result;
    //     }
    // }
    //配置统一格式00:00:30
    result=""+PrefixInteger(minutes,2)+":"+result;
    result=""+PrefixInteger(hours,2)+":"+result;
    return result;

};

/**
 * 格式化指定长度，格式化2位，前面补0
 */
function PrefixInteger(num,length) {
    return (Array(length).join('0')+num).slice(-length);
}

export default {
    optionKV,
    formatSecond
}