$(function(){
    function tab(s1,s2) {
        var links = document.querySelectorAll(s1);
        var list = document.querySelectorAll(s2);
        for (let i = 0; i < links.length; i++) {
            links[i].onclick = function () {
                for (let j = 0; j < list.length; j++) {
                    list[j].style.display = "none"
                    // links[j].style.color = "#343e48c7"
                    links[j].style.background="#ffffff"
                    // links[j].style['border-bottom']="0px"

                }
                list[i].style.display = "block"
                // this.style.background = color;
                // links[i].style['border-bottom']="2px solid #42484e87"
                // links[i].style.color="#0C0C0C"
                links[i].style.background="#d1dfdd54"
            }
        }
    }
    tab(".article-title",".article-info")
})
