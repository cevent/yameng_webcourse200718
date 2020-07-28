//定义页面常量
//1.课程管理页面：点击章节管理，保存课程信息
SESSION_KEY_COURSE="SESSION_KEY_COURSE";
//2.章节管理页面：点击小节管理，保存 章节信息
SESSION_KEY_CHAPTER="SESSION_KEY_CHAPTER";
//会话缓存
SessionStorage={
    get:function (key) {
        let v=sessionStorage.getItem(key);
        if(v && typeof (v)!=="undefined" && v!=="undefined"){
            return JSON.parse(v);
        }
    },
    set:function (key,data) {
        sessionStorage.setItem(key,JSON.stringify(data))
    },
    remove:function (key) {
        sessionStorage.removeItem(key)
    },
    clearAll:function () {
        sessionStorage.clear()
    }
};