$("#searchHouseConfirmButton").click(function () {
    var subway = $("#subway").val();
    var lowPrice = $("#lowPrice").val();
    var lowArea = $("#lowArea").val();
    var topPrice = $("#topPrice").val();
    var topArea = $("#topArea").val();
    var room = $("#room").val();
    var bathroom = $("#bathroom").val();

    $.ajax(
        {
            url: "/searchHouse",
            data: {
                subway: subway,
                lowPrice: lowPrice,
                topPrice: topPrice,
                lowArea: lowArea,
                topArea: topArea,
                room: room,
                bathroom: bathroom
            },
            method: "POST",
            success: function (result) {
                console.log(result);
                $("#conditionResult").empty();
                var list = result.extend.success.list;
                // t=list[number]
                list.forEach(function (t, number) {
                    $("#conditionResult").append(
                        // alert(houseId);
                        "<tr>\n" +
                        "<td>" + t.houseId + "</td>\n" +
                        " <td>\n" +
                        "<div class=\"col-xs-6 col-md-3\">\n" +
                        " <img src=\"" + t.img + "\" style=\"width: 180px;height: 110px\"/>\n" +
                        " </div>\n" +
                        " </td>" +
                        "<td>" + t.tittle + "</td>\n" +
                        "<td>" + t.price + "</td>\n" +
                        "<td>" + t.room + "</td>\n" +
                        "<td>" + t.subway + "</td>\n" +
                        "<td>" + t.area + "</td>\n" +
                        "                    <td>\n" +
                        "                            <a href=\"/checkHouseDetailsById/" + t.houseId + " \" class=\"button  button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
                        "                            <a class=\"button button-glow button-rounded button-caution\"> <span class=\"glyphicon glyphicon-star\"\n" +
                        "                                                               aria-hidden=\"true\"></span>收藏</a></tr>"
                    )
                })

            }
        }
    )
})