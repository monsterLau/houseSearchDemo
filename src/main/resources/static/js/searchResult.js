$('#searchButton').click(function () {
    // window.href.location("/searchResult");
    var keyWord = $('#keyWord').val();
    // $("#trline").empty();
    $.ajax({
        url: "/searchHouseByKeyWord",
        type: "POST",
        dataType: "JSON",
        data: {keyWord: keyWord},
        success: function (result) {
            console.log(result);
            $("#trline").empty();
            var list = result.extend.success.list;
            // t=list[number]
            list.forEach(function (t, number) {
                $("#trline").append(
                    "<tr>\n" +
                    "<td>" + t.houseId + "</td>\n" +
                    " <td>\n" +
                    "<div class=\"col-xs-6 col-md-3\">\n" +
                    " <img src=\"" + t.img + "\" style=\"width: 180px;height: 100px\"/>\n" +
                    " </div>\n" +
                    " </td>" +
                    "<td>" + t.tittle + "</td>\n" +
                    "<td>" + t.price + "</td>\n" +
                    "<td>" + t.room + "</td>\n" +
                    "<td>" + t.subway + "</td>\n" +
                    "<td>" + t.area + "</td>\n" +
                    "                    <td>\n" +
                    "                            <a href=\"/checkHouseDetailsById/" + t.houseId + "\"  class=\"button button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
                    "                            <a class=\"button button-glow button-rounded button-caution\"> <span class=\"glyphicon glyphicon-star\"\n" +
                    "                                                               aria-hidden=\"true\"></span>收藏</a></tr>"
                )
            })
        },
        error:function (XMLHttpResponse, textStatus, errorThrown) {
            console.log("1 异步调用返回失败,XMLHttpResponse.readyState:" + XMLHttpResponse.readyState);
            console.log("2 异步调用返回失败,XMLHttpResponse.status:" + XMLHttpResponse.status);
            console.log("3 异步调用返回失败,textStatus:" + textStatus);
            console.log("4 异步调用返回失败,errorThrown:" + errorThrown);
        }
    })
})