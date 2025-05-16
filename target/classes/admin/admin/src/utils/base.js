const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootdj912vwr/",
            name: "springbootdj912vwr",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootdj912vwr/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "爱猫小当家"
        } 
    }
}
export default base
