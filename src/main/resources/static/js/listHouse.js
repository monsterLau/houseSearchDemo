var houseId = $("#button").val();

$.ajax({
    url: "/allHouse",
    type: "GET",
    data: {},
    async: false,
    // beforeSend: function (xhr) {
    //     xhr.setRequestHeader(header, token);
    // },
    success: function (result) {
        console.log(result.extend.success.list);
        var list = result.extend.success.list;
        // t=list[number]
        list.forEach(function (t, number) {
            $("#trline").append("<tr>\n" +
                "<td>" + t.houseId + "</td>\n" +
                "<td><img src=\"" + t.img + "\" style=\"width: 150px;height: 100px\"/></td>\n" +
                "<td>" + t.tittle + "</td>\n" +
                "<td>" + t.price + "</td>\n" +
                "<td>" + t.room + "</td>\n" +
                "<td>" + t.subway + "</td>\n" +
                "<td>" + t.area + "</td>\n" +
                // "<td>" + t.操作 + "</td>\n" +
                " \n" +
                "<td>\n" +
                "<a href=\"/admin/updateHouse/" + t.houseId + "\" class=\"button button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>修改信息</a>\n" +
                "<a onclick='delHouse (" + t.houseId + ")' class=\"button button-glow button-rounded button-caution \"> <span class=\"glyphicon glyphicon-star\"\n" +
                "aria-hidden=\"true\"></span>下架房屋</a>\n" +
                "\n" +
                "</td>\n" +
                "</tr>")
        })

    },
    error: function (result) {
        alert("失败！");
    }
});

function delHouse(id) {
// console.log(id)
    var houseId = id;
    $.ajax({
        url: "/admin/deleteHouse/" + houseId,
        type: "POST",
        data: { houseId: houseId},
        success: function () {
            // alert("success!");
            window.location.href = "/admin/listHouse";
        }
    })
}


