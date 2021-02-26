window.onload=function () {

    //头像处移入移除
    var link1 = document.querySelector(".login-one-info");
    var list1 = document.querySelector(".hidden-userInfo-zst");
    link1.onmouseover = function () {
        link1.style.background = "#F4F4F4";
        list1.style.display = "block";
    }
    link1.onmouseout = function () {
        link1.style.background = "#FFF";
        list1.style.display = "none";
    }
    list1.onmouseover = function () {
        list1.style.display = "block";
    }
    list1.onmouseout = function () {
        link1.style.background = "#FFF";
        list1.style.display = "none";
    }

}