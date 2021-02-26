window.onload=function () {
    // 选项卡
    function tab(s1,s2,color) {
        var links = document.querySelectorAll(s1);
        var list = document.querySelectorAll(s2);
        for (let i = 0; i < links.length; i++) {
            links[i].onclick = function () {
                for (let j = 0; j < list.length; j++) {
                    list[j].style.display = "none"
                    links[j].style.background = ""
                }
                list[i].style.display = "block"
                this.style.background = color;
            }
        }
    }
    //时间戳转换为时间
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        var h = date.getHours() + ':';
        var m = date.getMinutes() + ':';
        var s = date.getSeconds();
        return Y + M + D + h + m + s;
    }
//    双重选项卡

    function tabs(selector1,selector2) {
        var links=document.querySelectorAll(selector1)
        var list=document.querySelectorAll(selector2)
        for(let i=0;i<links.length;i++){
            links[i].onclick=function () {
                for(let j=0;j<list.length;j++){
                    list[j].style.display="none"
                    links[j].style.color=""
                }
                list[i].style.display="block"
                this.style.color="red";
            }
        }
    }
}