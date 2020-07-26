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

export default {
    optionKV
}