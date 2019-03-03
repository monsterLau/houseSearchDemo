$("#searchButton").click(function () {
    var keyWord = $("#keyWord").val();

    if (keyWord.length == 0) {
        alert("不得为空，请输入内容！");
        return;
    }
})