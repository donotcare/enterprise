var xhr = new XMLHttpRequest();

xhr.open('GET', 'news/', true);
xhr.onreadystatechange = function () {
    if (this.readyState != 4) return;
    var newsContainer = document.getElementById("newsContainer");
    if (this.status == 200) {
        var news = JSON.parse(this.responseText);
        var innerHtml = "";
        for (var i = 0; i < news.length; i++) {
            innerHtml += "<li><a href = " + news[i].url + ">" + news[i].title + "</a></li>";
        }
        newsContainer.innerHTML = innerHtml;
    } else {
        newsContainer.innerHTML = "error";
    }
}
xhr.send();

w3.includeHTML();
